(ns youtube-web-clj.api
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs.core.async :refer [>! chan]]
            [ajax.core :refer [GET]]))

(def SEARCH_ENDPOINT "https://www.googleapis.com/youtube/v3/search")
(def ACCESS_TOKEN "AIzaSyDmryK2JLebbgKAW42wer7Me9_vIAsra6w")

(defn- calc-total-pages [data]
  (-> 
    (/ (data "totalResults")
       (data "resultsPerPage"))
    Math/ceil))

(defn- transform-results [data]
  {:next-page-token (data "nextPageToken")
   :total-pages (calc-total-pages (data "pageInfo"))
   :items (data "items")})

(defn search 
  ([query] 
   (search query 15 nil))
  ([query limit] 
   (search query limit nil))
  ([query limit page-token]
   (let [ch (chan 1)]
    (GET SEARCH_ENDPOINT {:params {:q query
                                   :key ACCESS_TOKEN
                                   :type "video"
                                   :part "snippet"
                                   :maxResults limit
                                   :pageToken page-token}
                          :handler #(go (>! ch {:data (transform-results %)}))
                          :error-handler #(go (>! ch {:err %}))})
    ch)))
