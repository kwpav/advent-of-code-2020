(ns advent-of-code-2020.day06-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2020.day06.part1 :as part1]
            [advent-of-code-2020.day06.part2 :as part2]))

(deftest day06-test
  (testing "Day 6 solutions"
    (is (= 6775 (part1/part1 "src/advent_of_code_2020/day06/input.txt")))
    (is (= 3356 (part2/part2 "src/advent_of_code_2020/day06/input.txt")))))
