(ns advent-of-code-2020.day10-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2020.day10.part1 :as part1]
            [advent-of-code-2020.day10.part2 :as part2]))

(deftest day10-test
  (testing "Day 10 solutions"
    (is (= 2048 (part1/part1 "src/advent_of_code_2020/day10/input.txt")))
    (is (= 1322306994176 (part2/part2 "src/advent_of_code_2020/day10/input.txt")))))
