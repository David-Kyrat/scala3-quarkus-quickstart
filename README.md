# scala3-quarkus-quickstart

[![CI](https://github.com/carlosedp/scala3-quarkus-quickstart/actions/workflows/CI.yaml/badge.svg)](https://github.com/carlosedp/scala3-quarkus-quickstart/actions/workflows/CI.yaml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=carlosedp_scala3-quarkus-quickstart&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=carlosedp_scala3-quarkus-quickstart)
[![codecov](https://codecov.io/gh/carlosedp/scala3-quarkus-quickstart/graph/badge.svg?token=IlH0MwK3RA)](https://codecov.io/gh/carlosedp/scala3-quarkus-quickstart)

This project is quickstart using Scala 3 and Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

To learn more about Scala and new in [Scala 3](https://docs.scala-lang.org/scala3/book/introduction.html), check-out <https://docs.scala-lang.org/scala3/new-in-scala3.html>.

## Development tools recommendation

To start developing in Quarkus/Scala 3, I recommend the following:

- [Coursier](https://get-coursier.io/) to manage Scala tools and JVM install
- GraalVM 21 installed thru Coursier
- Install scalafmt and scalafix thru Coursier
- [Quarkus CLI](https://quarkus.io/get-started/)
- [VSCode](https://code.visualstudio.com/) as IDE
- The following VSCode Extensions
  - [Metals](https://marketplace.visualstudio.com/items?itemName=scalameta.metals) by ScalaMeta
  - [Scala Syntax](https://marketplace.visualstudio.com/items?itemName=scala-lang.scala)
  - [Scaladex search](https://marketplace.visualstudio.com/items?itemName=baccata.scaladex-search)
  - [Quarkus Tools](https://marketplace.visualstudio.com/items?itemName=redhat.vscode-quarkus)

The repository also comes with a [Github Action](https://github.com/carlosedp/scala3-quarkus-quickstart/actions/workflows/CI.yaml) that runs tests on PRs and pushes.

## Running the application in dev mode

Run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
# or using the quarkus-cli (https://quarkus.io/get-started/)
quarkus dev
```

Then open <http://localhost:8080> that shows Quarkus static demo page and the endpoints <http://localhost:8080/hello> or <http://localhost:8080/greet?name=Yourname> which are written in Scala provided by the [GreetingResource.scala](./src/main/scala/org/acme/GreetingResource.scala) source file.

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

The sample repository also provides a sample Apache Kafka app containing a Producer, a Consumer and a Processor.

![article submission sample](./docs/articles.png)

This sample uses Kafka as a messaging middleware passing the data between each component and showing an HTML interface at <http://localhost:8080/articles.html>. The built-in Kafka UI can be seen at <http://localhost:8080/q/dev-ui/io.quarkus.quarkus-kafka-client/topics>.

To run tests, use:

```sh
# using quarkus-cli which will run in continuous testing
quarkus test
```

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/code-with-quarkus-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Provided Code

To reuse this code as a template for your own applications, remember to change the following:

- Create account on [Sonarcloud](https://sonarcloud.io) for analisys and [Codecov](https://app.codecov.io/) if desired
- Create account on [Mergify](https://dashboard.mergify.com/) if desired and configure the actions on `.mergify.yml`
- Update readme pointing to your own Sonarcloud (if kept), Codecov and GitHub action badges

### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
