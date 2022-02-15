(ns clj4cs.model.customer-test
  (:require [clojure.test :refer :all]
            [clj4cs.model.customer :as model.customer]
            [clj4cs.factory.customer :as factory]))

(deftest check-customer-is-active-with-unused-card
  (testing "active unused card with old card"
    (is (some? (model.customer/customer-has-active-unused-card? factory/customer-1))))
  (testing "only active unused card"
    (is (some? (model.customer/customer-has-active-unused-card? factory/customer-2))))
  (testing "active used card"
    (is (nil? (seq (model.customer/customer-has-active-unused-card? factory/customer-3))))))