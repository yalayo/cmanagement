(ns cmanagement.users.events
  (:require
   ;[clojure.data.json :as json]
   [re-frame.core :as re-frame]
   ["amazon-cognito-identity-js" :refer [AuthenticationDetails CognitoUserPool CognitoUser]]
   [cmanagement.users.db :as db]))

(def pool-data {:UserPoolId "us-east-1_ihQJoizHk" :ClientId "6a79ijj3m88rqal042ic1rpgbb"})

(re-frame/reg-event-fx
 :initialize-app
 (fn [_ _]
   {:db db/default-db}))

(re-frame/reg-event-fx
 :login
 (fn [{:keys [db]} [_ credentials navigation]]
   (let [email (credentials :email)
         password (credentials :password)
         data {:Username email :Password password}
         details (new AuthenticationDetails (clj->js data))
         pool (new CognitoUserPool (clj->js pool-data))
         user-data {:Username email :Pool pool}
         cognito-user (new CognitoUser (clj->js user-data))]
     (.authenticateUser cognito-user details
                        (clj->js
                         {:onSuccess (fn [result]
                                       (js/console.log result)
                                       (re-frame/dispatch [:login-success result])
                                       (.navigate navigation :register-compound))
                          :onFailure (fn [error]
                                       (let [data (js->clj error :keywordize-keys true)
                                             message (data :message)]
                                         (re-frame/dispatch [:login-error message])))
                          :newPasswordRequired (fn [user-attributes]
                                                 (re-frame/dispatch [:store-attributes cognito-user user-attributes])
                                                 (.navigate navigation :new-password))})))))

(re-frame/reg-event-fx
 :store-attributes
 (fn [{:keys [db]} [_ user attributes]]
   (let [with-user (assoc db :cognito-user user)
         with-attr (assoc with-user :user-attributes attributes)]
     {:db with-attr})))

(re-frame/reg-event-fx
 :new-password
 (fn [{:keys [db]} [_ new-password navigation]]
   (let [user-attributes (db :user-attributes)
         cognito-user (db :cognito-user)
         temp (js->clj user-attributes :keywordize-keys true)
         attr (dissoc temp :email_verified)
         att (dissoc attr :phone_number_verified)
         with-name (assoc att :name "Temp")
         password (new-password :password)]
     (.completeNewPasswordChallenge cognito-user password (clj->js with-name)
                                    (clj->js {:onSuccess (fn [result]
                                                           (js/console.log result))
                                              :onFailure (fn [error]
                                                           (js/console.error error))})))))

(re-frame/reg-event-fx
 :login-error
 (fn [{:keys [db]} [_ error]]
   {:db (assoc-in db [:errors :login] error)}))

(re-frame/reg-event-fx
 :login-success
 (fn [{:keys [db]} [_ user]]
   {:db (assoc db :user user)}))
