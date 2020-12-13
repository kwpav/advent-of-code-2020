(ns advent-of-code-2020.day10.part2
  (:require [advent-of-code-2020.day10.part1 :as part1]))

(def count-arrangements
  (memoize
   (fn
     ([x] 1)
     ([x y & zs]
      (if (> (+ x y) 3)
        (apply count-arrangements y zs)
        (+ (apply count-arrangements y zs)
           (apply count-arrangements (+ x y) zs)))))))

(defn part2
  [input]
  (apply count-arrangements (part1/adapter-diffs (part1/parse-input input))))

(part2 "src/advent_of_code_2020/day10/input.txt")
