(ns adv.util
  (:require  [clojure.test :as t]
             [clojure.java.io :as io]
             [clojure.string :as str]))

(defn lines [filename]
  (-> filename
      io/resource
      slurp
      (str/split #"\n")))

(defn ->int [n]
  (Integer/parseInt n))

(defn safe-inc [n]
  (inc (or n 0)))

(defn mean [coll]
  (let [sum (apply + coll)
        count (count coll)]
    (if (pos? count)
      (/ sum count)
      0)))

(defn median [coll]
  (let [sorted (sort coll)
        cnt (count sorted)
        halfway (quot cnt 2)]
    (if (odd? cnt)
      (nth sorted halfway) ; (1)
      (let [bottom (dec halfway)
            bottom-val (nth sorted bottom)
            top-val (nth sorted halfway)]
        (mean [bottom-val top-val]))))) ; (2)
