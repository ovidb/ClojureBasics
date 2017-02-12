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

| Reader Macros   | Expansion                   |
| ---             | ---                         |
| 'foo            | (quote foo)                 |
| #'foo           | (var foo)                   |
| @foo            | (deref foo)                 |
| #(+ % 5)        | (fn [x] (+ x 5)             |
| ^{:key val} foo | (with-meta foo {:key val})  |
| ^key foo        | (with-meta foo {:key true}) |
  

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