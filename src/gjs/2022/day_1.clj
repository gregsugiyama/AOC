(ns gjs.2022.day-1
  (:require [util.santas-little-helpers :as h]
            [clojure.string]))

(defn parse-input
  "Returns a list of Integers. Replaces breaks with 0"
  [input]
  (map (fn [i]
         (if (empty? i)
           0
           (h/parse-int i))) input))

(comment
  (def input (h/read-input "gjs/2022/day_1.txt"))

  ; solution part 1
  (->> (parse-input input)
       (partition-by pos?)
       (map #(apply + %))
       (apply max))

  ; solution part 2
  (->> (parse-input input)
       (partition-by pos?)
       (mapv #(apply + %))
       (remove zero?)
       (sort >)
       (take 3)
       (apply +)))




