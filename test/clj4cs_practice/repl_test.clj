(ns clj4cs-practice.repl-test
  (:require [clojure.test :refer :all]
            [clj4cs-practice.repl :refer [fizzbuzz]]))

; copiamos o código que iteramos no source para testes e pronto, temos isso automatizado
(deftest fizzbuzz-test
  (is (= (fizzbuzz 3) "fizz") "fizz")
  (is (= (fizzbuzz 5) "buzz") "buzz")
  (is (= (fizzbuzz 15) "fizzbuzz"))
  (is (nil? (fizzbuzz 4)) "nada"))