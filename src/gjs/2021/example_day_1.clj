(ns gjs.2021.example-day-1
  (:require
   [util.santas-little-helpers :as slh]))

(def input (->> (slh/read-input "gjs/2021/day_1.txt")
                (map slh/parse-int)))

(defn delta
  [depth-pair]
  (let [d1 (first depth-pair)
        d2 (second depth-pair)]
    (cond
      (= d1 d2) 0
      (> d1 d2) -1
      (> d2 d1) 1)))
(defn make-deltas
  [input]
  (let [depth-pairs (partition 2 1 input)]
    (map delta depth-pairs)))

(def solution-1 (->> (make-deltas input)
                     (filter pos?)
                     count))

(comment
  solution-1)
