(def increment #(inc %))
(def double #(* 2 %))
(def square #(* % %))
(def transform #(if (even? %) (+ % 2) (- (* 3 %) 1)))


(println "Increment 5:" (increment 5))   ;; 6
(println "Double 4:" (double 4))         ;; 8
(println "Square 3:" (square 3))         ;; 9
(println "Transform 6:" (transform 6))   ;; 8 (чётное: n+2)
(println "Transform 5:" (transform 5))   ;; 14 (нечётное: 3*n - 1)
