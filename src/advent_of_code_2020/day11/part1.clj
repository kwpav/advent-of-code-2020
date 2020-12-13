(ns advent-of-code-2020.day11.part1
  (:require [clojure.string :as s]))

(defn occupied-seat?
  [seat]
  (= seat \#))

(defn empty-seat?
  [seat]
  (= seat \L))

(defn seat?
  [seat]
  (or (occupied-seat? seat) (empty-seat? seat)))

(defn floor?
  [seat]
  (= seat \.))

(defn count-adjacent
  [row]
  0)

(defn switch-seat
  [seat]
  (cond (empty-seat? seat) \#
        (= (count-adjacent seat) 4) \L
        :else seat))

(defn change-seats
  [row]
  (apply str (map switch-seat row)))

(defn do-round
  [seats]
  (map change-seats seats))

(defn parse-input
  [input]
  (->> (slurp input)
       (s/split-lines)))

(defn part1
  [input]
  (do-round (parse-input input)))

(part1 "src/advent_of_code_2020/day11/testinput.txt")
