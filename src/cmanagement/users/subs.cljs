(ns cmanagement.users.subs
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
 :errors
 (fn [db _]
   (:errors db)))

(reg-sub
 :loading
 (fn [db _]
   (:loading db)))

(reg-sub
 :user-attributes
 (fn [db _]
   (:user-attributes db)))
