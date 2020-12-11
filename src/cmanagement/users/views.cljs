(ns cmanagement.users.views
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            ["react-native" :as rn]
            [cmanagement.users.events :as events]
            [cmanagement.core.components :as c]))

(defn errors-list [errors]
  [:ul.error-messages
   (for [[key [val]] errors]
     ^{:key key} [:li (str (name key) " " val)])])

(defn sign-in [{:keys [navigation] :as props}]
  (let [default {:email "" :password ""}
        credentials (reagent/atom default)
        navigate (:navigate navigation)]
    (fn []
      (let [{:keys [email password]} @credentials]
        [c/safe-area-view {:flex 1}
         [c/view {:flex 0.2}
          [c/text {:style {:font-size 30 :align-self :center}} "Sign in"]]
         [c/view {:flex 0.8}
                                        ;(when (:login errors)
                                        ;[errors-list (:login errors)])
          [c/text-input {:style {:margin-horizontal 20 :margin-vertical 10} :placeholder "Email" :default-value email :on-change-text #(swap! credentials assoc :email  %)}]
          [c/text-input {:style {:margin-horizontal 20 :margin-vertical 10} :placeholder "Password" :default-value password :secureTextEntry true :on-change-text #(swap! credentials assoc :password %)}]
          [c/button {:style {} :label "Sign In" :on-press #(do (re-frame/dispatch [:login @credentials navigation]))}]]]))))


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


;(defn loading [props]
;  (fn [props]
;    [view {:style {:flex            1
;                      :backgroundColor "#333333"
;                      :alignItems      "center"
;                      :justifyContent  "center"}}
;     [text "Loading"]]))
