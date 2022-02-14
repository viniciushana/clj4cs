(ns clj4cs.server
  (:gen-class)
  (:require [io.pedestal.http :as server]
            [clj4cs.system :as system]))

(defn -main
  "The entry-point for 'lein run'"
  [& args]
  (println "\nCreating your server...")
  (com.stuartsierra.component/start-system (system/new-system :prod)))
