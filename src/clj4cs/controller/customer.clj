(ns clj4cs.controller.customer
  (:require [clj4cs.model.customer :as model]
            [clj4cs.component.db :as db]
            [clj4cs.component.producer :as producer]))

;similar a controllers em .net, eles podem servir como os orquestradores de responder a um request
; eles só precisam ser determinados a uma rota, de forma a receberem o system em seus argumentos
(defn check-customers-with-active-and-unused-cards [{db :db producer :producer}] ;facilidade sintática para extrair valores de um mapa
  ; threading -> e ->> servem para evitar criar muitas expressões nested.
  ; -> pega o resultado da expressão anterior e serve como primeiro arg da proxima, e ->> como ultimo arg
  ; é bastante comum que operações em coleções sejam orientadas a receberem a coleção como ultimo arg
  (->> (db/query db "select...")
       (filter #(model/customer-has-active-unused-card? %))
       (run! #(producer/produce producer :customer-has-unused-card %))))

; run! aqui serve para evitar uma pegadinha!
; resultados de operações em coleções, como filter ou map, retornam valores lazy!
; se vc passar uma sequencia lazy para o producer, não necessariamente ele vai realizar a computação para realizar efeitos colaterais
; run! garante que os efeitos colaterais são realizados sem produzir mais outro resultado