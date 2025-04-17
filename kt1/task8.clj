(require '[clojure.string :as str])

;; Функция contains для проверки наличия элемента в списке
(defn contains [coll x]
  (> (count (filter #(= % x) coll)) 0))

;; Функция для проверки, является ли строка палиндромом
(defn is-palindrome [s]
  (let [filtered-str (apply str (filter #(Character/isLetter %) (str/lower-case s)))]
    (= filtered-str (apply str (reverse filtered-str)))))


(println "Contains 3 in [1 2 3 4 5]:" (contains [1 2 3 4 5] 3))  ;; true
(println "Contains 6 in [1 2 3 4 5]:" (contains [1 2 3 4 5] 6))  ;; false

(println "Is 'А роза упала на лапу Азора' a palindrome?:" (is-palindrome "А роза упала на лапу Азора"))  ;; true
(println "Is 'Hello' a palindrome?:" (is-palindrome "Hello"))  ;; false
