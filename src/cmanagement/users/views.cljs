(ns cmanagement.users.views
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            ["react-native" :as rn]
            [clojure.string :as string]
            [clojure.spec.alpha :as s]
            [cmanagement.users.events :as events]
            [cmanagement.core.components :as c]
            [cmanagement.users.specs :as specs]))

(defn errors-list [errors]
  [:ul.error-messages
   (for [[key [val]] errors]
     ^{:key key} [:li (str (name key) " " val)])])

(defn sign-in [{:keys [navigation] :as props}]
  (let [default {:email "" :password ""}
        credentials (reagent/atom default)]
    (fn []
      (let [{:keys [email password]} @credentials
            errors @(re-frame/subscribe [:errors])]
        [c/safe-area-view {:flex 1}
         [c/view {:flex 0.2}
          [c/text {:style {:font-size 30 :align-self :center}} "Sign in"]
          [c/button {:style {} :label "Sign Up" :on-press #(.navigate navigation :sign-up)}]]

         [c/view {:flex 0.8}
          (when (some? errors)
            [c/errors-list (errors :login)])
          [c/text-input {:style {:margin-horizontal 20 :margin-vertical 10} :placeholder "Email" :default-value email :on-change-text #(swap! credentials assoc :email  %)}]
          [c/text-input {:style {:margin-horizontal 20 :margin-vertical 10} :placeholder "Password" :default-value password :secureTextEntry true :on-change-text #(swap! credentials assoc :password %)}]
          [c/button {:style {} :label "Sign In"
                     :on-press #(if (s/valid? ::specs/email email)
                                                             (re-frame/dispatch [:login @credentials navigation])
                                                             (re-frame/dispatch [:login-error "Invalid email, please correct it!"]))
                     :disabled (or (string/blank? email)
                                   (string/blank? password))}]]]))))

(defn sign-up [{:keys [navigation] :as props}]
  (let [default {:email "" :password "" :password-conf "" :name ""}
        user-data (reagent/atom default)]
    (fn []
      (let [{:keys [email password password-conf name]} @user-data
            errors @(re-frame/subscribe [:errors])]
        [c/safe-area-view {:flex 1}
         [c/view {:flex 0.1}
          [c/text {:style {:font-size 30 :align-self :center}} "Sign up"]]
         [c/view {:flex 0.9}
          (when (some? errors)
            [c/errors-list (errors :register)])
          [c/text-input {:style {:margin-horizontal 20 :margin-vertical 10} :placeholder "Name" :default-value name :on-change-text #(swap! user-data assoc :name  %)}]
          [c/text-input {:style {:margin-horizontal 20 :margin-vertical 10} :placeholder "Email" :default-value email :on-change-text #(swap! user-data assoc :email  %)}]
          [c/text-input {:style {:margin-horizontal 20 :margin-vertical 10} :placeholder "Password" :default-value password :on-change-text #(swap! user-data assoc :password %)}]
          [c/text-input {:style {:margin-horizontal 20 :margin-vertical 10} :placeholder "Password confirmation" :default-value password-conf :on-change-text #(swap! user-data assoc :password-conf %)}]
          [c/button {:style {} :label "Sign Up"
                     :on-press #(if (s/valid? ::specs/email email)
                                  (if (= password password-conf)
                                    (re-frame/dispatch [:register @user-data navigation])
                                    (re-frame/dispatch [:register-error "Password should match the password confirmation!"]))
                                  (re-frame/dispatch [:register-error "Invalid email, please correct it!"]))
                     :disabled (or (string/blank? name)
                                   (string/blank? email)
                                   (string/blank? password)
                                   (string/blank? password-conf))}]]]))))


(defn new-password [{:keys [navigation] :as props}]
  (let [default {:password ""}
        new-password (reagent/atom default)]
    (fn []
      (let [{:keys [password]} @new-password]
        [c/safe-area-view {:flex 1}
         [c/view {:flex 0.2}
          [c/text {:style {:font-size 30 :align-self :center}} "New password"]]
         [c/view {:flex 0.8}
          [c/text-input {:style {:margin-horizontal 20 :margin-vertical 10} :placeholder "Password" :default-value password :secureTextEntry true :on-change-text #(swap! new-password assoc :password %)}]
          [c/button {:style {} :label "Accept" :on-press #(re-frame/dispatch [:new-password @new-password navigation])}]]]))))

(defn confirm-user [{:keys [navigation] :as props}]
  (let [default {:code ""}
        confirmation-code (reagent/atom default)]
    (fn []
      (let [{:keys [code]} @confirmation-code]
        [c/safe-area-view {:flex 1}
         [c/view {:flex 0.2}
          [c/text {:style {:font-size 30 :align-self :center}} "Confirm user"]]
         [c/view {:flex 0.8}
          (when (some? errors)
            [c/errors-list (errors :register)])
          [c/text-input {:style {:margin-horizontal 20 :margin-vertical 10} :placeholder "Conformation code" :default-value code :on-change-text #(swap! confirmation-code assoc :code %)}]
          [c/button {:style {} :label "Accept"
                     :on-press #(re-frame/dispatch [:confirm-user code navigation])
                     :disabled (string/blank? code)}]]]))))


;(defn loading [props]
;  (fn [props]
;    [view {:style {:flex            1
;                      :backgroundColor "#333333"
;                      :alignItems      "center"
;                      :justifyContent  "center"}}
;     [text "Loading"]]))
