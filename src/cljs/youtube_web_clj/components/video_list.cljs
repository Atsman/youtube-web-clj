(ns youtube-web-clj.components.video-list
  (:require [youtube-web-clj.components.video :refer [video]]))

(defn video-list [items]
  [:div.video-list
   (map #(-> [:div {:key (get-in % ["id" "videoId"])
                    :class "video-list__item"}
               (video %)])
        items)])
