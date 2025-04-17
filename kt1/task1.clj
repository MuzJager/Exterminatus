(ns task1)  ;; Определяем пространство имён (namespace)

(defn increment [n]
  (+ n 1))

(defn double [n]
  (* n 2))

(defn square [n]
  (* n n))

(defn transform [n]
  (if (even? n)
    (+ n 2)
    (- (* 3 n) 1)))

;; Вызовы функций (можно удалить, если хотим вызывать в REPL)
(println "Increment 5:" (increment 5))
(println "Double 4:" (double 4))
(println "Square 3:" (square 3))
(println "Transform 6:" (transform 6))
(println "Transform 5:" (transform 5))
