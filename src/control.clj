(ns control
	(:use clojure.test)
	(:gen-class
		:name ControlListener
		:prefix class-listener-
		:implements [integritycontrol.BridgeListener]
		:methods [[onEvents [String] void]]))

(defn class-listener-onEvents [event]
	(println (str "=============>" event)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;TESTS;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftest test-eq
	(is (= 2 2)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;AUXILIARY;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn jmethods "shows methods declaration for given class" [clazz]
	(doseq [m (.getDeclaredMethods clazz)] (println m)))

(defn runtests "reloads module and runs all tests" []
	(do (use 'control :reload-all) (clojure.test/run-tests 'control)))
