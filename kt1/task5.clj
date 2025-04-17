(defn repeat' [x]
  (lazy-seq (cons x (repeat' x))))

(defn subseq [start end coll]
  (take (- end start) (drop start coll)))

(defn in-first-half [elem coll]
  (let [half (quot (count coll) 2)]
    (some #(= elem %) (take half coll))))

(println "Repeat 3:" (take 5 (repeat' 3))) ;; (3 3 3 3 3)

(println "Subsequence:" (subseq 2 5 (range 1 10))) ;; (3 4 5)

(println "In first half (3 in [1 2 3 4 5 6 7 8]):" (in-first-half 3 [1 2 3 4 5 6 7 8])) ;; true
(println "In first half (7 in [1 2 3 4 5 6 7 8]):" (in-first-half 7 [1 2 3 4 5 6 7 8])) ;; false
