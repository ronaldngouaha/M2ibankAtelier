@echo off
cd /d "%~dp0"
for /f "delims=" %%i in ('mvn dependency:build-classpath -q -Dmdep.outputFile=/dev/stdout') do set CLASSPATH=target\classes;%%i
java -cp "%CLASSPATH%" com.m2i.atelier.tp12.app.M2IBankApp
pause

