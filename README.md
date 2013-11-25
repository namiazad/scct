Scala Code Coverage Tool [![Build Status](https://travis-ci.org/sqality/scct.png?branch=master)](https://travis-ci.org/sqality/scct) [![Coverage Status](https://coveralls.io/repos/SCCT/scct/badge.png?branch=master)](https://coveralls.io/r/SCCT/scct?branch=master)
=====================================================================================================================================================================================================================================

Available on sonatype oss and maven central repository.

Join the [SCCT](http://groups.google.com/group/scala-code-coverage-tool)
google group for help, bug reports, feature requests, and general
discussion on SCCT.

### What does it look like ?

![](http://sqality.github.io/scct/screenshot.png)

### Scala versions

2.10.x, 2.9.3, 2.9.2 and 2.9.1.

Install
-------

### Add the plugin to your build with the following in project/build.sbt:

    addSbtPlugin("com.sqality.scct" % "sbt-scct" % "0.2.2")

#### Or you can use the last Snapshot instead

    resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
    addSbtPlugin("com.sqality.scct" % "sbt-scct" % "0.3-SNAPSHOT")

### Add the plugin settings to your project, at build.sbt:

    ScctPlugin.instrumentSettings

### Run your unit tests :

    $ sbt clean scct:test

Then open:

    ./target/scala_2.9.2/coverage-report/index.html

### Multiproject builds

Add the plugin instrumentation settings to child projects and the report
merging settings to the parent project.\
For example project/MyBuild.scala:

    object MyBuild extends Build {
      lazy val root = Project(id = "parent", base = file("."))
          settings (ScctPlugin.mergeReportSettings: _*) aggregate(first, second)

      lazy val firstChild = Project(id = "firstchild", base = file("first"))
          settings (ScctPlugin.instrumentSettings: _*)

      lazy val secondChild = Project(id = "secondchild", base = file("second"))
          settings (ScctPlugin.instrumentSettings: _*)
    }

Run coverage for all child projects with:

    $ sbt clean scct:test

This creates individual coverage reports into
\$childProjectDir/target/scala*<ver>/coverage-report/\
Merge all the existing child reports into
\$parentProjectDir/target/scala*<ver>/coverage-report/ with:

    $ sbt scct-merge-report


### Integrations
- [Coveralls](https://github.com/theon/xsbt-coveralls-plugin)
- [Gradle](https://github.com/sqality/gradle-scct)
- [Jenkins](https://github.com/sqality/scct/wiki/Jenkins)

### Learn More
- [Use SCCT with Maven](https://github.com/sqality/scct/wiki/Maven)
- [Use SCCT Standalone](https://github.com/sqality/scct/wiki/Standalone)
- [Who is using it](https://github.com/sqality/scct/wiki/Who-is-using-it)


### License

This software is licensed under the Apache 2 license, quoted below.

Copyright 2013 SCCT(http://sqality.com).

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this project except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0.

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
