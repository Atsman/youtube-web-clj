(ns youtube-web-clj.components.loader)

(defn loader [display?]
  (if display?
    [:div.loader-container
      [:div.loader]]
    [:div]))
