(defproject com.qwickr/uuid-encoder "0.1.0-SNAPSHOT"
  :description "Utiliities for encoding UUIDs. Version one will support base58 encoding"
  :url "https://github.com/defpunk/uuid-encoder"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :aot :all
  :dependencies [[org.clojure/clojure "1.7.0"]]


  :scm {:url "git@github.com:defpunk/uuid-encoder.git"}
  :pom-addition [:developers [:developer
                              [:name "David Oliver"]
                              [:url "http://qwickr.com"]
                              [:email "david.oliver@qwickr.com"]
                          ]]



  :deploy-repositories [
        ["releases" {:url "https://oss.sonatype.org/service/local/staging/deploy/maven2/" :creds :gpg}]
        ["snapshots" {:url "https://oss.sonatype.org/content/repositories/snapshots/" :creds :gpg}]]

  )
