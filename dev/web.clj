(ns web
  (:require 
    [org.httpkit.server :refer [run-server]]
    [ring.middleware.params :refer [wrap-params]]
    [ring.middleware.keyword-params :refer [wrap-keyword-params]]
    [old-saml20-clj.sp :as saml-sp]
    [old-saml20-clj.xml :as saml-xml]
    [old-saml20-clj.shared :as saml-shared]
    [old-saml20-clj.routes :as saml-routes]
    [hiccup.core :as hiccup]
    [hiccup.page :refer [html5]]
    [hiccup.util :refer [escape-html]]
    [helmsman])
  (:gen-class))

(defn basic-page [content]
  (html5
  [:html
   [:head
    [:title "Blank page"]]
   [:body
    [:pre content]]]))

(defn wrap-app-state
  [config]
  (assoc config :handler
         (helmsman/create-ring-handler
          (into
           [[wrap-params]
            [wrap-keyword-params]]
           (saml-routes/helmsman-routes config)))))

(defn start!
  [state]
  (assoc
    state :http-server
    (run-server
      (:handler state)
      {:port 8080})))

(defn stop!
  [state]
  ((:http-server state))
  (dissoc state :http-server :handler))

