(ns advent-of-code-2020.day08.solution
  (:require [clojure.string :as s]))

(defn get-op
  [instruction]
  (first (keys instruction)))

(defn get-arg
  [instruction]
  (first (vals instruction)))

(defn exec-arg
  "Execute the argument given a value."
  [arg value]
  ((resolve (first arg)) value (second arg)))

(defn exec-instruction
  "Execute the instruction."
  [instruction state]
  (let [op (resolve (get-op instruction))
        arg (get-arg instruction)]
    (op arg state)))

(defn nop
  "Execute nop instruction."
  [arg state]
  {:loc (inc (:loc state))
   :acc (:acc state)
   :visited (conj (:visited state) (:loc state))})

(defn acc
  "Execute acc instruction."
  [arg state]
  {:loc (inc (:loc state))
   :acc (exec-arg arg (:acc state))
   :visited (conj (:visited state) (:loc state))})

(defn jmp
  "Execute jmp instruction."
  [arg state]
  {:loc (exec-arg arg (:loc state))
   :acc (:acc state)
   :visited (conj (:visited state) (:loc state))})

(defn repeat?
  "Has this line been visited?"
  [state]
  (contains? (:visited state) (:loc state)))

(defn oob?
  "Is the program out of bounds?"
  [program state]
  (not (<= 0 (:loc state) (count program))))

(defn eof?
  "Is the program at the end of the file?"
  [program state]
  (= (:loc state) (count program)))

(defn run-program
  "Run the program."
  [program]
  (loop [state {:loc 0 :acc 0 :visited #{}}]
    (cond (repeat? state)
          {:rpt (:acc state)}
          (oob? program state)
          {:hcf (:acc state)}
          (eof? program state)
          {:eof (:acc state)}
          :else (recur (exec-instruction (nth program (:loc state)) state)))))

(defn parse-instruction
  "Parses input to an instruction, e.g. acc +1 turns into {'acc ['+ 1]}."
  [input-line]
  (let [[op arg] (s/split input-line #" ")]
    (hash-map (symbol op)
              (vector (symbol (str (first arg)))
                      (Integer. (apply str (rest arg)))))))

(defn parse-input
  [input-location]
  (into [] (map parse-instruction (s/split-lines (slurp input-location)))))

(defn part1
  [input]
  (run-program (parse-input input)))

;; (part1 "src/advent_of_code_2020/day08/input.txt")

(defn switch-instruction
  [instruction]
  (let [op (get-op instruction)
        arg (get-arg instruction)]
    (cond (= op 'jmp)
          {'nop arg}
          (= op 'nop)
          {'jmp arg}
          :else instruction)))

(defn switch-until-eof
  "Bruteforce! Change one jmp/nop and run the program to see if it works."
  [program]
  (loop [loc 0]
    (let [new-program (update program loc switch-instruction)]
      (if (= new-program program)
        (recur (inc loc))
        (let [output (run-program new-program)
              haltcode (first (keys output))]
          (if (= haltcode :eof)
            output
            (recur (inc loc))))))))

(defn part2
  [input]
  (switch-until-eof (parse-input input)))

;; (part2 "src/advent_of_code_2020/day08/input.txt")
