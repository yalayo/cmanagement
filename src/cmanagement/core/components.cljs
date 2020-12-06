(ns cmanagement.core.components
  (:require [reagent.core :as reagent]
            ["react-native" :as rn]))

(defn adapt [class]
  (reagent/adapt-react-class class))

(def text (adapt rn/Text))
(def text-input-adaptor (adapt rn/TextInput))
(def touchable-opacity (adapt rn/TouchableOpacity))


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
