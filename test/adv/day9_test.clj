(ns adv.day9-test
  (:require [adv.day9 :as sut]
            [clojure.test :as t]
            [adv.util :as util]
            [clojure.string :as str]))

(defn parse [input]
  (mapv #(mapv util/->int (str/split % #"")) input))

(def sample (parse (util/lines "day9_sample.txt")))
(def puzzle (parse (util/lines "day9.txt")))

(t/deftest day9
  (t/is (= 15 (sut/part1 sample)))
  (t/is (= 502 (sut/part1 puzzle)))

  (t/is (= 3 (count (sut/flood-fill sample [0 0]))))
  (t/is (= 9 (count (sut/flood-fill sample [0 5]))))
  (t/is (= 14 (count (sut/flood-fill sample [1 2]))))
  (t/is (= 9 (count (sut/flood-fill sample [4 5]))))

  (t/is (= 1134 (sut/part2 sample)))
  (t/is (= 1330560 (sut/part2 puzzle))))

