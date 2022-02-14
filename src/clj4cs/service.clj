(ns clj4cs.service
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [io.pedestal.http.body-params :as body-params]
            [ring.util.response :as ring-resp]
            [clj4cs.system :as system]))

(defn about-page [_]
  (ring-resp/response (format "Clojure %s - served from %s"
                              (clojure-version)
                              (route/url-for ::about-page))))

(defn home-page [_]
  (ring-resp/response "Hello World!"))

(def common-interceptors [(body-params/body-params) http/html-body])

(def routes #{["/" :get (conj common-interceptors `home-page)]
              ["/about" :get (conj common-interceptors `about-page)]})

(def service {:env                     :prod
              :system                  (system/example-system {:host "localhost" :port 1234})
              ::http/routes            routes
              ::http/resource-path     "/public"
              ::http/type              :jetty
              ::http/port              8080
              ::http/container-options {:h2c? true
                                        :h2?  false
                                        :ssl? false}})
