(ns flow-control)


(defn show-evens [coll]
 (if-let [evens (seq(filter even? coll))]
   (println (str "The evens are: " evens))
   (println "There are no evens")))

(defn str-binary-cond [n]
 (cond
   (= n 0) "zero"
   (= n 1) "one"
   :else "Unknown"))

(defn str-binary-condp [n]
  (condp = n
    0 "zero"
    1 "one"
    "Unknown"))

(defn str-binary-case [n]
  (case n
    0 "zero"
    1 "one"
    "Unknown"))

;; This one brakes tha stack
(defn factorial-stack-overflow
  ([n] (factorial 1 n))
  ([accum n]
    (if (zero? n)
      accum
      (factorial (*' accum n) (dec n)))))
;; here is a fix that uses recur
(defn factorial-recur
  ([n] (factorial 1 n))
  ([accum n]
   (if (zero? n)
     accum
     (recur (*' accum n) (dec n)))))