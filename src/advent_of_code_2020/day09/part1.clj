(ns advent-of-code-2020.day09.part1
  (:require [clojure.string :as s]))

(defn get-all-sums
  "Get all possible sums from the previous numbers in a sequence."
  [numbers]
  (loop [sums #{} remaining numbers]
    (if (empty? remaining)
      sums
      (recur (into sums (map #(+ (first remaining) %) (rest remaining)))
             (rest remaining)))))

(defn find-invalid-num
  [input preamble size]
  (loop [loc size
         valid (get-all-sums preamble)
         numbers input]
    (let [current (first (drop loc input))]
      (if (not (contains? valid current))
        current
        (recur (inc loc)
               (get-all-sums (take size (rest numbers)))
               (rest numbers))))))

(defn parse-input
  [input]
  (->> (slurp input)
       (s/split-lines)
       (map #(Long/valueOf %))))

(defn part1
  [input size]
  (let [inp (parse-input input)]
    (find-invalid-num inp (take size inp) size)))

(part1 "src/advent_of_code_2020/day09/input.txt" 25)
