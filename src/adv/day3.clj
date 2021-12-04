(ns adv.day3
  (:require [clojure.string :as str]))

(defn- bin->n [b]
  (Integer/parseInt b 2))

(defn- bool->bin [bool]
  (->> bool
       (map #(case % true "1" false "0"))
       str/join
       bin->n))

(defn- bin-digit [b i]
  (-> b
      (bit-shift-right i)
      (bit-and 1)))

(defn- gamma-blueprint [coll i]
  (for [n coll
                :let [n (bin-digit n i)]]
            n))

(defn- gamma-bit [coll i]
  (reduce + (gamma-blueprint coll i)))

(defn- bit-comp [n i]
  (-> n bit-not (bit-and (dec (bit-shift-left 1 i)))))

(defn part1 [coll]
  (let [i (-> coll first count)
        coll (map bin->n coll)
        gamma (->> (range i)
                   (map #(gamma-bit coll %))
                   (map #(> % (/ (count coll) 2)))
                   reverse
                   bool->bin)
        epsilon (bit-comp gamma i)]
    {:gamma gamma :epsilon epsilon}))

(defn- sieve [coll i op]
  (if (= 1 (count coll))
    coll
    (let [gamma (gamma-bit coll i)]
      (if (op gamma (/ (count coll) 2))
        (sieve (filter #(= (bin-digit % i) 1) coll) (dec i) op)
        (sieve (filter #(= (bin-digit % i) 0) coll) (dec i) op)))))

(defn part2 [coll]
  (let [i (-> coll first count)
        coll (map bin->n coll)]
    [(first (sieve coll (dec i) >=))
     (first (sieve coll (dec i) <))]))
