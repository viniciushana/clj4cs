(ns clj4cs.component.db
  (:require [com.stuartsierra.component :as component]))

(defn connect-to-database [host port]
  (str host ":" port))                                      ; deveria retornar uma impl de java.sql.Connection

(defrecord Database [host port connection]                  ; records misturam dados e comportamentos, aqui temos os dados
  component/Lifecycle                                       ; records podem implementar protocols, que são como interfaces

  (start [component]
    (println ";; Starting database")
    (let [conn (connect-to-database host port)]
      (assoc component :connection conn)))                  ; retornamos o componente com uma conexão associada

  (stop [component]
    (println ";; Stopping database")
    #_(.close connection)                                     ; é assim que se chama um método de objeto java
    (assoc component :connection nil)))                     ; retornamos o componente sem uma conexão ativa

(defn new-database [host port]
  (map->Database {:host host :port port}))                  ; essa função é dinamicamente gerada para converter um map em um record

(defn query [db criteria]                                   ; recebe o db e retorna os resultados do criterio
  [])