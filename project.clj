(defproject integritycontrol "0.1.0-SNAPSHOT"
	:description "TESS Integrity Control"
	:repositories [
		["mvn.repo" "http://www.mvnrepository.com/artifacts"]
		["spaces.repo" "http://maven-repository.openspaces.org"]]
	:dependencies [
		[org.clojure/clojure "1.5.1"]
		[com.gigaspaces/gs-openspaces "9.0.2"]]
	:profiles {:user {:local-repo "/home/vu/.m2/repository"}}
	:source-paths ["src"]
	:test-paths ["src"]
	:resource-paths ["resources"]
	:java-source-paths ["src"]
	:main control)

