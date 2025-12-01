# Simple Java Build Script (No Maven Required)
# PowerShell Version

Write-Host "======================================" -ForegroundColor Cyan
Write-Host "House Price Prediction - Java Build" -ForegroundColor Cyan
Write-Host "======================================" -ForegroundColor Cyan
Write-Host ""

# Create bin directory
if (!(Test-Path "bin")) { New-Item -ItemType Directory -Path "bin" | Out-Null }
if (!(Test-Path "bin\classes")) { New-Item -ItemType Directory -Path "bin\classes" | Out-Null }
if (!(Test-Path "bin\output")) { New-Item -ItemType Directory -Path "bin\output" | Out-Null }

Write-Host "[1/3] Compiling Java source files..." -ForegroundColor Yellow
Write-Host ""

# Get all Java files recursively from src, excluding servlet
$javaFiles = Get-ChildItem -Path "src\main\java" -Include "*.java" -Recurse | Where-Object { $_.FullName -notlike "*\servlet\*" } | Select-Object -ExpandProperty FullName

if ($javaFiles.Count -eq 0) {
    Write-Host "ERROR: No Java files found!" -ForegroundColor Red
    exit 1
}

Write-Host "Found $($javaFiles.Count) Java files to compile" -ForegroundColor Green
Write-Host ""

# Compile
javac -d "bin\classes" -sourcepath "src\main\java" $javaFiles 2>&1

if ($LASTEXITCODE -ne 0) {
    Write-Host ""
    Write-Host "ERROR: Compilation failed!" -ForegroundColor Red
    exit 1
}

Write-Host ""
Write-Host "[2/3] Creating manifest file..." -ForegroundColor Yellow
Write-Host ""

# Create manifest
$manifest = @"
Manifest-Version: 1.0
Main-Class: com.houseprice.HousePriceApplication
Class-Path: lib/mysql-connector-java-8.0.33.jar
"@

Set-Content -Path "bin\classes\MANIFEST.MF" -Value $manifest -Encoding ASCII

Write-Host ""
Write-Host "[3/3] Creating JAR file..." -ForegroundColor Yellow
Write-Host ""

# Create JAR
jar cfm "bin\output\HousePricePrediction.jar" "bin\classes\MANIFEST.MF" -C "bin\classes" .

if ($LASTEXITCODE -eq 0) {
    Write-Host ""
    Write-Host "======================================" -ForegroundColor Green
    Write-Host "BUILD SUCCESSFUL!" -ForegroundColor Green
    Write-Host "======================================" -ForegroundColor Green
    Write-Host ""
    Write-Host "Output JAR: bin\output\HousePricePrediction.jar" -ForegroundColor Cyan
    Write-Host ""
    Write-Host "To run: java -jar bin\output\HousePricePrediction.jar" -ForegroundColor Cyan
    Write-Host ""
} else {
    Write-Host ""
    Write-Host "ERROR: JAR creation failed!" -ForegroundColor Red
    exit 1
}
