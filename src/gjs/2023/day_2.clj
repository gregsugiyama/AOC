(ns gjs.2023.day-2
  (:require
   [clojure.string :as string]
   [util.santas-little-helpers :refer [read-input]]))

(def input
  (->>
   (read-input "gjs/2023/day_2.txt")
   (map #(string/split % #":|(;)"))
   (map (fn [ln]
          (let [game-id (Integer/parseInt (re-find #"\d+" (first ln)))
                game    (map #(partition 2 2 %) (map #(re-seq #"\d+|red|blue|green" %) (rest ln)))]
            [game-id (map (fn [rounds]
                            (reduce (fn [acc round]
                                      (assoc acc (keyword (last round)) (Integer/parseInt (first round)))) {} rounds)) game)])))))

;;------------------- Part 1 ----------------
(def rules {:red 12 :green 13 :blue 14})

(defn xform [rules]
  (comp
   (map (fn [[id rounds]]
          (let [invalid-round (remove (fn [m]
                                        (every? (fn [[k v]]
                                                  (<= v (get rules k))) m)) rounds)]
            [id invalid-round])))
   (filter #(empty? (last %)))
   (map first)))

;;----------------- Part 2 --------------------

(def xform2 (comp
             (map #(apply merge-with max (last %)))
             (map #(reduce (fn [acc [_ v]]
                             (* acc v)) 1 %))))

(comment
  ;; Solution Part 1
  (transduce (xform rules) + input)

  ;; Solution Part 2
  (transduce xform2 + input))

