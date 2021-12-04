(ns adv.day3-test
  (:require [adv.day3 :as sut]
            [clojure.test :as t]
            [adv.util :as util]))

(t/deftest day3
  (t/is
   (= (sut/part1 ["00100" "11110" "10110" "10111" "10101" "01111" "00111"
                 "11100" "10000" "11001" "00010" "01010"])
      {:gamma 22 :epsilon 9}))

  (t/is
   (= (sut/part1 (util/lines "day3.txt"))
      {:epsilon 566, :gamma 3529}))

  (t/is
   (= (sut/part2 ["00100" "11110" "10110" "10111" "10101" "01111" "00111"
                  "11100" "10000" "11001" "00010" "01010"])
      [23 10]))

  (t/is
   (= (sut/part2 (util/lines "day3.txt"))
      [3573 289])))
