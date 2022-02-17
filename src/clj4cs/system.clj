(ns clj4cs.system
  [:require [com.stuartsierra.component :as component]
            [clj4cs.component.db :as db]
            [io.pedestal.http :as http]
            [clj4cs.component.pedestal :as pedestal]
            [clj4cs.component.producer :as producer]
            [clj4cs.routes :as routes]])

; aqui definimos uma função que cria um novo mapa de dependências
(defn new-system [env]
  (component/system-map
    ; dependências são identificadas por chave e valor
    :db (db/new-database "localhost" 1234)                  ; o valor é um record que implementa o protocol de Component

    :producer (producer/new-producer "localhost" 586)

    :service-map
    {:env          env
     ::http/routes routes/routes
     ::http/type   :jetty
     ::http/port   8890
     ::http/join?  false}

    :pedestal
    (component/using                                        ; using permite definir interdependências entre componentes
      (pedestal/new-pedestal)                               ; aqui, cria-se a dependência em si
      [:service-map])))                                     ; aqui, o vector determina quais componentes são necessários