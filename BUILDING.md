# Building

## Requirements
- Java 17
- Internet access for Gradle dependencies

## Default build (API only)
./gradlew clean build

## Build all modules
./gradlew -PincludeMods=true clean build

## Run example mod
./gradlew -PincludeMods=true :examplemod:runClient
