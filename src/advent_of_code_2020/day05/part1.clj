(ns advent-of-code-2020.day05.part1
  (:require [clojure.string :as s]))

(defn parse-input [input-location]
  (s/split-lines (slurp input-location)))

(defn binary-space-calc
  [characters lower-char upper-char lower upper]
  (loop [remaining characters
         lower lower 
         upper upper]
    (if (empty? remaining) lower
        (let [change (int (Math/ceil (/ (- upper lower) 2)))]
          (cond (= lower-char (first remaining))
                (recur (rest remaining)
                       lower
                       (- upper change))
                (= upper-char (first remaining))
                (recur (rest remaining)
                       (+ lower change)
                       upper))))))

(defn calc-row
  [boarding-pass]
  (binary-space-calc (take 7 boarding-pass) \F \B 0 127))

(defn calc-column
  [boarding-pass]
  (binary-space-calc (take-last 3 boarding-pass) \L \R 0 7))

(defn calc-seat-id
  [row column]
  (+ (* row 8) column))

(defn get-seat-info
  [boarding-pass]
  (let [row (calc-row boarding-pass)
        column (calc-column boarding-pass)
        seat-id (calc-seat-id row column)]
    {:row row :column column :seat-id seat-id}))

(defn part1
  [input]
  (apply max (map #(get (get-seat-info %) :seat-id) (parse-input input))))

(part1 "src/advent_of_code_2020/day05/input.txt")
