(ns gjs.2023.day-4
  (:require
   [clojure.string :as string]
   [util.santas-little-helpers :refer [read-input]]))

(def test-input
  '("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53"
    "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19"
    "Card 3: 1 21 53 59 44 | 69 82 63 72 16 21 14 1"
    "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83"
    "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36"
    "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"))

(def input (read-input "gjs/2023/day_4.txt"))

(defn parse-input
  [input]
  (map #(re-seq #"\d+" (last (string/split % #": "))) input))

(defn find-matches
  [numbers]
  (for [[k v] (group-by identity numbers)
        :when (> (count v) 1)]
    k))

(defn tally-points
  [number-of-matches]
  (loop [inst 1
         tally 1]
    (cond
      (= inst number-of-matches) tally
      :else                      (recur (inc inst) (* tally 2)))))

(def calculate-points
  (comp
   (map find-matches)
   (filter seq)
   (map (comp
         tally-points
         count))))

(comment
  ;; Part 1
  (transduce calculate-points + (parse-input input)))
