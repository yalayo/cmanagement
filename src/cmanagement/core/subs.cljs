(ns cmanagement.core.subs
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
 :initial-route
 (fn [db _]
   (:initial-route db)))
