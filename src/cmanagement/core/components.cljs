(ns cmanagement.core.components
  (:require [reagent.core :as reagent]
            ["react-native" :as rn]
            ["@react-navigation/stack" :refer [createStackNavigator]]
            ["@react-navigation/native" :refer [NavigationContainer]]))

(defn adapt [class]
  (reagent/adapt-react-class class))

(def safe-area-view (adapt rn/SafeAreaView))
(def view (adapt rn/View))
(def text (adapt rn/Text))
(def text-input-adaptor (adapt rn/TextInput))
(def touchable-opacity (adapt rn/TouchableOpacity))
(def stack (createStackNavigator))
(def navigator (adapt (.-Navigator stack)))
(def screen (adapt (.-Screen stack)))
(def nav-container (adapt NavigationContainer))


(defn text-input [{:keys [style] :as props}]
  [text-input-adaptor (assoc props
                             :autoCapitalize :none
                             :style (merge {:height 50 :border-width 1
                                            :border-color "rgba(100,100,100,0.4)"
                                            :padding-horizontal 10}
                                           style))])

(defn button [{:keys [style label] :as props}]
  [touchable-opacity (assoc props
                            :style (merge {:align-items :center
                                           :margin-vertical 5
                                           :color "#5cb85c"}
                                          style))
   [text label]])

(defn errors-list [errors]
  [view {:style {:margin-horizontal 20 :margin-top 10}}
   [text {:style {:color "#b85c5c"}} (str errors)]])
