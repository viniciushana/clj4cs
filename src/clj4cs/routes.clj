(ns clj4cs.routes
  (:require [ring.util.response :as ring-resp]
            [io.pedestal.http.body-params :as body-params]
            [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]))

(defn about-page [_]
  (ring-resp/response (format "Clojure %s - served from %s"
                              (clojure-version)
                              (route/url-for ::about-page))))

(defn home-page [_]
  (ring-resp/response "Hello, world!"))                     ; o ring é uma lib que facilita lidar com http

(def common-interceptors [(body-params/body-params) http/html-body])

; aqui temos configs mais relacionadas a pedestal, como as rotas e o que elas fazem
(def routes #{["/" :get (conj common-interceptors `home-page) :route-name :greet]
              ["/about" :get (conj common-interceptors `about-page)]})