(ns advent-of-code-2020.day01.part2
  (:require [clojure.string :as s]))

(defn parse-input [input-location]
  (s/split-lines (slurp input-location)))

(defn input->int [input-location]
  (map read-string (parse-input input-location)))

(def input (input->int "src/advent_of_code_2020/day01/input.txt"))

(defn part2 [inp]
  (for [x inp
        y (rest inp)
        z (rest (rest inp))]
    (when (= (+ x y z) 2020)
      (* x y z))))

(first (filter identity (part2 input)))
