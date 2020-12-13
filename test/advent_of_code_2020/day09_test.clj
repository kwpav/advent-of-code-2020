(ns advent-of-code-2020.day09-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2020.day09.part1 :as part1]
            [advent-of-code-2020.day09.part2 :as part2]))

(deftest day09-test
  (testing "Day 9 solutions"
    (is (= 1124361034 (part1/part1 "src/advent_of_code_2020/day09/input.txt" 25)))
    (is (= 129444555(part2/part2 "src/advent_of_code_2020/day09/input.txt" 25)))))
