(ns task2
  (:gen-class))

(defn -main [& args]
  (println "Received arguments:" args)  
  (let [nums (map #(Integer. %) args)
        sum (apply + nums)]
    (println "Sum of numbers:" sum))) 
