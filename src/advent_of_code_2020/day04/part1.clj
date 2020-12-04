(ns advent-of-code-2020.day04.part1
  (:require [clojure.string :as s]))

(defn parse-input
  [input-location]
  (s/split-lines (slurp input-location)))

(def required-fields
  ["byr"
   "iyr"
   "eyr"
   "hgt"
   "hcl"
   "ecl"
   "pid"])

(defn get-passports
  [input]
  (loop [passport-strs []
         passport-str (first input)
         input (rest input)]
    (cond
      (empty? input) passport-strs
      (empty? (first input))
      (recur (into passport-strs (list (str passport-str)))
             ""
             (rest input))
      (seq input)
      (recur passport-strs
             (if (empty? passport-str)
               (first input)
               (str passport-str " " (first input)))
             (rest input)))))

(defn unify-passport
  [key-value]
  {:key (first key-value)
   :value (second key-value)})

(defn parse-passport
  "Parse passport into map with :key and :value"
  [passport]
  (map unify-passport (map #(s/split % #":") (s/split passport #" "))))

(defn valid?
  [passport]
  (let [keys (into #{} (map :key passport))
        required (filter #(contains? keys %) required-fields)]
    (= (count required)
       (count required-fields))))

(defn part1
  [input]
  (let [passports (map parse-passport (get-passports input))]
    (count (filter valid? passports))))

(part1 (parse-input "src/advent_of_code_2020/day04/input.txt"))
