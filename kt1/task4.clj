(defn increment [n]       ;; Определяем функцию increment
  (+ n 1))

(defn double [n]          ;; Определяем функцию double
  (* n 2))

(defn square [n]          ;; Определяем функцию square
  (* n n))

(defn if-even [f n]       ;; Функция, которая выполняет переданную функцию f, если число чётное
  (if (even? n)
    (f n)
    n))

(def if-even-inc (partial if-even increment))    ;; Применение функции increment для чётных чисел
(def if-even-double (partial if-even double))    ;; Применение функции double для чётных чисел
(def if-even-square (partial if-even square))    ;; Применение функции square для чётных чисел

(defn binary-partial [f x]  ;; Частичное применение для бинарной функции
  (fn [y] (f x y)))

;; Вызовы функций:
(println "If-even-inc 4:" (if-even-inc 4))     ;; 5 (так как 4 чётное)
(println "If-even-double 6:" (if-even-double 6)) ;; 12 (так как 6 чётное)
(println "If-even-square 8:" (if-even-square 8)) ;; 64 (так как 8 чётное)

;; Проверяем binary-partial:
(def add5 (binary-partial + 5))  ;; Функция, которая добавляет 5 к аргументу
(println "Binary-partial 5 + 10:" (add5 10)) ;; 15
