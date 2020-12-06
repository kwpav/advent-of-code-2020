(ns advent-of-code-2020.day06.part2
  (:require [advent-of-code-2020.day06.part1 :as part1]
            [clojure.set :as set]))

(defn part2
  [input]
  (->> (part1/parse-input input)
       (map (fn [i] (map #(into #{} %) i)))
       (map #(apply set/intersection %))
       (map count)
       (reduce +)))

(part2 "src/advent_of_code_2020/day06/input.txt")
