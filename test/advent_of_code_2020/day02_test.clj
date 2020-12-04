(ns advent-of-code-2020.day02-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2020.day02.part1 :as part1]
            [advent-of-code-2020.day02.part2 :as part2]))

(deftest day02-test
  (testing "Day 2 solutions"
    (is (= 600 (part1/part1 part1/input)))
    (is (= 245 (part2/part2 part2/input)))))
