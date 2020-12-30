(ns cmanagement.dashboard.views
  (:require [reagent.core :as r]
            [cmanagement.core.components :as c]
            [cmanagement.compounds.views :as compounds]))

(defn home [{:keys [navigation] :as props}]
  [c/navigator {:mode :modal :header-mode :none :initial-route-name :register-compound}
   [c/drawer-screen {:name :register-compound :component (r/reactify-component compounds/register-compound) :options {:gestureEnabled false}}]])
