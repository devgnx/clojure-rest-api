(ns clojure-rest-api.api-test
  (:require [clojure.test :refer :all]
            [clojure-rest-api.routes :refer [app]]))

(defn request
  ([resource]
   (request :get resource nil))
  ([method resource]
   (request method resource nil))
  ([method resource params]
   (app {:request-method method :uri resource :params params})))

(deftest api-test
  (testing "Test API: ping route"
    (let [response (request "/api/ping")]
      (is (= 200 (:status response)))
      (is (= "pong" (:body response)))))

  (testing "Test API: Hellow World route"
    (let [response (request "/api/hello-world")]
      (is (= 200 (:status response)))
      (is (= "Hello, World!" (:body response)))))

  (testing "Test API: Hello :name route"
    (let [response (request :patch "/api/name" {:name "Joseph"})]
      (is (= 200 (:status response)))
      (is (= "Hello, Joseph" (:body response)))))

  (testing "Test API: Echo route param"
    (let [response (request "/api/echo/Hello")]
      (is (= 200 (:status response)))
      (is (= "Hello" (:body response)))))

  (testing "Test API: Echo post param"
    (let [response (request :post "/api/echo" {:foo "bar"})]
      (is (= 200 (:status response)))
      (is (= "{\"foo\":\"bar\"}" (:body response)))))

  (testing "Test API: PUT person"
    (let [response (request :put "/api/person" {:name "John" :age "21" :favorite-color "blue"})]
      (is (= 200 (:status response)))
      (is (= "{\"name\":\"John\",\"age\":21,\"favorite-color\":\"blue\"}"))))

  (testing "Test API: Not Found route"
    (let [response (request "/not-found")]
      (is (= 404 (:status response)))
      (is (= "Not Found" (:body response))))))