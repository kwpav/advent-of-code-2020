(ns advent-of-code-2020.day03-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2020.day03.part1 :as part1]
            [advent-of-code-2020.day03.part2 :as part2]))

(deftest day03-test
  (testing "Day 3 solutions"
    (is (= 173 (part1/part1 (part1/parse-input "src/advent_of_code_2020/day03/input.txt"))))
    (is (= 4385176320 (part2/part2 (part2/parse-input "src/advent_of_code_2020/day03/input.txt"))))))
