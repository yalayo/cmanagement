(ns cmanagement.main
  (:require [reagent.core :as r]
            ["react-native" :as rn]
            ["aws-amplify" :refer (Amplify)]
            ["@react-navigation/stack" :refer [createStackNavigator]]
            ["@react-navigation/native" :refer [NavigationContainer]]
            [re-frame.core :as re-frame]
            [cmanagement.users.views :as users]
            [cmanagement.users.events :as events]))

(def aws-manual
  ;; User pool reframerecomamplifye16cd456a_userpool_16cd456a-dev
  {:Auth {:identityPoolId "us-east-1:6e9a4922-32d8-431d-9f8a-5d3a5015027a"
          :region "us-east-1"
          :userPoolId "us-east-1_ihQJoizHk"
          :userPoolWebClientId "6gnfb2fg2bpkjqi22lp15jtav8"
          }})

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
   [navigator {:mode :modal :header-mode :none :initial-route-name :main}
    [screen {:name :main :component (r/reactify-component users/sign-in) :options {:gestureEnabled false}}]]])
    ;[navigator {:mode :modal :header-mode :none}
                                        ;[screen {:name :main :component users/sign-in}]]]])

(defn dash-board []
  [safe-area-view {:flexDirection "row" :padding 20}
   [view {:flex 1 :align-items "center" :justify-content "center" :borderWidth 1 :borderRadius 10}
    [text "TOTAL INGRESOS"]
    [text {:style {:font-size 18 :color "blue"}} "$32575.00"]]
   [view {:flex 1 :align-items "center" :justify-content "center" :borderWidth 1 :borderRadius 10}
    [text "TOTAL GASTOS"]
    [text {:style {:font-size 18 :color "blue"}} "$20590.00"]]
   [view {:flex 1 :align-items "center" :justify-content "center" :borderWidth 1 :borderRadius 10}
    [text "GANANCIA TOTAL"]
    [text {:style {:font-size 18 :color "blue"}} "$17100.00"]]])

;; the function figwheel-rn-root must be provided. It will be called by
;; react-native-figwheel-bridge to render your application.
;; You can configure the name of this function with config.renderFn
(defn figwheel-rn-root []
  (re-frame/dispatch-sync [::events/initialize-db])
  (.configure Amplify (clj->js aws-manual))
  (r/as-element [app]))
