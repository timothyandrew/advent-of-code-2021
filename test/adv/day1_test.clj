(ns adv.day1-test
  (:require [adv.day1 :as sut]
            [clojure.test :as t]
            [adv.util :as util]
            [clojure.java.io :as io]))

(t/deftest day1
  (t/is (= (sut/day1-part1 [199 200 208 210 200 207 240 269 260 263]) 7))
  (t/is (= (sut/day1-part2 [199 200 208 210 200 207 240 269 260 263]) 5))

  (let [input (->> "day1.txt" util/lines (map util/->int))]
    (t/is (= (sut/day1-part1 input)
             1451))

    (t/is (= (sut/day1-part2 input)
             1395))))
