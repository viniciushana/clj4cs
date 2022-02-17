(ns clj4cs.component.pedestal
  (:require [com.stuartsierra.component :as component]
            [io.pedestal.http :as http]))

(defn test?
  [service-map]
  (= :test (:env service-map)))

; pedestal é mantido como um componente no mesmo nível de db e demais, a web stack é só mais um componente
; service-map aqui, no caso, recebe justamente a dependência que fora descrita no system
(defrecord Pedestal [service-map service]
  component/Lifecycle

  (start [this]
    ; aqui vemos um exemplo de truthyness. se service for false ou nil, é considerado false.
    (if service
      this
      (cond-> service-map                                   ;essa construção é sintaticamente mais complexa.
              true                      http/create-server
              (not (test? service-map)) http/start
              true                      ((partial assoc this :service)))))

  (stop [this]
    (when (and service (not (test? service-map)))
      (http/stop service))
    (assoc this :service nil)))

(defn new-pedestal []
  (map->Pedestal {}))



; sobre cond->
; lembra que a gente viu `cond` no fizzbuzz? ele testava diversas condições e fazia short circuit assim que uma fosse verdade
; em outras partes, vemos que -> e ->> são apenas syntatic sugar para encadear expressões
; cond-> pega um valor, e testa diversas condições, sem fazer short circuiting e encadeando o resultado para a próxima condição
; recomendo bastante brincar com isso no repl! é uma construção complicada mas pode ser bem expressiva em alguns casos