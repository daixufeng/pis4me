@echo off

set webapp=balian-webapp

call mvn clean -f pom.xml

if exist %webapp%\src\main\webapp\WEB-INF\lib rd /s /q %webapp%\src\main\webapp\WEB-INF\lib

call mvn eclipse:eclipse -f pom.xml

call mvn package -Dmaven.test.skip=true -f pom.xml

xcopy /e /q %webapp%\target\%webapp%\WEB-INF\lib %webapp%\src\main\webapp\WEB-INF\lib\

echo ²Ù×÷Íê³É
pause
