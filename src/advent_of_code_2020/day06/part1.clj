(ns advent-of-code-2020.day06.part1
  (:require [clojure.string :as s]
            [clojure.set :as set]))

(defn parse-input
  "Split into groups based on empty newlines, split again based on single newlines for person"
  [input]
  (map #(s/split % #"[\n]") (s/split (slurp input) #"[\n]{2}")))

(defn part1
  [input]
  (let [answers (map #(apply str %) (parse-input input))]
    (reduce + (map count (map #(into #{} %) answers)))))

(part1 "src/advent_of_code_2020/day06/input.txt")
