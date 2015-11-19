(ns com.qwickr.uuid)

  (gen-class
    :main false
     :name com.qwickr.EncodedUUID
     :prefix java-
     :methods [^:static [base58EncodedRandomUUID [] String] 
               ^:static [base58EncodedUUID [java.util.UUID] String] 
               ^:static [base58EncodedUUIDFromString [String] String] 
              ])

  ;; The Bitcoin base58 alphabet
(def code-string "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz")
 
(defn int-to-base58 [num leading-zeros]
  "Encodes an integer into a base58 string."
  (loop [acc []
         n num]
    (if (pos? n)
      (let [i (rem n 58)
            s (nth code-string i)]
        (recur (cons s acc)
               (quot n 58)))
      (apply str (concat
                  (repeat leading-zeros (first code-string))
                  acc)))))
 
(defn encode [bytes]
  "Encodes a byte array into a base58 string."
  (let [leading-zeros (->> bytes
                       (take-while zero?)
                       count)
        n (java.math.BigInteger. 1 (byte-array bytes))]
    (int-to-base58 n leading-zeros)))


(defn- uuid [] (java.util.UUID/randomUUID))

(defn- uuid-to-byte-array [x]
  (let [bb (java.nio.ByteBuffer/wrap (byte-array 16))]
    (.putLong bb (.getMostSignificantBits x))
    (.putLong bb (.getLeastSignificantBits x))
    (.array bb)))

(defmulti base58-encode-uuid class)

(defmethod base58-encode-uuid java.lang.String [x]
  (base58-encode-uuid (java.util.UUID/fromString x)))

(defmethod base58-encode-uuid java.util.UUID [x]
  (encode (uuid-to-byte-array x)))

(defn base58-encoded-uuid 
	([] (let [u (uuid)] (base58-encode-uuid u)))
	([x] (base58-encode-uuid x))
	)


(defn- java-base58EncodedRandomUUID
	"creates a random UUID and returns the base58 encoded string representation" [] 
  (base58-encoded-uuid)
	)

(defn- java-base58EncodedUUID
  "given a UUID and returns the base58 encoded string representation" [x]
  (base58-encoded-uuid x)
  )

(defn- java-base58EncodedUUIDFromString
  "given a string representation of a UUID returns the base58 encoded string representation" [x] 
  (base58-encoded-uuid x)
  )
