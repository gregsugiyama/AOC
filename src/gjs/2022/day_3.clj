(ns gjs.2022.day-3
  (:require
   [clojure.data :as data]
   [clojure.set :as set]
   [util.santas-little-helpers :refer [read-input]]))

(def test-input '("vJrwpWtwJgWrhcsFMMfFFhFp"
                  "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL"
                  "PmmdzqPrVvPwwTWBwg"
                  "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn"
                  "ttgJtRGJQctTZtZT"
                  "CrZsJsPPZsGzwwsLwLmpwMDw"))

(def input (read-input "gjs/2022/day_3.txt"))
(def priority ["a"
               "b"
               "c"
               "d"
               "e"
               "f"
               "g"
               "h"
               "i"
               "j"
               "k"
               "l"
               "m"
               "n"
               "o"
               "p"
               "q"
               "r"
               "s"
               "t"
               "u"
               "v"
               "w"
               "x"
               "y"
               "z"
               "A"
               "B"
               "C"
               "D"
               "E"
               "F"
               "G"
               "H"
               "I"
               "J"
               "K"
               "L"
               "M"
               "N"
               "O"
               "P"
               "Q"
               "R"
               "S"
               "T"
               "U"
               "V"
               "W"
               "X"
               "Y"
               "Z"])

(defn find-priority
  [s]
  (some
   (fn [i]
     (when (some? i)
       i))
   (map-indexed (fn [idx p]
                  (when (= s p)
                    (inc idx)))
                priority)))
(defn compartmentalize
  [backpack]
  (partition (/ (count backpack) 2) backpack))

(defn parse-input
  [string-coll]
  (map compartmentalize string-coll))

(comment
  ;; Solution pt 1

  (reduce
   (fn [acc s]
     (+
      acc
      (find-priority (str s))))
   0
   (reduce (fn [acc coll]
             (concat
              acc
              (last (data/diff
                     (set (first coll))
                     (set (last coll))))))
           []
           (parse-input input)))

  ;; Solution pt 2

  (->> input
       (partition 3)
       (reduce
        (fn [acc backpack-coll]
          (concat
           acc
           (apply set/intersection (map set backpack-coll))))
        [])
       (map #(find-priority (str %)))
       (apply +)))


