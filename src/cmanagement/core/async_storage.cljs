(ns cmanagement.core.async-storage
  (:require ["@react-native-community/async-storage" :as st]
            [clojure.string :as s]))

(def AsyncStorage (.-default st))

(defn clj->json [data]
  (.stringify js/JSON data))

(def serialize clj->json)

(defn deserialize [data]
  (.parse js/JSON data))

(defn get-item [key cb]
  (.. AsyncStorage
      (getItem key)
      (then #(cb (deserialize %)))))

(defn set-item [key value]
  (.setItem AsyncStorage key (serialize value)))

