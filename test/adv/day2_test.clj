(ns adv.day2-test
  (:require [adv.day2 :as sut]
            [clojure.test :as t]
            [adv.util :as util]))

(t/deftest day2
  (t/is
   (=
    (sut/part1 ["forward 5" "down 5" "forward 8" "up 3" "down 8" "forward 2"])
    {:horiz 15 :depth 10}))

  (t/is
   (=
    (sut/part1 (util/lines "day2.txt"))
    {:horiz 1991 :depth 911}))

  (t/is
   (=
    (sut/part2 ["forward 5" "down 5" "forward 8" "up 3" "down 8" "forward 2"])
    {:horiz 15 :depth 60 :aim 10}))

  (t/is
   (=
    (sut/part2 (util/lines "day2.txt"))
    {:horiz 1991 :depth 984716 :aim 911})))
