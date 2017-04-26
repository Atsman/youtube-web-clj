(ns youtube-web-clj.handler
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.route :refer [not-found resources]]
            [hiccup.page :refer [include-js include-css html5]]
            [youtube-web-clj.middleware :refer [wrap-middleware]]
            [config.core :refer [env]]))

(def mount-target
  [:div#app
   [:h3 "ClojureScript has not been compiled!"]
   [:p "please run "
    [:b "lein figwheel"]
    " in order to start the compiler"]])

(defn head []
  [:head
   [:meta {:charset "utf-8"}]
   [:meta {:name "viewport"
           :content "width=device-width, initial-scale=1"}]
   (include-css "https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.min.css")
   (include-css "https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css")
   (include-css "https://fonts.googleapis.com/css?family=Roboto")
   (include-css "/css/index.css")])

(defn loading-page []
  (html5
   (head)
   [:body
    mount-target
    (include-js "/js/app.js")]))

(defroutes routes
  (GET "/" [] (loading-page))
  (GET "/about" [] (loading-page))

  (resources "/")
  (not-found "Not Found"))

(def app (wrap-middleware #'routes))
