(ns gjs.2021.example-day-1-test
  (:require
   [clojure.test :as t :refer [deftest testing is]]
   [gjs.2021.example-day-1 :as sut]))

(def input '(199 200 208 210 200 207 240 269 260 263))

(def output '(1 1 1 -1 1 1 1 -1 1))

(deftest make-deltas-test
  (testing "output should contain seven '1' & two '-1'"
    (let [expected output
          actual (sut/make-deltas input)]
      (is (= expected actual)))))

(comment
  (t/run-tests 'gjs.2021.example-day-1-test))
