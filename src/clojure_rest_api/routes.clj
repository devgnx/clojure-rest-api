(ns clojure-rest-api.routes
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [clojure.tools.logging :as log]
            [ring.middleware.json :as json]
            [ring.util.response :refer [response]]))

(defroutes app-routes
  (GET "/api/ping" []
    (response "pong"))

  (GET "/api/hello-world" []
    (response "Hello, World!"))

  (PATCH "/api/hello-name" {:keys [params]}
    (let [{:keys [name]} params]
      (response (str "Hello, " name))))

  (GET "/api/get-by-id/:id" [id]
    (response
     (some #(and (= (Integer/parseInt id) (:id %)) %)
           [{:id 1 :name "Mia Wallace"}
            {:id 2 :name "Jules Winnfield"}
            {:id 3 :name "Vincent Vega"}])))

  (POST "/api/save" {:keys [params]}
    (response params))

  (PUT "/api/person" {:keys [params]}
    (let [{:keys [person]} params]
      (response person)))

  (route/resources "/")
  (route/not-found "Not Found"))


(defn wrap-fallback-exception
  [handler]
  #(try
     (handler %)
     (catch Exception e
       (log/error e)
       {:status 500 :body (.getMessage e)})))


(def app
  (-> (handler/api app-routes)
      (json/wrap-json-params)
      (json/wrap-json-response)
      (wrap-fallback-exception)))