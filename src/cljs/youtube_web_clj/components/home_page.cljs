(ns youtube-web-clj.components.home-page
  (:require [youtube-web-clj.store :as store]
            [youtube-web-clj.components.header :refer [header]]
            [youtube-web-clj.components.video-list :refer [video-list]]
            [youtube-web-clj.components.loader :refer [loader]]))

(defn home-page []
  (let [state (store/get-state)]
    [:div.home-page
      [header (:query state)]
      [:div.home-page__content
        [video-list (:items state)]]
      [loader (:loading state)]]))
