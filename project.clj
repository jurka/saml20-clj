(defproject jurka/old-saml20-clj "0.1.17"
  :description "Basic SAML 2.0 library for SSO."
  :url "https://github.com/jurka/saml20-clj"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :source-paths ["src"]
  :dependencies [[clj-time "0.15.2"]
                 [compojure "1.6.1" :exclusions [ring/ring-core]]
                 [org.apache.santuario/xmlsec "2.1.4"]
                 [org.clojure/clojure "1.10.1"]
                 [org.clojure/data.codec "0.1.1"]
                 [org.clojure/data.xml "0.0.8"]
                 [org.clojure/data.zip "0.1.3"]
                 [org.opensaml/opensaml "2.6.4"]
                 [org.vlacs/helmsman "1.0.0"]]
  :jvm-opts ["-Djdk.xml.entityExpansionLimit=2000"
             "-Djdk.xml.totalEntitySizeLimit=100000"
             "-Djdk.xml.maxParameterEntitySizeLimit=10000"
             "-Djdk.xml.maxElementDepth=1000"]
  :pedantic :warn
  :profiles {:dev {:source-paths ["dev" "test"]
                   :dependencies [[org.clojure/tools.namespace "0.3.1"]
                                  [org.clojure/tools.nrepl "0.2.13"]
                                  [hiccup "1.0.5"]
                                  [http-kit "2.3.0"]]}}
  :repositories [["releases" {:url "https://clojars.org/repo"
                              :sign-releases false
                              :username :env/clojars_username
                              :password :env/clojars_password}]]
  :release-tasks [["vcs" "assert-committed"]
                  ["change" "version" "leiningen.release/bump-version" "release"]
                  ["vcs" "commit"]
                  ["vcs" "tag" "--no-sign"]
                  ["change" "version" "leiningen.release/bump-version"]
                  ["vcs" "commit"]
                  ["vcs" "push"]])
