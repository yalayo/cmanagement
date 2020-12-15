(ns cmanagement.main
  (:require [reagent.core :as r]
            [re-frame.core :as re-frame]
            [cmanagement.core.components :as c]
            [cmanagement.users.views :as users]
            [cmanagement.users.subs :as s]
            [cmanagement.compounds.views :as compounds]))

(defn app []
  [c/nav-container
   [c/navigator {:mode :modal :header-mode :none :initial-route-name :sign-in}
    [c/screen {:name :sign-in :component (r/reactify-component users/sign-in) :options {:gestureEnabled false}}]
    [c/screen {:name :sign-up :component (r/reactify-component users/sign-up) :options {:gestureEnabled false}}]
    [c/screen {:name :new-password :component (r/reactify-component users/new-password) :options {:gestureEnabled false}}]
    [c/screen {:name :register-compound :component (r/reactify-component compounds/register-compound) :options {:gestureEnabled false}}]]])

;; the function figwheel-rn-root must be provided. It will be called by
;; react-native-figwheel-bridge to render your application.
;; You can configure the name of this function with config.renderFn
(defn figwheel-rn-root []
  (re-frame/dispatch-sync [:initialize-app])
    (r/as-element [app]))
