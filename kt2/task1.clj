(ns task1
  (:require [clojure.string :as str]
            [clojure.pprint :refer [print-table]]
            [clojure.set :as set]))

(def users #{"иван_шаповал" "софья_кузина" "алина_мага" "павел_безусый" "иван_соболев"})

;; Преобразование: заменяем "_" на пробел и делаем имена с заглавной буквы
(defn format-name [name]
  (str/join " " (map str/capitalize (str/split name #"_"))))

(def formatted-users (set (map format-name users)))

(def admins #{"Иван Шаповал" "Павел Безусый" "Алина Мага"})

(defn is-admin? [user]
  (set/subset? #{user} admins))

(println "Formatted users:" formatted-users)

;; Напечатать таблицу с пользователями
(print-table [{:user-name "Иван Шаповал"}
              {:user-name "Павел Безусый"}
              {:user-name "Алина Мага"}])

(println "Admin check:")
(doseq [user formatted-users]
  (println (str user " is admin? " (is-admin? user))))
