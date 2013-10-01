package com.github.scct

class UncoveredAnnotationInstrumentationSpec extends InstrumentationSpec {
  "skipping instrumentation with uncovered-annotation" should {
    "work for class " in {
      offsetsMatch("""\@com.github.scct.uncovered class Foo { println("yeah"); def x = 12; }""")
    }
    "work for method" in {
      classOffsetsMatch("""\@com.github.scct.uncovered def method { println("yeah"); 123 } """)
    }
  }
}