(def is-last-in-stock
  (comp #(get % :in-stock) last))

(defn average [xs] (/ (reduce + 0 xs) (count xs)))

(defn average-dollar-value [cars]
  (average (map #(get % :dollar-value) cars)))

(def fastest-car
  (comp #(str (:name %) " - быстрее всех")
        last
        (partial sort-by :horsepower)))
(println "Is last car in stock?" (is-last-in-stock [{:name "Car1" :in-stock true} {:name "Car2" :in-stock false}]))  ;; false
(println "Average dollar value:" (average-dollar-value [{:name "Car1" :dollar-value 10000} {:name "Car2" :dollar-value 20000}]))  ;; 15000
(println "Fastest car:" (fastest-car [{:name "Car1" :horsepower 100} {:name "Car2" :horsepower 200}]))  ;; Car2 - быстрее всех