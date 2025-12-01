@echo off
REM Simple Java Build Script (No Maven Required)

echo ======================================
echo House Price Prediction - Java Build
echo ======================================

REM Create bin directory
if not exist bin mkdir bin
if not exist bin\classes mkdir bin\classes
if not exist bin\output mkdir bin\output

echo.
echo [1/3] Compiling Java source files...
echo.

REM Compile all Java files
javac -d bin\classes -sourcepath src\main\java ^
    src\main\java\com\houseprice\*.java ^
    src\main\java\com\houseprice\model\*.java ^
    src\main\java\com\houseprice\dao\*.java ^
    src\main\java\com\houseprice\service\*.java ^
    src\main\java\com\houseprice\util\*.java ^
    src\main\java\com\houseprice\exception\*.java 2>&1

if %errorlevel% neq 0 (
    echo.
    echo ERROR: Compilation failed!
    exit /b 1
)

echo.
echo [2/3] Creating manifest file...
echo.

REM Create manifest
(
    echo Manifest-Version: 1.0
    echo Main-Class: com.houseprice.HousePriceApplication
    echo Class-Path: lib/mysql-connector-java-8.0.33.jar
) > bin\classes\MANIFEST.MF

echo.
echo [3/3] Creating JAR file...
echo.

REM Create JAR
jar cfm bin\output\HousePricePrediction.jar bin\classes\MANIFEST.MF -C bin\classes .

if %errorlevel% equ 0 (
    echo.
    echo ======================================
    echo BUILD SUCCESSFUL!
    echo ======================================
    echo.
    echo Output JAR: bin\output\HousePricePrediction.jar
    echo.
    echo To run: java -jar bin\output\HousePricePrediction.jar
    echo.
) else (
    echo.
    echo ERROR: JAR creation failed!
    exit /b 1
)
