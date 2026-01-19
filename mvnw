#!/usr/bin/env sh
BASEDIR=$(cd "$(dirname "$0")" && pwd)
MVNW_REPOURL="https://repo.maven.apache.org/maven2"
WRAPPER_JAR="$BASEDIR/.mvn/wrapper/maven-wrapper.jar"
PROPERTIES_FILE="$BASEDIR/.mvn/wrapper/maven-wrapper.properties"

if [ ! -f "$WRAPPER_JAR" ]; then
  echo "maven-wrapper.jar no está presente (normal si acabas de generar el proyecto)."
  echo "Genera el wrapper con: mvn -N wrapper:wrapper"
  exit 1
fi

exec java -jar "$WRAPPER_JAR" "$@"
