(ns youtube-web-clj.reducer)

(defn search-reducer [state {query :query}]
  (let [loading (not (= (:query state) query))] ;; show load indicator only if query changes
    (assoc state :query query
                 :loading loading)))

(defn search-done-reducer [state {data :data}]
  (merge state {:loading false} data))

(defn reducer [state action]
  (case (:type action)
    :SEARCH_ACTION (search-reducer state action)
    :SEARCH_DONE_ACTION (search-done-reducer state action)
    state))
