(ns clj4cs.server
  (:gen-class)
  (:require [io.pedestal.http :as server]
            [clj4cs.system :as system]))

(defn -main
  "The entry-point for 'lein run'"
  [& args]
  (println "\nCreating your server...")
  (com.stuartsierra.component/start-system (system/new-system :prod)))

; o ponto de entrada basicamente cria um novo mapa de componentes e inicializa
; a importância de ter funções de start e stop via o protocol de Component aparece aqui:
; a inicialização que a lib faz é chamar o start de cada componente, e o pedestal é um deles
