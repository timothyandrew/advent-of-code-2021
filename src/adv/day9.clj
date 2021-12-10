(ns adv.day9)

(defn- adj-coord [heightmap [x y]]
  (let [width (-> heightmap first count dec)
        height (-> heightmap count dec)

        left (if (= y 0) nil [x (dec y)])
        right (if (= y width) nil [x (inc y)])
        top (if (= x 0) nil [(dec x) y])
        down (if (= x height) nil [(inc x) y])]
    (remove nil? [left right top down])))

(defn- adj [heightmap [x y]]
  (let [width (-> heightmap first count dec)
        height (-> heightmap count dec)

        left (if (= y 0) nil (get-in heightmap [x (dec y)]))
        right (if (= y width) nil (get-in heightmap [x (inc y)]))
        top (if (= x 0) nil (get-in heightmap [(dec x) y]))
        down (if (= x height) nil (get-in heightmap [(inc x) y]))]
    (remove nil? [left right top down])))

(defn flood-fill
  "All points in the basin that [x y] is contained in"
  [heightmap [x y]]
  (let [seen (atom #{})
        impl (fn impl [heightmap [x y]]
               (let [around (adj-coord heightmap [x y])
                     around (remove #(= 9 (get-in heightmap %)) around)
                     around (remove #(contains? @seen %) around)]
                 (swap! seen conj [x y])
                 (cons [x y] (mapcat #(impl heightmap %) around))))]
    (set (impl heightmap [x y]))))

(defn- low-point? [n adj]
  (every? #(< n %) adj))

(defn part2 [heightmap]
  (let [seen (atom #{})]
    (->>
     (for [x (range (count heightmap))
           y (range (count (first heightmap)))
           :let [basin (flood-fill heightmap [x y])]
           :when (and
                  (not= 9 (get-in heightmap [x y]))
                  (not (contains? @seen [x y])))]
       (do
         (doseq [location basin] (swap! seen conj location))
         (count basin)))
     sort
     reverse
     (take 3)
     (reduce *))))

(defn part1 [heightmap]
  (->>
   (for [x (range (count heightmap))
         y (range (count (first heightmap)))
         :let [n (get-in heightmap [x y])]]
     [n (adj heightmap [x y])])
   (filter #(apply low-point? %))
   (map first)
   (map inc)
   (reduce +)))
