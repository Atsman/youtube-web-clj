(ns youtube-web-clj.prod
  (:require [youtube-web-clj.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
