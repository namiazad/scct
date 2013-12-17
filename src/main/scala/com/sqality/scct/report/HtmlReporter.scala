package com.sqality.scct.report

import java.io.File
import io.Source
import xml.{ Text, Node, NodeSeq }
import com.sqality.scct._

class HtmlReporter(project: ProjectData, writer: HtmlReportWriter) extends HtmlHelper {
  val data = project.coverage

  object files {
    val packages = "packages.html"
    val summary = "summary.html"
  }

  def report = {
    summaryReport
    packageListReport
    packageReports
    sourceFileReports
    resources
  }

  def summaryReport {
    writer.write(files.summary, projectSummaryReport ++ packageSummaryReport)
  }

  def projectSummaryReport = {
    val projects = data.forProjects
    if (projects.size > 1) {
      val header = headerRow("Total", data.percentage, data.ratioDetail)
      val items = for ((name, projectData) <- projects) yield headerRow(name, projectData.percentage, projectData.ratioDetail)
      table(header, items.toList)
    } else {
      val header = headerRow(projects.head._1, data.percentage, data.ratioDetail)
      table(header, NodeSeq.Empty)
    }
  }

  def packageSummaryReport = {
    val items = for ((name, packageData) <- data.forPackages) yield itemRow(name, packageData.percentage, packageReportFileName(name), packageData.ratioDetail)
    table(NodeSeq.Empty, items.toList)
  }

  def packageListReport {
    val html =
      <div class="content">
        <div class="pkgRow header">
          <a href={ files.summary }>Summary { format(data.percentage) }</a>
        </div>
        {
          for ((pkg, packageData) <- data.forPackages) yield {
            <div class="pkgRow pkgLink">
              <a href={ packageReportFileName(pkg) }>
                { pkg }
                &nbsp;{ format(packageData.percentage) }
              </a>
            </div> ++
              <div class="pkgRow pkgContent">
                {
                  for ((clazz, classData) <- packageData.forClasses) yield <div class="pkgRow">
                                                                             <a href={ classHref(clazz) }>
                                                                               <span class="className">{ classNameHeader(clazz) }</span>
                                                                               &nbsp;{ format(classData.percentage) }
                                                                             </a>
                                                                           </div>
                }
              </div>
          }
        }
      </div>
    writer.write(files.packages, html)
  }

  def packageReports {
    for ((pkg, packageData) <- data.forPackages) {
      val header = headerRow(pkg, packageData.percentage, packageData.ratioDetail)
      val items = classItemRows(packageData)
      writer.write(packageReportFileName(pkg), table(header, items))
    }
  }

  def resources {
    val rs = List("class.png", "object.png", "package.png", "trait.png", "filter_box_left.png", "filter_box_right.png",
      "jquery-1.6.1.min.js", "jquery-ui-1.8.4.custom.min.js", "style.css", "main.js", "index.html")
    rs.foreach { name =>
      writer.write(name, IO.readResourceBytes("/html-reporting/" + name))
    }
  }
  def sourceFileReports {
    for ((sourceFile, sourceData) <- data.forSourceFiles) {
      val report = SourceFileHtmlReporter.report(sourceFile, sourceData, project)
      writer.write(sourceReportFileName(sourceFile), report)
    }
  }

}
