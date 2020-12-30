(ns cmanagement.dashboard.views
  (:require [reagent.core :as r]
            [cmanagement.core.components :as c]
            [cmanagement.compounds.views :as compounds]))

(defn show-data [{:keys [navigation] :as props}]
  [c/safe-area-view {:flex 1}
   [c/view
    [c/text "Test"]
    [c/button {:style {} :label "Open" :on-press #(.openDrawer navigation)}]]])

(defn custom-drawer-content [{:keys [navigation] :as props}]
  [c/dc-scroll-view
   [c/drawer-item-list
    [c/drawer-item {:label "Close" :on-press #(.closeDrawer navigation)}]]])

(defn home [{:keys [navigation] :as props}]
  [c/drawer-navigator {:contentComponent (r/reactify-component custom-drawer-content) :drawerWidth      300
                       :drawerPosition   "left"
                       :drawerBackgroundColor "#c0c0c0"
                       :drawerType "front"} ;{:drawerContent custom-drawer-content}
   [c/drawer-screen {:name :show-data :component (r/reactify-component show-data) :options {:gestureEnabled false}}]
   [c/drawer-screen {:name :register-compound :component (r/reactify-component compounds/register-compound) :options {:gestureEnabled false}}]])
