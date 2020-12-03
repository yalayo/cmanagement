(ns cmanagement.users.events
  (:require
   [re-frame.core :as re-frame]
   [cmanagement.users.db :as db]))

(re-frame/reg-event-db
 ::initialize-db
 (fn initialize-db-handler [_ _]
   db/default-db))

(re-frame/reg-event-fx
 ::login
 (fn [{:keys [db]} [_ _]]
   ))
