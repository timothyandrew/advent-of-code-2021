(ns adv.day4-test
  (:require [adv.day4 :as sut]
            [clojure.test :as t]
            [adv.util :as util]
            [clojure.string :as str]))

(def sample (util/lines "day4_sample.txt"))
(def puzzle (util/lines "day4.txt"))

(defn parse [input]
  (let [draws (map util/->int (-> input first (str/split #",")))
        input (drop 1 (remove #(= "" %) input))
        boards (for [board (partition 5 input)]
                 (->> board
                      (map #(-> %
                                (str/replace #"\s+" " ")
                                str/trim
                                (str/split #" ")))
                      flatten
                      (map util/->int)
                      (map #(assoc {} :marked false :value %))))]
    [draws boards]))

(t/deftest day4
  (let [[draws boards] (parse sample)]
    (t/is (= (sut/part1 draws boards)
             [188 24]))

    (t/is (= (sut/part2 draws boards)
             [148 13])))

  (let [[draws boards] (parse puzzle)]
    (t/is (= (sut/part1 draws boards)
             [982 32]))

    (t/is (= (sut/part2 draws boards)
             [281 82]))))
