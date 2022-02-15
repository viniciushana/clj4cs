(ns clj4cs.model.customer-test
  (:require [clojure.test :refer :all]
            [clj4cs.model.customer :as model.customer]))

(deftest check-customer-is-active-with-unused-card
  (testing "active unused card with old card"
    (is (seq? (model.customer/customer-has-active-unused-card? {:customer/cards
                                                                [{:card/activated-at #inst"2020-05-12"
                                                                  :card/transactions []}
                                                                 {:card/deactivated-at #inst"2020-05-12"
                                                                  :card/activated-at   #inst"2020-01-02"
                                                                  :card/transactions
                                                                  [{:transaction/id     (java.util.UUID/randomUUID)
                                                                    :transaction/amount 53.62M}]}]}))))
  (testing "only active unused card"
    (is (seq? (model.customer/customer-has-active-unused-card? {:customer/cards
                                                                [{:card/activated-at #inst"2020-05-12"
                                                                  :card/transactions []}]}))))
  (testing "active used card"
    (is (not (seq? (model.customer/customer-has-active-unused-card? {:customer/cards
                                                                 [{:card/deactivated-at #inst"2020-05-12"
                                                                   :card/activated-at   #inst"2020-01-02"
                                                                   :card/transactions
                                                                   [{:transaction/id     (java.util.UUID/randomUUID)
                                                                     :transaction/amount 53.62M}]}]}))))))