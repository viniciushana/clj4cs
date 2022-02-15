(ns clj4cs.factory.customer
  (:require [clojure.test :refer :all]))

(def customer-1
  {:customer/cards
   [{:card/activated-at #inst"2020-05-12"
     :card/transactions []}
    {:card/deactivated-at #inst"2020-05-12"
     :card/activated-at   #inst"2020-01-02"
     :card/transactions
     [{:transaction/id     (java.util.UUID/randomUUID)
       :transaction/amount 53.62M}]}]})

(def customer-2
  {:customer/cards
   [{:card/activated-at #inst"2020-05-12"
     :card/transactions []}]})

(def customer-3
  {:customer/cards
   [{:card/activated-at #inst"2020-05-12"
     :card/transactions [{:transaction/id     (java.util.UUID/randomUUID)
                          :transaction/amount 53.62M}]}]})

(def customers [customer-1 customer-2 customer-3])