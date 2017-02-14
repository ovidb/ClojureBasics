# 1 Introduction

This is a document to help me through a journey to create new stuff with Clojure

## Literals

```clojure
42              ; Long
6.022e23        ; Double

42N             ; BigInt
1.0M            ; BigDecimal
22/7            ; Ratio

"hello"         ; String
\e              ; Character

true false      ; boolean
nil             ; null
+ Fred *bob*    ; Symbols *mutable* also known as mothears
:key :word      ; Keywords

```

## Data structures
```clojure
(4 :key 3.0)      ; List
[2 "string" 99]   ; Vector
{:one 1, :two 2}  ; Map, come is whitespace
{:one 1 :two 2}   ; Map without coma works too
#{unique member}  ; Set
```

## Meta data

```clojure
(with-metadata [1 2 3] {:numbers true})
;; => [1 2 3]
;; 
(meta (with-metadata [1 2 3] {:numbers true}))
;; => {:numbers true}
```

## Reader macros

| Reader Macros   | Expansion                     |
| ---             | ---                           |
| 'foo            | `(quote foo)`                 |
| #'foo           | `(var foo)`                   |
| @foo            | `(deref foo)`                 |
| #(+ % 5)        | `(fn [x] (+ x 5)`             |
| ^{:key val} foo | `(with-meta foo {:key val})`  |
| ^key foo        | `(with-meta foo {:key true})` |
  

## REPL Utilities

``

```clojure
;; First we need to import the clojure.repl
(use clojure.repl)

;;
;; Then we can ask for more documentation
;; 

(doc when)
;; => 
;; -------------------------
;; clojure.core/when
;; ([test & body])
;; Macro
;;  Evaluates test. If logical true, evaluates body in an implicit do.
```

### You could easily ask for
- `(find-doc "sequence")` all that matches sequence
- `(apropos "map)` a list of all the definitions that have map in their name
- `(source take)` see the source code for a function
- `(dir clojure.repl)` prints all available definitions.

## Leiningen directory structure

| Path        | Purpose                   |
| ---         | ---                       |
| project.clj | Project/build config      |
| classes/    | Compiled bytecode         |
| lib/        | Dependent JARs            |
| public/     | HTML/CSS/JS files for web |
| src/        | Clojure source            |
| test/       | Unit tests                |

## Maven directory structure

| Path              | Purpose              |
| ---               | ---                  |
| pom.xml           | Project/build config |
| target/classes    | Compiled bytecode    |
| ~/.m2/repository/ | Dependent JARs       |
| src/main/clojure  | Clojure source       |
| src/test/clojure  | Unit test            |

## Summary
 
![Summary](./docs/assets/module1-summary.png "Module 1 Summary")

# 2 Functions

- are first-class abstration
- can be stored, passed as argument, invoked
- fn creates a function with named parameters and body

## Anonymous Functions
```clojure
;;   params       body
;;  ---------   -------------
(fn [message] (print message)) 
```
- fn makes _anonymous_ functions
- can be invoked the same way as in JS

```clojure
( (fn [message] (print message)) ; Operation
) "Hello world"                  ; Arguments
;; => Hello world!
```
 Here the "Hello world" is passed as an argument to the anonymous function which is then printed as a message

## Naming Functions
```clojure
;; long version 
(def messenger (fn [msg] (print msg)))
;;=> #'user/messenger
;; short version 
(defn messenger [msg] (print msg))
;; invoking the function
(messenger "Hello world!")
;;=> hello world=> nil
```

## Let
- bind symbols to immutable values
- values may be literals or expressions
- bound symbols are available in lexical scope
```clojure
(defn messenger [msg]
  (let [a 7
        b 5
        c (capitalize msg)]
    (println a b c)
  ) ; end of let scope
) ; end of function
```
## Multi-arity functions
- can overload function by arity
  - Arity: number of arguments
- each arity is a list: ([args*] body*)
- one arity can invoke another

```clojure
(defn messenger
  ([] (messenger "Hello World!"))
  ([msg] (print msg)))
;;

(messenger)                       ;;=> Hello world
(messenger "Some other messeage") ;;=> Some other messaage

```

> Think of arity as a way of providing default arguments

## Variatic functions

- Variatic: function of indefinite arguments
  - Only one version of a variatic function is allowed when overloading on arity
- & symbol in params, instructs clojure a variadic is coming
  - next param collects all remaining args (0 or more)
  - colected args represented as sequence
  
```clojure
;; can receive 0 or n of [who] arguments which becomes a list
(defn messenger [greeting & who]                            
  (print greeting who))

(messenger "Hello" "world" "class")
;;=> Hello (world class) 
;; List----^-----------^
```
## Apply
- invokes function on arguments from a sequence
- final argument is a sequence
- "unpacks" remaining arguments from sequence

```clojure
(let [a 1
      b 2
      more `(3 4)]
  (apply messenger a b more))
;; this invokes (messenger 1 2 3 4) => 1 (2 3 4)
```

> To better see the relationship between apply and variadic functions

```clojure
(defn messenger [greeting & who]
  (apply print greeting who) ;; unpacking who
;;
(messenger "Hello" "world" "class")
;;=> Helo world class
```

## Clojures
- fn "closes" over surrounding lexical scope
  - creates a clojure
- closed-over references persist beyond lexical scope

```clojure
(defn messenger-builder [greeting] ; returns an anonymous function
  (fn [who] (print greeting who))) ; which closes over/remebers/captures the greeting symbol

;; greeting provided here, then goes out of scope
(def hello-er (messenger-builder "Hello"))

;; greeting still available because hello-er is a closure
(hello-er "world!")
;;=> Hello world
```

## Invoking Java Code
| Task            | Java              | Clojure          |
| Instatiation    | new Widget("Foo") | (Widget. "foo")  |
| Instance method | rnd.nextInt()     | (.nextInt rnd)   |
| Instance field  | object.field      | (.-field object) |
| Static method   | Math.sqrt(25)     | (Math/sqrt 25)   |
| Static field    | Math.PI           | Math/PI          |

## Chaining access

| Language      | Syntax                             |
| Java          | person.getAddress().getZipCode()   |
| Clojure       | (.getZipCode (.getAddress person)) |
| Clojure Sugar | (.. person getAddress getZipCode)  |

## Java Method vs Functions
- Java methods are not Clojure functions
- Can't store them, pass them as arguments
- Can wrap them in functions when necessary

```clojure
;; make a function to invoke .length on arg
(fn [obj] (.length obj))
```

## Terse fn reader macro

- Terse form #() short fns defined inline
  - Single argument: %
  - Multiple args: %1, %2, %3, ..
  - Variadic: %& for remaining args
  
```clojure
;; a function to invoke .length on arg
#(.length %)
```

![Summary](./docs/assets/module2-summary.png "Module 2 Summary")