(ns advent-of-code-2020.day09.part2
  (:require [advent-of-code-2020.day09.part1 :as part1]))

(defn sum-smallest-largest
  [numbers]
  (+ (apply min numbers) (apply max numbers)))

(defn find-numbers
  "Find a contiguous set of numbers adding up to given value."
  [numbers value]
  (loop [start numbers
         remaining numbers
         current-set []]
    (let [current (first remaining)
          sum (reduce + current-set)]
      (cond (= sum value)
            current-set
            (> sum value)
            (recur (rest start) (rest start) [])
            (< sum value)
            (recur start (rest remaining) (conj current-set current))))))

(defn index-of [e coll] (first (keep-indexed #(if (= e %2) %1) coll)))

(defn part2
  [input size]
  (let [answer (part1/part1 input size)
        numbers (part1/parse-input input)
        idx (index-of answer numbers)]
    (sum-smallest-largest (find-numbers (take idx numbers) answer))))

(part2 "src/advent_of_code_2020/day09/input.txt" 25)
