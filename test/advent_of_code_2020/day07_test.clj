(ns advent-of-code-2020.day07-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2020.day07.part1 :as part1]
            [advent-of-code-2020.day07.part2 :as part2]))

(deftest day06-test
  (testing "Day 7 solutions"
    (is (= 296 (part1/part1 "src/advent_of_code_2020/day07/input.txt")))
    (is (= 9339 (part2/part2 "src/advent_of_code_2020/day07/input.txt")))))
