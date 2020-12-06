(ns advent-of-code-2020.day05.part1
  (:require [clojure.string :as s]))

;; Convert the row, column and seat info into binary and then calculate the binary to an integer.
;; This works because FBFBBFFRLR converted to binary is 0101100 and 101
;; 0101100 converts to 44
;; 101 converts to 5
;; Adding them together gives the seat id. e.g.:
;; 0101100101 = 357

(defn binary
  [chars zero-char]
  (apply str (map #(if (= zero-char %) 0 1) chars)))

(defn binary-calc
  [binary-str]
  (Integer/parseInt binary-str 2))

(defn get-seat-info
  [boarding-pass]
  (let [row (binary (take 7 boarding-pass) \F)
        column (binary (take-last 3 boarding-pass) \L)
        seat-id (str row column)]
    {:row (binary-calc row) :column (binary-calc column) :seat-id (binary-calc seat-id)}))

(defn parse-input [input-location]
  (s/split-lines (slurp input-location)))

(defn part1
  [input]
  (apply max (map #(get (get-seat-info %) :seat-id) (parse-input input))))

(part1 "src/advent_of_code_2020/day05/input.txt")
