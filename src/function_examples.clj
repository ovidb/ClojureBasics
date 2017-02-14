(ns function-examples)

;; Local context

(let [x 10
      y 20]
  (+ x y))

;; Multiple arity

(defn messenger
  ([] (messenger "Hello World!"))
  ([msg] (print msg)))

;; Withouth arguments
(messenger)
;;=> Hello world

(messenger "Some other messeage")
;;=> Some other messaage

;; Variadic functions

(defn messenger [greeting & who]                            ;; can receive 0 or n who arguments which becomes a list
  (print greeting who))

(messenger "Hello" "world" "class")
;;=> Hello (world class)


;; Apply

(let [a 1
      b 2
      more `(3 4)]
  (apply messenger a b more))
;; this invokes (messenger 1 2 3 4) => 1 (2 3 4)

;; Messenger with apply

(defn messenger [greeting & who]
  (apply print greeting who))

(messenger "Hello" "world" "class")
;;=> Helo world class


;; Clojures

(defn messenger-builder [greeting]                          ; returns an anonymous function
  (fn [who] (print greeting who)))                          ; closes over greeting

;; greeting provided here, then goes out of scope
(def hello-er (messenger-builder "Hello"))

;; greeting still available because hello-er is closure
(hello-er "world!")
;;=> Hello world

