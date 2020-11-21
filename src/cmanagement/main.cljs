(ns cmanagement.main
  (:require [reagent.core :as r]
            [react-native :as rn]))

(defn adapt [class]
  (r/adapt-react-class class))

(def safe-area-view (adapt rn/SafeAreaView))
(def view (adapt rn/View))
(def text (adapt rn/Text))

(defn app []
  [safe-area-view {:flex 1}
   [view {:flex 1 :align-items "center" :justify-content "center"}
    [text {:style {:font-size 50}} "Hello Jason!"]]])

;; the function figwheel-rn-root must be provided. It will be called by 
;; react-native-figwheel-bridge to render your application. 
;; You can configure the name of this function with config.renderFn
(defn figwheel-rn-root []
  (r/as-element [app]))
