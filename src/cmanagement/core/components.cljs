(ns cmanagement.core.components
  (:require [reagent.core :as reagent]
            ["react-native" :as rn]
            ["@react-navigation/stack" :refer [createStackNavigator]]
            ["@react-navigation/native" :refer [NavigationContainer]]
            ["@react-navigation/drawer" :refer [createDrawerNavigator DrawerContentScrollView DrawerItemList DrawerItem]]))

(defn adapt [class]
  (reagent/adapt-react-class class))

(def safe-area-view (adapt rn/SafeAreaView))
(def view (adapt rn/View))
(def button-adapter (adapt rn/Button))
(def text (adapt rn/Text))
(def text-input-adaptor (adapt rn/TextInput))
(def touchable-opacity (adapt rn/TouchableOpacity))
(def nav-container (adapt NavigationContainer))
(def stack (createStackNavigator))
(def drawer (createDrawerNavigator))
(def navigator (adapt (.-Navigator stack)))
(def screen (adapt (.-Screen stack)))
(def dc-scroll-view (adapt DrawerContentScrollView))
(def drawer-item-list (adapt DrawerItemList))
(def drawer-item (adapt DrawerItem))
(def drawer-navigator (adapt (.-Navigator drawer)))
(def drawer-screen (adapt (.-Screen drawer)))



(defn text-input [{:keys [style] :as props}]
  [text-input-adaptor (assoc props
                             :autoCapitalize :none
                             :style (merge {:height 50 :border-width 1
                                            :border-color "rgba(100,100,100,0.4)"
                                            :padding-horizontal 10}
                                           style))])

#_(defn button [{:keys [style label] :as props}]
  [view {:style {:align-items :center :margin-vertical 5 :color "#5cb85c"}}
   [touchable-opacity (assoc props
                             :style (merge {:align-items :center
                                            :margin-vertical 5
                                            :color "#5cb85c"}
                                           style))
    [text label]]])

(defn button [props]
  [view {:style {:align-items :center :margin-vertical 5}}
   [button-adapter (merge {:color "#5cb85c"} props)]])

(defn errors-list [errors]
  [view {:style {:margin-horizontal 20 :margin-top 10}}
   [text {:style {:color "#b85c5c"}} (str errors)]])
