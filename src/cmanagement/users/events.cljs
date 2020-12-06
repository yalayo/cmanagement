(ns cmanagement.users.events
  (:require
   [clojure.string :as string]
   [re-frame.core :as re-frame]
   ["amazon-cognito-identity-js" :refer [AuthenticationDetails CognitoUserPool CognitoUser]]
   [cmanagement.users.db :as db]))

(def pool-data {:UserPoolId "us-east-1_ihQJoizHk" :ClientId "6a79ijj3m88rqal042ic1rpgbb"})

(re-frame/reg-event-db
 ::initialize-db
 (fn initialize-db-handler [_ _]
   db/default-db))

(re-frame/reg-event-fx
 :login
 (fn [{:keys [db]} [_ credentials]]
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
                                       (js/console.log result))
                          :onFailure (fn [error]
                                       (js/console.error error))})))))
