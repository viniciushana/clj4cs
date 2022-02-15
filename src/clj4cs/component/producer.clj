(ns clj4cs.component.producer
  (:require [com.stuartsierra.component :as component]))

(defn init-producer [host port]
  (str host ":" port))

(defrecord Producer [host port connection]
  component/Lifecycle

  (start [component]
    (println ";; Starting producer")
    (let [conn (init-producer host port)]
      (assoc component :connection conn)))

  (stop [component]
    (println ";; Stopping producer")
    #_(.close connection)
    (assoc component :connection nil)))

(defn new-producer [host port]
  (map->Producer {:host host :port port}))

(defn produce [producer topic message]
  ; does the magic
  true)