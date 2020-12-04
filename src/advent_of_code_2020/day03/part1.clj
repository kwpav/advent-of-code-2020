(ns advent-of-code-2020.day03.part1
  (:require [clojure.string :as s]))

(defn parse-input [input-location]
  (s/split-lines (slurp input-location)))

(defn expand-forest [forest-line]
  (str forest-line forest-line))

(defn tree? [position forest]
  (if (>= (get position :x) (count (first forest)))
    (tree? position (conj (rest forest)
                          (expand-forest (first forest))))
    (= \# (nth (first forest)
               (get position :x)))))

(defn part1 [input]
  (loop [position {:x 0 :y 0}
         forest input
         trees-hit 0]
    (if (>= (get position :y) (count input))
      trees-hit
      (recur {:x (+ 3 (get position :x))
              :y (+ 1 (get position :y))}
             (rest forest)
             (if (tree? position forest)
               (inc trees-hit)
               trees-hit)))))

(part1 (parse-input "src/advent_of_code_2020/day03/input.txt"))
