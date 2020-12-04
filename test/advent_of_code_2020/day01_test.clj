(ns advent-of-code-2020.day01-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2020.day01.part1 :as part1]
            [advent-of-code-2020.day01.part2 :as part2]))

(deftest day01-test
  (testing "Day 1 solutions"
    (is (= 618144 (part1/part1 part1/input)))
    (is (= 173538720 (part2/part2 part2/input)))))
