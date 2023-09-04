Master branch is the main Production Branch

All new features merge to develop branch once feature branch passes

Feature branch branches out from develop branch

Useful Commands: 

mvn test -Dsurefire.suiteXmlFiles=testng_PNS_Prod_Smoke_Test.xml -Dtest.env=prod -Dtest.brand=pns -Dtest.browser=HEADLESS

-Dsurefire.suiteXmlFiles = Testng.xml file with tests required running. 

For other config paramater value, please refer config.properties file
