(ns cmanagement.main
  (:require [reagent.core :as r]
            ["react-native" :as rn]
            ["aws-amplify" :refer (Amplify)]
            ["@react-navigation/stack" :refer [createStackNavigator]]
            ["@react-navigation/native" :refer [NavigationContainer]]
            [re-frame.core :as re-frame]
            [cmanagement.users.views :as users]
            [cmanagement.users.events :as events]))

(defn adapt [class]
  (r/adapt-react-class class))

(def stack (createStackNavigator))

(def view (adapt rn/View))
(def text (adapt rn/Text))
(def navigator (adapt (.-Navigator stack)))
(def screen (adapt (.-Screen stack)))
(def nav-container (adapt NavigationContainer))

(defn app []
  [nav-container
   [navigator {:mode :modal :header-mode :none :initial-route-name :sign-in}
    [screen {:name :sign-in :component (r/reactify-component users/sign-in) :options {:gestureEnabled false}}]
    [screen {:name :new-password :component (r/reactify-component users/new-password) :options {:gestureEnabled false}}]]])


;(defn dash-board []
;  [safe-area-view {:flexDirection "row" :padding 20}
;   [view {:flex 1 :align-items "center" :justify-content "center" :borderWidth 1 :borderRadius 10}
;    [text "TOTAL INGRESOS"]
;    [text {:style {:font-size 18 :color "blue"}} "$32575.00"]]
;   [view {:flex 1 :align-items "center" :justify-content "center" :borderWidth 1 :borderRadius 10}
;    [text "TOTAL GASTOS"]
;    [text {:style {:font-size 18 :color "blue"}} "$20590.00"]]
;   [view {:flex 1 :align-items "center" :justify-content "center" :borderWidth 1 :borderRadius 10}
;    [text "GANANCIA TOTAL"]
;    [text {:style {:font-size 18 :color "blue"}} "$17100.00"]]])

;; the function figwheel-rn-root must be provided. It will be called by
;; react-native-figwheel-bridge to render your application.
;; You can configure the name of this function with config.renderFn
(defn figwheel-rn-root []
  (re-frame/dispatch-sync [::events/initialize-db])
    (r/as-element [app]))

