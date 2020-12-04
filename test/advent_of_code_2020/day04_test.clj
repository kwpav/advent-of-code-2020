(ns advent-of-code-2020.day04-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2020.day04.part1 :as part1]
            [advent-of-code-2020.day04.part2 :as part2]))

(deftest day04-test
  (testing "Day 4 solutions"
    (is (= 2 (part1/part1 (part1/parse-input "src/advent_of_code_2020/day04/testinput.txt"))))
    (is (= 200 (part1/part1 (part1/parse-input "src/advent_of_code_2020/day04/input.txt"))))
    (is (= 116 (part2/part2 "src/advent_of_code_2020/day04/input.txt")))))
