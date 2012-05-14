@echo off
if ""%1"" == ""clean"" goto doClean
if ""%1"" == ""eclipse"" goto doEclipse
if ""%1"" == ""package"" goto doPackage
if ""%1"" == ""run"" goto doRun

:doClean
call mvn clean
if ""%1"" == ""clean"" goto Done

:doEclipse
rem call mvn eclipse:eclipse
if ""%1"" == ""eclipse"" goto Done

:doPackage
rem balian-all webapp 
if exist balian-webapp\src\main\webapp\WEB-INF\lib rd /s /q balian-webapp\src\main\webapp\WEB-INF\lib
if exist balian-webapp\src\main\webapp\WEB-INF\classes rd /s /q balian-webapp\src\main\webapp\WEB-INF\classes

call mvn package -Dmaven.test.skip=true

rem balian-all webapp 
xcopy /e /q balian-webapp\target\balian-webapp\WEB-INF\lib balian-webapp\src\main\webapp\WEB-INF\lib\
xcopy /e /q balian-webapp\target\balian-webapp\WEB-INF\classes balian-webapp\src\main\webapp\WEB-INF\classes\

goto Done
:doRun

:Done
echo COMPLETE
pause