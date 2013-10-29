Scala Code Coverage Tool [![Build Status](https://travis-ci.org/SCCT/scct.png?branch=master)](https://travis-ci.org/SCCT/scct) [![Coverage Status](https://coveralls.io/repos/SCCT/scct/badge.png?branch=master)](https://coveralls.io/r/SCCT/scct?branch=master)
=====================================================================================================================================================================================================================================

Available on sonatype oss and maven central repository.

Join the [SCCT](http://groups.google.com/group/scala-code-coverage-tool)
google group for help, bug reports, feature requests, and general
discussion on SCCT.

### What does it look like ?

![](http://scct.github.io/scct/screenshot.png)

### Scala versions

2.10.x, 2.9.3, 2.9.2 and 2.9.1.

Install
-------

### Add the plugin to your build with the following in project/build.sbt:

    addSbtPlugin("com.github.scct" % "sbt-scct" % "0.2.1")

#### Or you can use the last Snapshot instead

    resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
    addSbtPlugin("com.github.scct" % "sbt-scct" % "0.3-SNAPSHOT")

### Add the plugin settings to your project, at build.sbt:

    seq(ScctPlugin.instrumentSettings : _*)

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

Integrations
------------

### Jenkins
If you want to integrate your code coverage into Jenkins, you will need
to install [Cobertura
plugin](https://wiki.jenkins-ci.org/display/JENKINS/Cobertura+Plugin)
and add the post-build action.
![](https://d233eq3e3p3cv0.cloudfront.net/max/800/0*Ly-UfeaQGAO36ZwK.png)
![](https://d233eq3e3p3cv0.cloudfront.net/max/1235/0*7CVN-giWRJxiGy-L.png)

### Coveralls
A sbt plugin is available in case you need your coverage reports to be uploaded to https://coveralls.io :

[xsbt-coveralls-plugin](https://github.com/theon/xsbt-coveralls-plugin)

Who is using it
---------------

[![TomTom](http://corporate.tomtom.com/images/tomtom-logo_tcm166-3340.png)](http://www.tomtom.com)
