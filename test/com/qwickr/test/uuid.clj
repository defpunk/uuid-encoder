(ns com.qwickr.test.uuid
  (:require [clojure.test :refer :all]
  			[com.qwickr.uuid :refer :all])
)


(deftest test-encoded-longs
	"test encoding works for known values of uuids"
  (is (= "1111111111111111" (base58-encode-uuid (java.util.UUID. 0 0))))
  (is (= "1111111111111112" (base58-encode-uuid (java.util.UUID. 0 1))))
  (is (= "111111111111111z" (base58-encode-uuid (java.util.UUID. 0 57))))
  (is (= "11111111111111121" (base58-encode-uuid (java.util.UUID. 0 58))))
  )