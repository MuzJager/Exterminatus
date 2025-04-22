(ns url-shortener.client
  (:require [clojure.java.io :as io]
            [cheshire.core :as json])
  (:import [java.net HttpURLConnection URL]))

(defn request [method path data]
  (let [url (URL. (str "http://localhost:3000" path))
        conn (.openConnection url)]
    (.setRequestMethod conn method)
    (.setRequestProperty conn "Content-Type" "application/json")
    (.setDoOutput conn true)
    (when data
      (with-open [w (io/writer (.getOutputStream conn))]
        (.write w (json/generate-string data))))
    (let [status (.getResponseCode conn)
          stream (if (<= 200 status 299)
                   (.getInputStream conn)
                   (.getErrorStream conn))]
      {:status status
       :body (slurp stream)})))

(defn print-menu []
  (println "\nВыберите действие:")
  (println "1. Создать")
  (println "2. Показать")
  (println "3. Изменить")
  (println "4. Удалить")
  (println "5. Выйти"))

(defn -main []
  (loop []
    (print-menu)
    (let [choice (read-line)]
      (case choice
        "1" (do
              (print "Введите полный URL: ") (flush)
              (let [url (read-line)
                    res (request "POST" "/" {:url url})]
                (println "Короткий URL: " (:body res)))
              (recur))

        "2" (do
              (print "Введите короткий URL: ") (flush)
              (let [short (read-line)
                    res (request "GET" (str "/" short) nil)]
                (println "Результат: " (:body res)))
              (recur))

        "3" (do
              (print "Введите короткий URL: ") (flush)
              (let [short (read-line)]
                (print "Введите новый полный URL: ") (flush)
                (let [url (read-line)
                      res (request "PUT" (str "/" short) {:url url})]
                  (println "Статус: " (:status res))))
              (recur))

        "4" (do
              (print "Введите короткий URL: ") (flush)
              (let [short (read-line)
                    res (request "DELETE" (str "/" short) nil)]
                (println "Статус: " (:status res)))
              (recur))

        "5" (println "Выход.")

        (do (println "Неверный выбор.") (recur))))))