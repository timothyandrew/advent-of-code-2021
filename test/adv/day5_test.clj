(ns adv.day5-test
  (:require [adv.day5 :as sut]
            [clojure.test :as t]
            [adv.util :as util]))

(def sample (util/lines "day5_sample.txt"))
(def puzzle (util/lines "day5.txt"))

(defn parse [coll]
  (->> coll
       (map (fn [s]
              (let [parsed (re-matches #"^(\d+),(\d+)\D+(\d+),(\d+)$" s)
                    parsed (drop 1 parsed)
                    [x1 y1 x2 y2] (map util/->int parsed)]
                {:x1 x1 :y1 y1 :x2 x2 :y2 y2})))))

(t/deftest day5
  (t/is (= (sut/part1 (parse sample)) 5))
  (t/is (= (sut/part1 (parse puzzle)) 4421))

  (t/is (= (sut/part2 (parse sample)) 12))
  (t/is (= (sut/part2 (parse puzzle)) 18674)))
