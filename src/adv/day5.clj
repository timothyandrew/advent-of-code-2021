(ns adv.day5
  (:require [adv.util :as util]))

(defn- range-pos [a b]
  (if (> a b)
    (range a (dec b) -1)
    (range a (inc b))))

(defn- line->points [line]
  (cond
    (= (:x1 line) (:x2 line)) (for [y (range-pos (:y1 line) (:y2 line))] {:x (:x1 line) :y y})
    (= (:y1 line) (:y2 line)) (for [x (range-pos (:x1 line) (:x2 line))] {:x x :y (:y1 line)})
    :else (for [[x y] (partition 2 (interleave (range-pos (:x1 line) (:x2 line)) (range-pos (:y1 line) (:y2 line))))]
            {:x x :y y})))

(defn- mark-line [board line]
  (reduce #(update %1 %2 util/safe-inc) board (line->points line)))

(defn part1 [lines]
  (->> lines
       (filter #(or (= (:x1 %) (:x2 %)) (= (:y1 %) (:y2 %))))
       (reduce mark-line {})
       vals
       (filter #(>= % 2))
       count))

(defn part2 [lines]
  (->> lines
       (reduce mark-line {})
       vals
       (filter #(>= % 2))
       count))
