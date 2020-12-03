(ns cmanagement.users.views
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            ["react-native" :as rn]
            [cmanagement.users.events :as events]))

(defn adapt [class]
  (reagent/adapt-react-class class))

(def view (adapt rn/View))
(def text (adapt rn/Text))
(def text-input (adapt rn/TextInput))

(defn errors-list [errors]
  [:ul.error-messages
   (for [[key [val]] errors]
     ^{:key key} [:li (str (name key) " " val)])])

(defn sign-in []
  (let [default {:email "" :password ""}
        credentials (reagent/atom default)]
    (fn []
      (let [{:keys [email password]} @credentials
            login-user (fn [event credentials]
                         (.preventDefault event)
                         (re-frame/dispatch [::login credentials]))]
        [view {:flex 1}
         [text {:style {:font-size 30 :align-self :center}} "Sign in"]
         ;(when (:login errors)
           ;[errors-list (:login errors)])
         [text-input {:style {:margin-horizontal 20 :margin-vertical 10} :placeholder "Email" :default-value email :on-change-text #(swap! credentials assoc :email (-> % .-target .-value))}]
         [text-input {:style {:margin-horizontal 20 :margin-vertical 10} :placeholder "Password" :default-value password :on-change-text #(swap! credentials assoc :password (-> % .-target .-value))} ]
         [:> rn/Button {:title   "Sign In"} :on-click #(login-user % @credentials)]]))))

;; :value email :on-change #(swap! credentials assoc :email (-> % .-target .-value))

;; :value password :on-change #(swap! credentials assoc :password (-> % .-target .-value))
