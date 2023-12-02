(ns gjs.2022.day-2
  (:require
   [clojure.string :refer [split]]
   [util.santas-little-helpers :refer [read-input]]))

(def input (->> (read-input "gjs/2022/day_2.txt")
                (map #(split % #"\s"))))

(def scores-pt-1 {["A" "X"] 4
                  ["A" "Y"] 8
                  ["A" "Z"] 3
                  ["B" "X"] 1
                  ["B" "Y"] 5
                  ["B" "Z"] 9
                  ["C" "X"] 7
                  ["C" "Y"] 2
                  ["C" "Z"] 6})

(def scores-pt-2 {["A" "X"] 3
                  ["A" "Y"] 4
                  ["A" "Z"] 8
                  ["B" "X"] 1
                  ["B" "Y"] 5
                  ["B" "Z"] 9
                  ["C" "X"] 2
                  ["C" "Y"] 6
                  ["C" "Z"] 7})

(defn solve
  [scores]
  (transduce (map #(get scores %)) + 0 input))

(comment

  (defn add-1 [n] (+ 1 n))

  (def foo (map add-1 [1 2 3 4 5]))

  (solve scores-pt-1)
  (solve scores-pt-2))
