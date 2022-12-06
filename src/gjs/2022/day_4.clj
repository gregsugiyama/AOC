(ns gjs.2022.day-4
  (:require
   [clojure.set :refer [intersection subset?]]
   [clojure.string :as string]
   [util.santas-little-helpers :refer [parse-int read-input]]))
(def input (read-input "gjs/2022/day_4.txt"))

(defn parse-input
  [input]
  (map #(string/split % #",") input))

(defn string->range-set
  [s-vec]
  (let [parsed-sv (mapv #(string/split % #"-") s-vec)
        to-range-set (fn [[s1 s2]]
                       (set (range (parse-int s1)
                                   (inc (parse-int s2)))))]
    (mapv to-range-set parsed-sv)))

(comment
;;Solution pt 1
  (->> (parse-input input)
       (map string->range-set)
       (filter (fn [[s1 s2]]
                 (or (subset? s1 s2)
                     (subset? s2 s1))))
       count)

;;Solution pt 2
  (->> (parse-input input)
       (map string->range-set)
       (map (fn [[s1 s2]] (intersection s1 s2)))
       (filter seq)
       count))

