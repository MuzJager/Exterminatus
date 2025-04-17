(ns task4
  (:require [clojure.test :refer :all]
            [coffee-ordering-system :as system]))

(deftest test-display-order
  (is (= (system/display-order {:coffee-name "Эспрессо" :quantity 2 :price 50}) "Кофе: Эспрессо, Количество: 2, Цена: 50")))

(deftest test-file-exist
  (is (true? (system/file-exist "orders.txt"))))

(deftest test-save-coffee-order
  (is (= (system/save-coffee-order {:coffee-name "Американо" :quantity 1 :price 80}) "Заказ сохранён")))

(deftest test-load-orders
  (is (= (system/load-orders) "Кофе: Эспрессо, Количество: 2, Цена: 50\n")))

;; Пример вызова тестов:
(run-tests)
