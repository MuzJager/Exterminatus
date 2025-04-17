(defn flock [n]
  {:seagulls n})

(defn conjoin [flock1 flock2]
  (update flock1 :seagulls + (:seagulls flock2)))

(defn breed [flock1 flock2]
  (update flock1 :seagulls * (:seagulls flock2)))


(let [flock-a (flock 4)
      flock-b (flock 2)
      flock-c (flock 0)]
  (let [result (-> flock-a
                   (conjoin flock-c)
                   (breed flock-b)
                   (conjoin (breed flock-a flock-b)))]
    (println "Resulting seagulls:" (:seagulls result))))  ;; 48
