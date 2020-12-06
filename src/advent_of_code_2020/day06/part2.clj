(ns advent-of-code-2020.day06.part2
  (:require [advent-of-code-2020.day06.part1 :as part1]
            [clojure.set :as set]))

(defn part2
  [input]
  (let [answers (map (fn [i] (map #(into #{} %) i)) (part1/parse-input input))]
    (reduce + (map count (map #(apply set/intersection %) answers)))))

(part2 "src/advent_of_code_2020/day06/input.txt")
