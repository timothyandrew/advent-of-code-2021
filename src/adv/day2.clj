(ns adv.day2
  (:require [adv.util :as util]))

(defn parse-command [s]
  (let [[_ comm n] (re-matches #"^(\S+)\s(\d+)$" s)]
    {:comm (keyword comm) :n (util/->int n)}))

(defn- tick [{horiz :horiz depth :depth} {comm :comm n :n}]
  (case comm
    :forward {:horiz (+ horiz n) :depth depth}
    :down {:horiz horiz :depth (+ depth n)}
    :up {:horiz horiz :depth (- depth n)}))

(defn- tick2 [{horiz :horiz depth :depth aim :aim :as state} {comm :comm n :n}]
  (case comm
    :forward {:horiz (+ horiz n) :depth (+ depth (* aim n)) :aim aim}
    :down (update state :aim #(+ % n))
    :up (update state :aim #(- % n))))

(defn part1 [commands]
  (let [state {:horiz 0 :depth 0}
        commands (map parse-command commands)]
    (reduce tick state commands)))

(defn part2 [commands]
  (let [state {:horiz 0 :depth 0 :aim 0}
        commands (map parse-command commands)]
    (reduce tick2 state commands)))
