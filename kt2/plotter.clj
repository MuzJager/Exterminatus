(ns plotter)

(def colors {:black "чёрный" :red "красный" :green "зелёный"})
(def carriage-states {:up :up :down :down})

(defn create-plotter-state []
  {:position [0 0]
   :angle 0
   :color :black
   :carriage :up})

(defn calc-new-position [distance angle [x y]]
  (let [radians (* angle (/ Math/PI 180))
        new-x (+ x (* distance (Math/cos radians)))
        new-y (+ y (* distance (Math/sin radians)))]
    [(Math/round new-x) (Math/round new-y)]))

(defn move [state distance]
  (let [new-pos (calc-new-position distance (:angle state) (:position state))]
    (if (= (:carriage state) :down)
      (println "Чертим линию из" (:position state) "в" new-pos "используя" (colors (:color state))))
    (assoc state :position new-pos)))

(defn turn [state angle]
  (assoc state :angle (mod (+ (:angle state) angle) 360)))

(defn set-color [state color]
  (assoc state :color color))

(defn set-position [state position]
  (assoc state :position position))

(defn carriage-up [state]
  (assoc state :carriage :up))

(defn carriage-down [state]
  (assoc state :carriage :down))

(defn draw-triangle [state size]
  (-> state
      carriage-down
      (move size)
      (turn 120)
      (move size)
      (turn 120)
      (move size)
      carriage-up))

(defn draw-square [state size]
  (-> state
      carriage-down
      (move size)
      (turn 90)
      (move size)
      (turn 90)
      (move size)
      (turn 90)
      (move size)
      carriage-up))

;; Использование:
(let [state (create-plotter-state)]
  (-> state
      (draw-triangle 100)
      (set-position [10 10])
      (set-color :red)
      (draw-square 80)))
