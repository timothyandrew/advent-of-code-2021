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

(defn pmap-indexed [f coll]
  (pmap (fn [[a b]] (f a b)) (partition 2 (interleave (iterate inc 0) coll))))

(defn mmap [f coll]
  (map #(map f %) coll))
