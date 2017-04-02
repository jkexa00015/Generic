#!/bin/bash
mvn archetype:generate -DarchetypeCatalog=local -DgroupId=com.jk -DartifactId=RESTfulExample -DarchetypeGroupid=com.kohls.bigdata.omnich -DarchetypeArtifactId=of-kpi-apt-archetype -DinteractiveMode=false


-DarchetypeCatalog=local

org.apache.maven.archetypes:of-kpi-apt-archetype:1.0

mvn archetype:generate -B -DarchetypeCatalog=local -DarchetypeGroupId=com.kohls.bigdata.omnich -DarchetypeArtifactId=of-kpi-apt-archetype -DarchetypeVersion=1.0.0-SNAPSHOT -DgroupId=com.company -DartifactId=project -Dversion=1.0-SNAPSHOT -Dpackage=com.company.project
