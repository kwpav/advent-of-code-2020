(ns advent-of-code-2020.day03.part2
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

(defn traverse-map [input right down]
  (loop [position {:x 0 :y 0}
         forest input
         trees-hit 0]
    (if (>= (get position :y) (count input))
      trees-hit
      (recur {:x (+ right (get position :x))
              :y (+ down (get position :y))}
             (if (= 2 down)
               (rest (rest forest))
               (rest forest))
             (if (tree? position forest)
               (inc trees-hit)
               trees-hit)))))

(defn part2 [input]
  (* (traverse-map input 1 1)
     (traverse-map input 3 1)
     (traverse-map input 5 1)
     (traverse-map input 7 1)
     (traverse-map input 1 2)))

(part2 (parse-input "src/advent_of_code_2020/day03/input.txt"))
