(ns clj4cs.system
  [:require [com.stuartsierra.component :as component]
            [clj4cs.components.db :as db]])

(defn example-system [config-options]
  (let [{:keys [host port]} config-options]
    (component/system-map
      :db (db/new-database host port))))