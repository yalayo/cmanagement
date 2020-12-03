(ns cmanagement.users.subs
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
 :errors ;; usage: (subscribe [:errors])
 (fn [db _]
   (:errors db)))

(reg-sub
 :loading ;; usage: (subscribe [:loading])
 (fn [db _]
   (:loading db)))
