# Learning clojure

This is a document to help me through my journey to create new stuff with this language

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
  

## 

First we need to bring clojure.repl

`(use clojure.repl)`

```clojure
(doc when)
;; => 
```