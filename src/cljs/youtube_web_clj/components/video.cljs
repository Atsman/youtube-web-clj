(ns youtube-web-clj.components.video)

(defn video-url [id]
  (str "https://www.youtube.com/watch?v=" id))

(defn video [item]
  [:div {:class "video"}
   [:div {:class "video__preview"}
    [:img {:src (get-in item ["snippet" "thumbnails" "medium" "url"])
           :class "video__image"}]
    [:a {:href (video-url (get-in item ["id" "videoId"]))
         :class "video__title"} (get-in item ["snippet" "title"])]]
   [:div {:class "video__info"}
    [:a {:class "video__author"} (get-in item ["snippet" "channelTitle"])]
    [:ul {:class "video__meta-info"}
     [:li "198,000 views"]
     [:li {:class "video__meta-date"} (get-in item ["snippet" "publishedAt"])]]
    [:div {:class "video__description"} (get-in item ["snippet" "description"])]]])
