(ns advent-of-code-2020.day10.part1
  (:require [clojure.string :as s]))

(defn adapter-diffs
  [adapters]
  (->> adapters
       (apply max)
       (+ 3)
       (conj adapters 0)
       sort
       (partition 2 1)
       (map (fn [[a b]] (- b a)))))

(defn parse-input
  [input]
  (->> (slurp input)
       (s/split-lines)
       (map #(Long/valueOf %))))

(defn part1
  [input]
  (let [diffs (adapter-diffs (parse-input input))]
    (* (count (filter #(= 1 %) diffs))
       (count (filter #(= 3 %) diffs)))))

(part1 "src/advent_of_code_2020/day10/input.txt")
