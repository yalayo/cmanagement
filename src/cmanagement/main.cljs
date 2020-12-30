(ns cmanagement.main
  (:require [reagent.core :as r]
            [re-frame.core :as re-frame]
            [cmanagement.core.components :as c]
            [cmanagement.users.views :as users]
            [cmanagement.users.subs :as usubs]
            [cmanagement.core.subs :as csubs]
            [cmanagement.dashboard.views :as dashboard]))

(defn security []
  [c/navigator {:mode :modal :header-mode :none :initial-route-name :sign-in}
   [c/screen {:name :sign-in :component (r/reactify-component users/sign-in) :options {:gestureEnabled false}}]
   [c/screen {:name :sign-up :component (r/reactify-component users/sign-up) :options {:gestureEnabled false}}]
   [c/screen {:name :new-password :component (r/reactify-component users/new-password) :options {:gestureEnabled false}}]
   [c/screen {:name :confirm-user :component (r/reactify-component users/confirm-user) :options {:gestureEnabled false}}]])

(defn app []
  ;(js/console.error @(re-frame/subscribe [:user]))
  [c/nav-container
   (if (nil? @(re-frame/subscribe [:user]))
     [security]
     [dashboard/home])])


;; the function figwheel-rn-root must be provided. It will be called by
;; react-native-figwheel-bridge to render your application.
;; You can configure the name of this function with config.renderFn
(defn figwheel-rn-root []
  (re-frame/dispatch-sync [:initialize-app])
  (r/as-element [app]))
