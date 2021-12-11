(ns adv.day10
  (:require [clojure.string :as str]
            [adv.util :as util]))

(defn- is-open? [char]
  (contains? #{"(" "[" "{" "<"} char))

(defn- is-close? [char]
  (contains? #{")" "]" "}" ">"} char))

(defn- invert  [char]
  (case char
    "(" ")"
    "[" "]"
    "{" "}"
    "<" ">"
    ")" "("
    "]" "["
    "}" "{"
    ">" "<"))

(defn- parse-line [line]
  (let [line (str/split line #"")
        stack (atom '())
        error (atom false)]
    
    (dorun
     (for [char line
           :when (not @error)]
       (do
         
         (cond
           (is-open? char) (swap! stack conj char)
           (is-close? char) (if (= (peek @stack) (invert char))
                              (swap! stack pop)
                              (swap! error #(identity %2) char))))))

    @error))

(defn- complete-line [line]
  (let [line (str/split line #"")
        stack (atom '())]
    
    (dorun
     (for [char line]
       (cond
         (is-open? char) (swap! stack conj char)
         (is-close? char) (swap! stack pop))))

    (->> @stack
        (map invert))))

(defn- syntax-score [char]
  (case char
    ")" 3
    "]" 57
    "}" 1197
    ">" 25137))

(defn- autocomplete-score [line]
  (let [table {")" 1 "]" 2 "}" 3 ">" 4}]
    (reduce #(+ (* 5 %1) (get table %2)) 0 line)))

(defn part1 [lines]
  (->> lines
       (map parse-line)
       (remove #(= false %))
       (map syntax-score)
       (reduce +)))

(defn part2 [lines]
  (->> lines
       (filter #(= false (parse-line %)))
       (map complete-line)
       (map autocomplete-score)
       util/median))
