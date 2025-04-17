(ns task3
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def books-by-year
  {2021 [{:title "Программирование на Clojure" :price 100.0}
         {:title "Backend в IThub" :price 400.0}]
   2022 [{:title "Java для начинающих" :price 150.0}
         {:title "Микросервисы на Java" :price 500.0}]})

(def orders-file "orders.txt")

(defn print-menu []
  (println "Наши книги")
  (println "1 - Меню")
  (println "2 - Заказы")
  (println "3 - Выход"))

(defn print-books-by-year []
  (println "Доступные книги по годам:")
  (doseq [year (keys books-by-year)]
    (println year)))

(defn print-books-for-year [year]
  (if-let [books (get books-by-year year)]
    (do
      (println (str "Книги за " year ":"))
      (doseq [book books]
        (println (str "- " (:title book) " (R" (:price book) ")"))))
    (println "Нет данных git push -u origin mainза этот год")))

(defn calculate-price [book count]
  (* (:price book) count))

(defn confirm-order [book count]
  (let [total (calculate-price book count)]
    (println (str "Покупка " count " шт. " (:title book) " всего за: R" total))))

(defn save-order [book count]
  (spit orders-file
        (str "Куплено " count " шт. " (:title book) " за R" (calculate-price book count) "\n")
        :append true))

(defn load-orders []
  (if (.exists (io/file orders-file))
    (slurp orders-file)
    "Нет заказов"))

(defn -main []
  (loop []
    (print-menu)
    (let [choice (read-line)]
      (cond
        (= choice "1") (do
                         (print-books-by-year)
                         (let [year (Integer. (read-line))]
                           (print-books-for-year year)
                           (println "Введите название книги:")
                           (let [book-title (read-line)]
                             (println "Введите количество:")
                             (let [count (Integer. (read-line))
                                   book (first (filter #(= (:title %) book-title) (get books-by-year year)))]
                               (if book
                                 (do
                                   (confirm-order book count)
                                   (save-order book count))
                                 (println "Ошибка: книга не найдена"))))))

        (= choice "2") (println (load-orders))

        (= choice "3") (do (println "Выход") (System/exit 0))

        :else (do (println "Неверный ввод, попробуйте снова.") (recur))))))
