(ns advent-of-code-2020.day05
  (:require [clojure.test :refer :all]
            [advent-of-code-2020.day05.part1 :as part1]
            [advent-of-code-2020.day05.part2 :as part2]))

(deftest day05-test
  (testing "Day 5 solutions"
    (is (= 959 (part1/part1 "src/advent_of_code_2020/day05/input.txt")))
    (is (= 527 (second (part2/part2 "src/advent_of_code_2020/day05/input.txt"))))))
