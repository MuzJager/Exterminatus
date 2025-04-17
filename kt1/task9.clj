(def robots [{:name "RobotA" :hp 100} {:name "RobotB" :hp 200} {:name "RobotC" :hp 150}])

(defn fight [robot-a robot-b]
  (if (> (:hp robot-a) (:hp robot-b))
    robot-a
    robot-b))

(defn three-round-fight [robot-a robot-b]
  (loop [r1 robot-a, r2 robot-b, round 1]
    (if (= round 4)
      (if (> (:hp r1) (:hp r2)) r1 r2)
      (recur (fight r1 r2) (fight r2 r1) (inc round)))))

(def new-robot {:name "RobotD" :hp 180})

(defn robot [attrs]
  {:name (first attrs)
   :hp (second attrs)
   :attack (last attrs)})

(def smart-robot (robot ["умный" 15 40]))

;; Вызовы функций
(println "Robot HPs:" (map :hp robots))  ;; (100 200 150)

(println "Winner of the fight:"
         (three-round-fight {:name "RobotA" :hp 100} {:name "RobotB" :hp 150}))  ;; {name "RobotB" :hp 110}

(println "Fights:" (map #(fight new-robot %) robots))  ;; Обрабатывает бой для каждого робота

(println "Smart robot:" smart-robot)  ;; {:name "умный", :hp 15, :attack 40}
