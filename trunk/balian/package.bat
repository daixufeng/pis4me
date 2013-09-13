@echo off

set webapp=balian-webapp

call mvn clean -f pom.xml

if exist %webapp%\src\main\webapp\WEB-INF\lib rd /s /q %webapp%\src\main\webapp\WEB-INF\lib
if exist %webapp%\src\main\webapp\WEB-INF\classes rd /s /q %webapp%\src\main\webapp\WEB-INF\classes

call mvn eclipse:eclipse -f pom.xml

call mvn package -Dmaven.test.skip=true -f pom.xml

xcopy /e /q %webapp%\target\%webapp%\WEB-INF\lib %webapp%\src\main\webapp\WEB-INF\lib\
xcopy /e /q %webapp%\target\%webapp%\WEB-INF\classes %webapp%\src\main\webapp\WEB-INF\classes\

echo ²Ù×÷Íê³É
pause
