(ns clj4cs.controller.customer-test
  (:require [clojure.test :refer :all]
            [clj4cs.controller.customer :as controller]
            [clj4cs.component.db :as db]
            [clj4cs.component.producer :as producer]
            [mockfn.clj-test :as mfn]))

(def producer (producer/new-producer "localhost" 123))
(def db (db/new-database "localhost" 123))

(mfn/deftest notifying-customers-with-unused-active-cards
  (mfn/testing "triggers events for eligible customers"
    (is (controller/check-customers-with-active-and-unused-cards
          {:producer producer
           :db       db})))
  (mfn/providing (db/query db "select..."))


  )