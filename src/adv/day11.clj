(ns adv.day11
  (:require [adv.util :as util]))

(defn- adj [board [x y]]
  (let [width (-> board first count dec)
        height (-> board count dec)

        left (if (= y 0) nil (get-in board [x (dec y)]))
        top-left (if (or (= y 0) (= x 0)) nil (get-in board [(dec x) (dec y)]))
        bottom-left (if (or (= y 0) (= x height)) nil (get-in board [(inc x) (dec y)]))
        right (if (= y width) nil (get-in board [x (inc y)]))
        top-right (if (or (= y width) (= x 0)) nil (get-in board [(dec x) (inc y)]))
        bottom-right (if (or (= y height) (= x height)) nil (get-in board [(inc x) (inc y)]))
        top (if (= x 0) nil (get-in board [(dec x) y]))
        down (if (= x height) nil (get-in board [(inc x) y]))]

    (remove nil? [left right top down top-left top-right bottom-left bottom-right])))

(defn- tick [board]
  (->> board
       (util/mmap inc)))
