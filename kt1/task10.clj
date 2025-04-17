(def words (partial clojure.string/split " "))

(def filter-qs (partial filter #(re-seq #"q" %)))

(defn maximum [xs]
  (reduce #(if (>= %2 %1) %2 %1) ##-Inf xs))
(println "Words in sentence:" (words "Hello world, Clojure is awesome!"))  ;; ["Hello" "world," "Clojure" "is" "awesome!"]
(println "Filter qs in list:" (filter-qs ["quick" "brown" "fox"]))  ;; ("quick")
(println "Maximum in list:" (maximum [1 2 3 4 5]))  ;; 5