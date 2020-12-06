(ns advent-of-code-2020.day05.part2
  (:require [advent-of-code-2020.day05.part1 :as part1]))

(defn find-seat
  [sorted-seats]
  (loop [missing-seats '() 
         remaining-seats sorted-seats]
    (if (empty? remaining-seats)
      missing-seats
      (recur (if (not (= (inc (first remaining-seats))
                         (second remaining-seats)))
               (cons (inc (first remaining-seats)) missing-seats)
               missing-seats)
             (rest remaining-seats)))))

(defn part2
  [input]
  (find-seat (sort (map #(get (part1/get-seat-info %) :seat-id) (part1/parse-input input)))))

(part2 "src/advent_of_code_2020/day05/input.txt")
