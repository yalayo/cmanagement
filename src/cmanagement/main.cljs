(ns cmanagement.main
  (:require [reagent.core :as r]
            [re-frame.core :as re-frame]
            [cmanagement.core.components :as c]
            [cmanagement.users.views :as users]
            [cmanagement.compounds.views :as compounds]
            [cmanagement.users.events :as events]))

(defn app []
  [c/nav-container
   [c/navigator {:mode :modal :header-mode :none :initial-route-name :sign-in}
    [c/screen {:name :sign-in :component (r/reactify-component users/sign-in) :options {:gestureEnabled false}}]
    [c/screen {:name :new-password :component (r/reactify-component users/new-password) :options {:gestureEnabled false}}]
    [c/screen {:name :register-compound :component (r/reactify-component compounds/register-compound) :options {:gestureEnabled false}}]]])

;; the function figwheel-rn-root must be provided. It will be called by
;; react-native-figwheel-bridge to render your application.
;; You can configure the name of this function with config.renderFn
(defn figwheel-rn-root []
  (re-frame/dispatch-sync [::events/initialize-db])
    (r/as-element [app]))
