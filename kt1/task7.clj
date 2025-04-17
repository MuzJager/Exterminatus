(defn my-reverse [coll]
  (loop [lst coll rev-list []]
    (if (empty? lst)
      rev-list
      (recur (rest lst) (cons (first lst) rev-list)))))

(defn fib [n]
  (if (<= n 1)
    n
    (+ (fib (- n 1)) (fib (- n 2)))))

(defn fast-fib [n1 n2 counter]
  (if (= counter 0)
    n1
    (recur n2 (+ n1 n2) (dec counter))))

(println "Reverse [1 2 3 4]:" (my-reverse [1 2 3 4])) ;; (4 3 2 1)

(println "Fib 10:" (fib 10)) ;; 55
(println "Fast-fib 1000:" (fast-fib 1 1 1000)) ;; Очень быстрое вычисление 1000-го числа Фибоначчи
