(ns youtube-web-clj.doo-runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [youtube-web-clj.core-test]))

(doo-tests 'youtube-web-clj.core-test)
