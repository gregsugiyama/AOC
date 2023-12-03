(ns gjs.2023.day-1
  (:require
   [util.santas-little-helpers :refer [read-input]]))

(def input (read-input "gjs/2023/day_1.txt"))

(def number-strings {"one"   1
                     "two"   2
                     "three" 3
                     "four"  4
                     "five"  5
                     "six"   6
                     "seven" 7
                     "eight" 8
                     "nine"  9})

(defn parse-string-number
  [s]
  (if (every? #(Character/isDigit %) s)
    s
    (number-strings s)))

(def regex-pt-1 #"(\d)")

(def regex-pt-2 #"(?=(\d|one|two|three|four|five|six|seven|eight|nine))")

(defn xform [re]
  (comp
   (map #(re-seq re %))
   (map #(map (comp parse-string-number second) %))
   (map (juxt first last))
   (map (comp parse-long (partial apply str)))))

(comment
  (map #(re-seq regex-pt-2 %) input)
  ;; Solution Part 1
  (transduce (xform regex-pt-1) + input)

  ;; Solution Part 2 
  (transduce (xform regex-pt-2) + input))
