(ns url-shortener.core
  (:require [ring.adapter.jetty :as jetty]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.util.response :as response]
            [cheshire.core :as json])
  (:gen-class))

(defonce db (atom {})) ;; Хранилище вида {"short1" "https://long-url.com"}

(defn generate-short-url []
  (str (subs (str (java.util.UUID/randomUUID)) 0 6)))

(defroutes app-routes
  ;; GET /short -> normal
  (GET "/:short" [short]
    (if-let [normal (@db short)]
      (response/response (json/generate-string {:url normal}))
      (response/status (response/response "Not Found") 404)))

  ;; POST / -d {"url": "https://..."}
  (POST "/" {body :body}
    (let [data (json/parse-stream (clojure.java.io/reader body) true)
          normal-url (:url data)
          short (generate-short-url)]
      (swap! db assoc short normal-url)
      (response/response (json/generate-string {:short short}))))

  ;; PUT /short -d {"url": "https://new"}
  (PUT "/:short" [short :as req]
    (let [data (json/parse-stream (clojure.java.io/reader (:body req)) true)
          normal-url (:url data)]
      (if (@db short)
        (do (swap! db assoc short normal-url)
            (response/status (response/response "Updated") 200))
        (response/status (response/response "Not Found") 404))))

  ;; DELETE /short
  (DELETE "/:short" [short]
    (if (@db short)
      (do (swap! db dissoc short)
          (response/status (response/response "Deleted") 200))
      (response/status (response/response "Not Found") 404)))

  (route/not-found "Not Found"))

(defn -main []
  (jetty/run-jetty app-routes {:port 3000}))