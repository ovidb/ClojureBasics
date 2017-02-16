(ns destructuring-examples)

(def fruits ["Bananna" "Apple" "Orange"])

(let [[first-fruit] fruits] first-fruit)

(let [[_ & favourites]fruits] favourites)

(defn favourites-fruites [[_ & favourites-fruites]]
  favourites-fruites)

