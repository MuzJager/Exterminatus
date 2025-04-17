(defn my-rest [coll]
  (if (empty? coll)
    nil
    (rest coll)))

(defn gcd [a b]
  (cond
    (= b 0) a               ;; Если b равно 0, то возвращаем a
    :else (recur b (mod a b))))  ;; Иначе продолжаем рекурсию

(println "My-rest [1 2 3]:" (my-rest [1 2 3]))  ;; (2 3)
(println "My-rest []:" (my-rest []))            ;; nil

(println "GCD 28 14:" (gcd 28 14))              ;; 14
(println "GCD 48 18:" (gcd 48 18))              ;; 6
