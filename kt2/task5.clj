(defmacro multi-print [n s]
  `(dotimes [_ ~n] (println ~s)))

(multi-print 3 "Привет!")
;; Результат:
;; Привет!
;; Привет!
;; Привет!


(multi-print 5 "Hello!")

(defmacro and-ors [& args]
  (let [or-groups (partition-by #(= % '|) args)]
    `(and ~@(map #(apply or %) or-groups))))

;; Пример вызова:
(and-ors (> 5 3) (= 6 6) | (> 6 3) | (= 5 5 5))
;; Результат: true

(and-ors
 (and-ors (= 3 3) | (= 5 5) (= 6 8))
 | (> 5 3) (= 6 6) (< 7 8)
 | (> 6 3)
 | (= 5 5 5))
;; Результат: false
