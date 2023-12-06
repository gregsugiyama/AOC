(ns gjs.2023.day-3
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(defn sum [ns]
  (reduce + 0 ns))

(defn indexed [xs]
  (map-indexed vector xs))

(defn space? [spot]
  (and spot
       (= \. spot)))
(defn digit? [spot]
  (and spot
       (<= (int \0) (int spot) (int \9))))

(defn sym? [spot]
  (and spot
       (not (space? spot))
       (not (digit? spot))))

(defn gear? [spot]
  (and spot
       (= \* spot)))

;; ----------------------------------------
;; parse input
(defn read-input [filename]
  (into {}
        (for [[y row] (indexed (str/split-lines (slurp (io/resource filename))))
              [x c] (indexed row)
              :when (not (space? c))]
          [[x y] c])))

(def full-input (read-input "gjs/2023/day_3.txt"))
(defn neighbors [[x y]]
  [[(dec x) (dec y)]
   [(dec x) y]
   [(dec x) (inc y)]
   [x (dec y)]
   [x (inc y)]
   [(inc x) (dec y)]
   [(inc x) y]
   [(inc x) (inc y)]])

(defn line-neighbors [[x y]]
  [[(dec x) y]
   [(inc x) y]])

(defn mark-connected [input start-points]
  (let [numbers (for [point start-points
                      neighbor (neighbors point)
                      :when (digit? (get input neighbor))]
                  neighbor)]
    (loop [to-visit (set numbers)
           marked (set numbers)]
      (if (empty? to-visit)
        marked
        (let [visit (first to-visit)
              digit-neighbors (for [neighbor (line-neighbors visit)
                                    :when (and (digit? (get input neighbor))
                                               (not (marked neighbor)))]
                                neighbor)]
          (recur (-> to-visit
                     (disj visit)
                     (into digit-neighbors))
                 (into marked digit-neighbors)))))))

(defn take-connected [in-points]
  (when (seq in-points)
    (loop [points (rest in-points)
           group [(first in-points)]]
      (let [this-point (first points)
            prev-point (last group)
            [px py] prev-point
            [nx ny] (or this-point [-1 -1])]
        (if (and (= (inc px) nx)
                 (= py ny))
          (recur (rest points)
                 (conj group this-point))
          group)))))

(defn group-points [points]
  (loop [points points
         groups []]
    (if (empty? points)
      groups
      (let [group (take-connected points)]
        (recur (drop (count group) points)
               (conj groups group))))))

(defn extract-numbers [input]
  (let [ordered-points (sort-by (fn [[x y]] [y x])
                                (keys input))
        groups (group-points ordered-points)]

    (for [group groups]
      (parse-long (apply str (map input group))))))

;; find all points in input that are a symbol
(defn symbol-points [input]
  (for [[point c] input
        :when (sym? c)]
    point))

(defn part1 [input]
  (let [filtered-input (select-keys input
                                    (mark-connected input (symbol-points input)))]
    (sum
     (extract-numbers filtered-input))))

(defn gear-points [input]
  (for [[point c] input
        :when (gear? c)]
    point))

(defn part2 [input]
  (sum
   (for [gear-point (gear-points input)
         :let [filtered-input (select-keys input
                                           (mark-connected input [gear-point]))
               numbers-on-gear (extract-numbers filtered-input)]
         :when (= 2 (count numbers-on-gear))
         :let [ratio (apply * numbers-on-gear)]]
     ratio)))

; solution belongs to
; https://gitlab.com/maximoburrito/advent2023/-/blob/main/src/day03/main.clj?ref_type=heads
; will try to understand later
