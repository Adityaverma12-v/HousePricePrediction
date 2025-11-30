@echo off
REM HousePricePrediction App Auto-Start Script
REM This script compiles, builds, deploys, and starts the application

setlocal enabledelayedexpansion

echo ============================================
echo HousePricePrediction Auto-Start Script
echo ============================================
echo.

REM Set paths
set PROJECT_DIR=%~dp0
set TOMCAT_HOME=C:\tomcat
set JAVA_HOME=C:\Program Files\Java\jdk-24

echo [1/6] Stopping any running Tomcat instances...
taskkill /F /IM java.exe >nul 2>&1
timeout /t 2 /nobreak >nul

echo [2/6] Cleaning build directory...
if exist "%PROJECT_DIR%build" rmdir /s /q "%PROJECT_DIR%build" 2>nul
if exist "%TOMCAT_HOME%\webapps\HousePricePrediction*" rmdir /s /q "%TOMCAT_HOME%\webapps\HousePricePrediction*" 2>nul
if exist "%TOMCAT_HOME%\work\Catalina\localhost\HousePricePrediction" rmdir /s /q "%TOMCAT_HOME%\work\Catalina\localhost\HousePricePrediction" 2>nul

echo [3/6] Creating build structure...
cd /d "%PROJECT_DIR%"
mkdir build\classes build\webapp\WEB-INF\lib build\webapp\WEB-INF\classes 2>nul

echo [4/6] Copying webapp files...
robocopy .\webapp .\build\webapp /E /NFL /NDL /NJH /NJS >nul 2>&1
copy ".\webapp\WEB-INF\classes\housing.arff" ".\build\webapp\WEB-INF\classes\housing.arff" >nul 2>&1

echo [5/6] Compiling Java sources and building WAR...
setlocal enabledelayedexpansion
for /f %%f in ('dir /b .\webapp\WEB-INF\lib\*.jar') do set CLASSPATH=!CLASSPATH!.\webapp\WEB-INF\lib\%%f;

javac -d build\classes -cp "%CLASSPATH%" src\com\uity\*.java src\com\uity\model\*.java 2>nul
if errorlevel 1 (
    echo ERROR: Compilation failed!
    pause
    exit /b 1
)

robocopy build\classes build\webapp\WEB-INF\classes /E /NFL /NDL /NJH /NJS >nul 2>&1

cd build\webapp
jar -cvf ..\..\HousePricePrediction.war . >nul 2>&1
cd ..\..

copy "HousePricePrediction.war" "%TOMCAT_HOME%\webapps\HousePricePrediction.war" >nul 2>&1

echo [6/6] Starting Tomcat server...
set CATALINA_HOME=%TOMCAT_HOME%
set JAVA_HOME=%JAVA_HOME%
call "%TOMCAT_HOME%\bin\startup.bat"

echo.
echo ============================================
echo ✓ Application is running!
echo ============================================
echo.
echo Access at: http://localhost:8080/HousePricePrediction/
echo.
echo To stop: C:\tomcat\bin\shutdown.bat
echo.
pause
