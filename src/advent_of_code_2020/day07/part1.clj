(ns advent-of-code-2020.day07.part1
  (:require [clojure.string :as s]))

(defn remove-empty
  [coll]
  (filter #(seq %) coll))

(defn create-can-contain
  [contains-strings]
  (if (= "no other" (first contains-strings))
    []
    (let [spc-split-rules (map #(s/split % #" ") contains-strings)]
      (map #(hash-map :amt (first %)
                      :name (str (second %) " " (second (rest %)))) spc-split-rules))))

(defn create-bag-rule
  [input-line]
  (let [rules-list (remove-empty (map s/trim (s/split input-line #"contain|bags|bag|[,.]")))]
    (hash-map :name (s/trim (first rules-list))
              :can-contain (create-can-contain (rest rules-list)))))

(defn parse-input [input-location]
  (map create-bag-rule (s/split-lines (slurp input-location))))

(def testdata
  [{:name "light red" :can-contain
    [{:name "bright white" :amt 1} {:name "muted yellow" :amt 2}]}
   {:name "bright white" :can-contain
    [{:name  "shiny gold" :amt 1}]}
   {:name "dark orange" :can-contain
    [{:name "bright white" :amt 3}
     {:name "muted yellow" :amt 4}]}
   {:name "muted yellow" :can-contain
    [{:name "shiny gold" :amt 2}
     {:name "faded blue" :amt 9}]}
   {:name "shiny gold" :can-contain
    [{:name "dark olive" :amt 1}
     {:name "vibrant plum" :amt 2}]}
   {:name "dark olive" :can-contain
    [{:name "faded blue" :amt 3}
     {:name "dotted black" :amt 4}]}
   {:name "vibrant plum" :can-contain
    [{:name "faded blue" :amt 6}
     {:name "dotted black"  :amt 6}]}
   {:name "faded blue" :can-contain []}
   {:name "dotted black" :can-contain []}])

(defn can-contain-bag?
  [bag-rule bag-type]
  (let [can-contain (map #(% :name) (get bag-rule :can-contain))]
    (some #{bag-type} can-contain)))

(defn get-containing-bags
  [bag-rules name]
  (into #{} (map #(% :name) (filter #(can-contain-bag? % name) bag-rules))))

(defn get-all-containing-bags
  [bag-rules bag-name]
  (loop [look-up-names [bag-name]
         visited #{}
         holds #{}]
    (if (empty? look-up-names)
      holds
      (let [hlds (get-containing-bags bag-rules (first look-up-names))]
        (recur (concat (filter #(not (contains? visited %)) hlds)
                       (rest look-up-names))
               (conj visited (first look-up-names))
               (into holds hlds))))))

(defn part1
  [input]
  (count (get-all-containing-bags (parse-input input) "shiny gold")))

(part1 "src/advent_of_code_2020/day07/input.txt")
