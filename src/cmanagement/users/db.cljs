(ns cmanagement.users.db
  (:require ["@react-native-community/async-storage" :as storage]
            [promesa.core :as p]
            [clojure.string :as s]))

(def default-db
  {:initial-route {}})
