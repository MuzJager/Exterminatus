(defn full-address [name]
  (str "Многоуважаемый(ая) " name ", Владивосток, Россия"))

(defn get-location [city name]
  (if (= city "Владивосток")
    (full-address name)
    (str name ", " city)))

(println (get-location "Владивосток" "Иван Иванов"))  ;; "Многоуважаемый(ая) Иван Иванов, Владивосток, Россия"
(println (get-location "Москва" "Анна Петрова"))      ;; "Анна Петрова, Москва"
