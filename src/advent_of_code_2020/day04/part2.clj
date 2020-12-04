(ns advent-of-code-2020.day04.part2
  (:require [clojure.string :as s])
  (:require [advent-of-code-2020.day04.part1 :as part1]))

(defn year-valid?
  [year lower upper]
  (<= lower (Integer. year) upper))

(defn byr-valid?
  [byr]
  (year-valid? byr 1920 2002))

(defn iyr-valid?
  [iyr]
  (year-valid? iyr 2010 2020))

(defn eyr-valid?
  [eyr]
  (year-valid? eyr 2020 2030))

(defn hgt-valid?
  [hgt]
  (let [hgt (re-matches #"([\d]+)(cm|in)" hgt)
        type (second (rest hgt))]
    (cond (empty? hgt) false
          (= "in" type) (<= 59 (Integer. (second hgt)) 76)
          (= "cm" type) (<= 150 (Integer. (second hgt)) 193)
          :else false)))

(defn hcl-valid?
  [hcl]
  (re-matches #"#[0-9a-f]{6}" hcl))

(defn ecl-valid?
  [ecl]
  (let [valid-colors
        #{"amb" "blu" "brn" "gry" "grn" "hzl" "oth"}]
    (contains? valid-colors ecl)))

(defn pid-valid?
  [pid]
  (re-matches #"[0-9]{9}" pid))

(defn required-fields?
  [passport]
  (let [fields (into #{} (keys passport))
        required-fields #{:byr :iyr :eyr :hgt :hcl :ecl :pid}]
    (= (count (filter #(contains? fields %) required-fields))
       (count required-fields))))

(defn valid?
  [passport]
  (and (required-fields? passport)
       (byr-valid? (get passport :byr))
       (iyr-valid? (get passport :iyr))
       (eyr-valid? (get passport :eyr))
       (hgt-valid? (get passport :hgt))
       (hcl-valid? (get passport :hcl))
       (ecl-valid? (get passport :ecl))
       (pid-valid? (get passport :pid))))

(defn unify-passport
  [key-value]
  {(keyword (first key-value))
   (second key-value)})

(defn parse-passport
  "Parse passport into map with :key and value"
  [passport]
  (into {} (map unify-passport (map #(s/split % #":") (s/split passport #" ")))))

(defn part2
  [input]
  (let [passports (map parse-passport (part1/get-passports (part1/parse-input input)))]
    (count (filter valid? passports))))

(part2 "src/advent_of_code_2020/day04/input.txt" )
