(ns youtube-web-clj.components.header
  (:require [reagent.core :as r]
            [youtube-web-clj.store :as store]
            [youtube-web-clj.actions :as actions]))

(defn- logo []
  [:div {:class "logo"}
    [:a {:href "#"} "YouTube"
        [:span "Finder"]]])

(defn- input [on-change]
  [:input {:class "search__input", 
           :type "text", 
           :placeholder "Search", 
           :name "Search"
           :on-change on-change}])

(defn- on-submit-handler 
  [event state on-submit]
  (.preventDefault event)
  (on-submit state))

(defn- form [query on-submit]
  (let [state (r/atom "")]
    (fn []
      [:form {:class "search__form" :on-submit #(on-submit-handler % @state on-submit)}
        [input {:value @state :on-change #(reset! state (->> % .-target .-value))}]
        [:button {:class "search__button", 
                  :name "Search"}
          [:i {:class "search__search-ico fa fa-search", 
               :aria-hidden "true"}]]])))

(defn header [query]
  [:header {:class "header"}
    [:section {:class "header__search"}
      [logo]
      [form query #(store/dispatch (actions/search %))]]])
