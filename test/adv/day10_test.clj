(ns adv.day10-test
  (:require [adv.day10 :as s]
            [clojure.test :as t]
            [adv.util :as util]))

(def sample (util/lines "day10_sample.txt"))
(def puzzle (util/lines "day10.txt"))

(t/deftest day10
  (t/is (= 26397 (s/part1 sample)))
  (t/is (= 318099 (s/part1 puzzle)))
  (t/is (= 288957 (s/part2 sample)))
  (t/is (= 2389738699 (s/part2 puzzle))))
