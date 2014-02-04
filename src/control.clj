(ns control
	(:require
		[clojure.test :refer :all]
		[clojure.xml :refer [parse]])
	(:use clojure.test)
	(:gen-class
		:name ControlListener
		:prefix class-listener-
		:implements [integritycontrol.BridgeListener]
		:methods [[onEvents [String] void]]))

(defn class-listener-onEvents [event]
	(println (str "=============>" event)))

(defn xmlid-to-id [xmlid]
	(let [attrs (:attrs xmlid)]
		[(:domain attrs) (first (:content xmlid)) (:version attrs)]))

(defn extract-inventory-ids [inventory-xml-source]
	(->>
		(parse inventory-xml-source)
		(xml-seq)
		(first)
		(:content)
		(map xmlid-to-id)))

(defn extract-inventory-ids-lisp-style [inventory-xml-source]
	(map xmlid-to-id
		(:content
				(first (xml-seq (parse inventory-xml-source))))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;TESTS;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def test-inventory-ids [["FO" "111" "1"] ["FO" "222" "2"]])

(deftest should-extract-ids-from-inventory-message
	(is
		(= ["FO" "111" "1"] (xmlid-to-id {:tag :id, :attrs {:domain "FO", :version "1"}, :content ["111"]})))
	(is
		(= test-inventory-ids (extract-inventory-ids (clojure.java.io/file "resources/inventory-message-example.xml")))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;AUXILIARY;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn jmethods "shows methods declaration for given class" [clazz]
	(doseq [m (.getDeclaredMethods clazz)] (println m)))

(defn runtests "reloads module and runs all tests" []
	(do (use 'control :reload-all) (clojure.test/run-tests 'control)))
