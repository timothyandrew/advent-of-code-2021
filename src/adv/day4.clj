(ns adv.day4)
 
(defn transpose [m]
  (apply mapv vector m))

(defn unmarked-sum [board]
  (->> board
       (filter #(not (:marked %)))
       (map :value)
       (reduce +)))

(defn- board-complete? [board]
  (let [rows (partition 5 board)
        cols (transpose rows)]
    (or
     (some #(every? :marked %) rows)
     (some #(every? :marked %) cols))))

(defn- apply-draw [boards draw]
  (for [board boards]
    (map #(if (= (:value %) draw)
            {:value draw :marked true}
            %)
         board)))

(defn part1 [draws boards]
  (loop [draws draws boards boards]
    (let [draw (first draws)
          boards (apply-draw boards draw)]
      (if (or (empty? draws) (some board-complete? boards))
        [(->> boards
              (filter board-complete?)
              first
              unmarked-sum) draw]
        (recur (next draws) boards)))))


(defn part2 [draws boards]
  (loop [draws draws boards boards last-completed nil]
    (let [draw (first draws)
          boards (apply-draw boards draw)]
      (cond
        (empty? draws) last-completed

        (some board-complete? boards)
        (if (= (count boards) 1)
          [(-> boards first unmarked-sum) draw]
          (let [completed (first (filter board-complete? boards))]
            (recur (next draws) (remove #(board-complete? %) boards) [completed draw])))

        :else (recur (next draws) boards last-completed)))))
