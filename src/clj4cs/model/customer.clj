(ns clj4cs.model.customer)

; models servem para lógicas determinísticas
(defn customer-has-active-unused-card? [customer]
  (->> customer
       :customer/cards
       (filter #(and (nil? (:card/deactivated-at %))
                     (empty? (:card/transactions %))))
       seq))