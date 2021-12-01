(ns adv.day1)

(defn day1-part1 [input]
  (->> input
       (partition 2 1)
       (filter (fn [[a b]] (> b a)))
       count))

(defn day1-part2 [input]
  (->> input
       (partition 3 1)
       (map #(apply + %))
       day1-part1))
