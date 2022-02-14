(ns clj4cs-practice.repl)

(comment
  ; fizzbuzz com modulos
  (mod 9 3)
  (mod 5 5)
  (mod 7 5)

  ; 0 é um valor! vai ser sempre truthy
  (when (mod 5 5) "buzz")
  (when (mod 7 5) "buzz")

  ; testar com zero? traz um booleano para testar
  (zero? (mod 15 3))
  (zero? (mod 15 5))

  ; com o booleano, podemos tomar decisões
  (when (zero? (mod 5 5)) "buzz")
  (when (zero? (mod 3 5)) "fizz")

  ; pra ficar mais fácil de testar, podemos extrair os valores para bindings mais simples
  (let [n 15
        mult3 (zero? (mod n 3))
        mult5 (zero? (mod n 5))]
    (cond                                                   ; cond simplifica a estrutura de decisão
      (and mult3 mult5) "fizzbuzz"
      mult3 "fizz"
      mult5 "buzz"))

  ; agora é só extrair pra uma defn e testar mais facilmente
  (defn fizzbuzz [n]
    (let [mult3 (zero? (mod n 3))
          mult5 (zero? (mod n 5))]
      (cond
        (and mult3 mult5) "fizzbuzz"
        mult3 "fizz"
        mult5 "buzz")))

  ; com esses testes, podemos deixá-los automatizados
  (= (fizzbuzz 3) "fizz")
  (nil? (fizzbuzz 4))
  (= (fizzbuzz 5) "buzz")
  (= (fizzbuzz 15) "fizzbuzz")
  )

; agora é 'so deixar a defn aqui e apagar os comentários
(defn fizzbuzz [n]
  (let [mult3 (zero? (mod n 3))
        mult5 (zero? (mod n 5))]
    (cond
      (and mult3 mult5) "fizzbuzz"
      mult3 "fizz"
      mult5 "buzz")))