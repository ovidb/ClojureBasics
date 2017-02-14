(ns namespaces-examples
  (:require [clojure.set :as s])
  (:use [clojure.java.io :only (delete-file)]))

(defn do-union [& sets]
  (apply s/union sets))c