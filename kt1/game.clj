
(def number-to-guess (atom nil))  
(def low (atom nil))              
(def high (atom nil))             
(def current-guess (atom nil))   


(defn start [low-range high-range] ; начало игры
  (reset! low low-range)
  (reset! high high-range)
  (reset! number-to-guess (rand-int (- high-range low-range)))
  (reset! current-guess nil)
  (println "Я готов..."))


(defn guess-my-number [] ;угадывание
  (let [mid (int (/ (+ @low @high) 2))]
    (reset! current-guess mid)
    (println @current-guess)
    @current-guess))

(defn smaller []
  (swap! high dec)
  (guess-my-number))

(defn bigger []
  (swap! low inc)
  (guess-my-number))
