(ns clj4cs.system
  [:require [com.stuartsierra.component :as component]
            [clj4cs.component.db :as db]
            [io.pedestal.http :as http]
            [clj4cs.component.pedestal :as pedestal]
            [clj4cs.routes :as routes]])

(defn new-system [env]
  (component/system-map
    :db (db/new-database "localhost" 1234)

    :producer (clj4cs.component.producer/new-producer "localhost" 586)

    :service-map
    {:env          env
     ::http/routes routes/routes
     ::http/type   :jetty
     ::http/port   8890
     ::http/join?  false}

    :pedestal
    (component/using
      (pedestal/new-pedestal)
      [:service-map])))