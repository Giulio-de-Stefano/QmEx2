# TheOtherThingy

#How to run me

1) Make sure you have chromedriver.exe
2) By default I'm installing the Ublock Origin extension cause I was experiencing significant slow downs when
trying to load ebay.co.uk. If you want to keep this behaviour, make sure you have the CRX file, otherwise please comment
out "installUblock()" in ChromeDriverBuilder.
3) Make sure the paths to the files above are correct relative to your system in ChromeDriverBuilder.
4) Now you can either run using "mvn clean test", running TestRunner.java or just running the feature/scenario directly.
5) Reporting is enabled (and pretty!) but will only be running when running through mvn or TestRunner.java.
6) I haven't used Background or Scenario Outlines simply because they don't make much sense with just 1 test case.