(ns advent-of-code-2020.day01.part1
  (:require [clojure.string :as s]))

(defn parse-input [input-location]
  (s/split-lines (slurp input-location)))

(defn input->int [input-location]
  (map read-string (parse-input input-location)))

(def input (input->int "src/advent_of_code_2020/day01/input.txt"))

(defn part1 [inp]
  (loop [i inp]
    (let [x (first inp)
          y (second i)]
      (cond
        (nil? y) (part1 (rest inp))
        (= (+ x y) 2020) (* x y)
        :else (recur (rest i))))))

;; (part1 input)
