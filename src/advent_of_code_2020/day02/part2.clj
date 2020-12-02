(ns advent-of-code-2020.day02.part2
  (:require [clojure.string :as s]))

(defn parse-input [input-location]
  (s/split-lines (slurp input-location)))

(def input (parse-input "src/advent_of_code_2020/day02/input.txt"))

(defn count-password-letters [policy-letter password]
  (count (filter #(= policy-letter %) password)))

(defn in-position? [policy-range policy-letter password]
  (let [pw (seq password)
        split-range (s/split policy-range #"-")
        lower (- (Integer. (nth split-range 0)) 1)
        upper (- (Integer. (nth split-range 1)) 1)]
    (= 1 (count-password-letters policy-letter
                                 [(nth pw lower) (nth pw upper)]))))

(defn valid? [inp]
  (let [split-inp (s/split inp #" ")
        policy-range (nth split-inp 0)
        policy-letter (first (seq (nth split-inp 1)))
        password (nth split-inp 2)]
    (in-position? policy-range policy-letter password)))

(defn part2 [inp]
  (count (filter valid? inp)))

(part2 input)
