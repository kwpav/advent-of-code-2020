(ns advent-of-code-2020.day07.part2
  (:require [advent-of-code-2020.day07.part1 :as part1]
            [clojure.string :as s]
            [clojure.java.io :as io]))

;; rewrite parsei-nput to give this testdata
  ;; {:fadedblue [],
  ;;  :darkolive ([:fadedblue 3] [:dottedblack 4]),
  ;;  :lightred ([:brightwhite 1] [:mutedyellow 2]),
  ;;  :mutedyellow ([:shinygold 2] [:fadedblue 9]),
  ;;  :dottedblack [],
  ;;  :vibrantplum ([:fadedblue 5] [:dottedblack 6]),
  ;;  :darkorange ([:brightwhite 3] [:mutedyellow 4]),
  ;;  :brightwhite ([:shinygold 1]),
  ;;  :shinygold ([:darkolive 1] [:vibrantplum 2])})

(defn remove-empty
  [coll]
  (filter #(seq %) coll))

(defn create-color-amt
  [i]
  (let [color (keyword (str (second i) (second (rest i))))
        amt (Integer. (first i))]
    [color amt]))

(defn create-can-contain
  [contains-strings]
  (if (= "no other" (first contains-strings))
    []
    (let [spc-split-rules (map #(s/split % #" ") contains-strings)]
      (map create-color-amt spc-split-rules))))

(defn create-bag-rule
  [input-line]
  (let [rules-list (remove-empty (map s/trim (s/split input-line #"contain|bags|bag|[,.]")))]
    (hash-map (keyword (s/join "" (take 2 (s/split (first rules-list) #" "))))
              (create-can-contain (rest rules-list)))))

(defn parse-input [input-location]
  (into {} (map create-bag-rule (s/split-lines (slurp input-location)))))

(defn count-all-containing-bags
  "bfs that counts amount of contained bags in given bagname"
  [bag-rules bagname visited level]
  (let [raw-neighbors (bagname bag-rules)
        neighbors (map first raw-neighbors)]
    (if (empty? raw-neighbors)
      0
      (apply + 
             (for [n raw-neighbors]
               (+ (second n) (* (second n)
                                (count-all-containing-bags bag-rules
                                                           (first n)
                                                           (into visited neighbors)
                                                           (inc level)))))))))

(defn part2
  [input]
  (count-all-containing-bags (parse-input input) :shinygold  #{} 0))

(part2 "src/advent_of_code_2020/day07/input.txt")
