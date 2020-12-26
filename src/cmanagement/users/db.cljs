(ns cmanagement.users.db
  (:require ["@react-native-community/async-storage" :as s]
            [promesa.core :as p]))

(def default-db
  {:errors {}})

(def as (.-default s))

(defn initial-db []
  (-> (p/resolved (.getItem as "user"))
      (p/then (fn [user]
                (js/console.log "user from as")
                {:user {}}))))
