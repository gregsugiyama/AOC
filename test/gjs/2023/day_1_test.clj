(ns gjs.2023.day-1-test
  (:require
   [clojure.test :as t :refer [deftest testing is]]
   [gjs.2023.day-1 :as sut]))

(deftest default-test
  (testing "Get the calibration value of a string"
    (let [expected 12
          actual (sut/get-calibration-value "1abc2")]
      (is (= expected actual)))))

(comment
  (t/run-tests 'gjs.2023.day-1-test))
