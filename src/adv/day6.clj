(ns adv.day6
  (:require [adv.util :as util]))

(defn- tick [state _]
  (let [state (map (fn [[k v]] [(dec k) v]) state)
        state (into {} state)
        spawn (get state -1 0)]
    (-> state
        (update 6 #(+ (or % 0) spawn))
        (update 8 #(+ (or % 0) spawn))
        (dissoc -1))))

(defn part1 [state n]
  (reduce tick (frequencies state) (range n)))
