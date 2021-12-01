(ns adv.util
  (:require  [clojure.test :as t]
             [clojure.java.io :as io]
             [clojure.string :as str]))

(defn lines [filename]
  (-> filename
      io/resource
      slurp
      (str/split #"\n")))

(defn ->int [n]
  (Integer/parseInt n))
