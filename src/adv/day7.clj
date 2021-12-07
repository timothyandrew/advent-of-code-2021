(ns adv.day7
  (:require [adv.util :as util]))

(defn- fixed-cost [a b]
  (Math/abs (- a b)))

(defn- variable-cost [a b]
  (let [n (fixed-cost a b)]
    (/ (* n (inc n)) 2)))

(defn- calc [positions cost-fn]
  (let [results
        (pmap #(reduce + %)
                     (for [n (range (apply min positions) (inc (apply max positions)))]
                       (map 
                        #(cost-fn n %)
                        positions)))]
    (apply min results)))

(defn part1 [positions] (calc positions fixed-cost))
(defn part2 [positions] (calc positions variable-cost))
