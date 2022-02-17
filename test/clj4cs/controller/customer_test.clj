(ns clj4cs.controller.customer-test
  (:require [clojure.test :refer :all]
            [clj4cs.controller.customer :as controller]
            [clj4cs.component.db :as db]
            [clj4cs.component.producer :as producer]
            [mockfn.matchers :refer :all]
            [mockfn.clj-test :as mfn]
            [matcher-combinators.matchers :as m]
            [matcher-combinators.standalone :refer [match?]]
            [clj4cs.factory.customer :as factory]))

(def producer (producer/new-producer "localhost" 123))
(def db (db/new-database "localhost" 123))

; o mockfn é uma lib do nubank que facilita a criação de mocks.
; o providing determina comportamentos que valem no escopo do deftest
; o verifying faz asserções nos mocks, usando alguns matchers avançados que outra lib do nubank provê
(mfn/deftest notifying-customers-with-unused-active-cards
  (mfn/providing
    (db/query db "select...") factory/customers
    (producer/produce producer :customer-has-unused-card (any)) :result)

  (mfn/testing "triggers events for eligible customers"
    (controller/check-customers-with-active-and-unused-cards {:producer producer :db db})
    (mfn/verifying
      (db/query db "select...") factory/customers (exactly 1)
      (producer/produce producer :customer-has-unused-card (any)) :result (exactly 2))))