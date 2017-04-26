(ns youtube-web-clj.store
  (:require-macros [cljs.core.async.macros :refer [go-loop go]])
  (:require [cljs.core.async :refer [<! >! chan]]
            [reagent.core :as r]
            [youtube-web-clj.reducer :refer [reducer]]))

;; TODO: refactor this staff to custom type (using deftype or what ever else).

(def state (r/atom {:loading false
                    :items []})) ;; Global application state
(def app-channel (chan)) ;; Channel with actions

(defn dispatch [action]
  (go
    (>! app-channel action)))

(defn get-state []
  @state)

(defn- app-loop [reducer] ;; Infinit app loop
  (go-loop []
    (when-let [action (<! app-channel)]
      (println ";; before state" state)
      (println ";; action" action)
      (reset! state (reducer @state action))
      (println ";; after state" state)
      (recur))))

(app-loop reducer)
