(ns clj4cs.controller.customer
  (:require [clj4cs.model.customer :as model]
            [clj4cs.component.db :as db]
            [clj4cs.component.producer :as producer]))

(defn check-customers-with-active-and-unused-cards [{db :db producer :producer}]
  (->> (db/query db "select...")
       (filter #(model/customer-has-active-unused-card? %))
       (run! #(producer/produce producer :customer-has-unused-card %))))
