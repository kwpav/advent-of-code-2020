(ns advent-of-code-2020.day02.part1
  (:require [clojure.string :as s]))

(defn parse-input [input-location]
  (s/split-lines (slurp input-location)))

(def input (parse-input "src/advent_of_code_2020/day02/input.txt"))

(defn count-password-letters [policy-letter password]
  (count (filter #(= policy-letter %) (seq password))))

(defn in-range? [policy-range letter-count]
  (let [split-range (s/split policy-range #"-")
        lower (Integer. (nth split-range 0))
        upper (Integer. (nth split-range 1))]
    (and (<= lower letter-count)
         (>= upper letter-count))))

(defn valid? [inp]
  (let [split-inp (s/split inp #" ")
        policy-range (nth split-inp 0)
        policy-letter (first (seq (nth split-inp 1)))
        password (nth split-inp 2)]
    (in-range? policy-range (count-password-letters policy-letter password))))

(defn part1 [inp]
  (count (filter valid? inp)))
