(ns advent-of-code-2020.day06.part1
  (:require [clojure.string :as s]))

(defn parse-input
  "Split into groups based on empty newlines, split again based on single newlines for person"
  [input]
  (->> (s/split (slurp input) #"[\n]{2}")
       (map s/split-lines)))

(defn part1
  [input]
  (->> (parse-input input)
       (map #(apply str %))
       (map #(into #{} %))
       (map count)
       (reduce +)))

(part1 "src/advent_of_code_2020/day06/input.txt")
