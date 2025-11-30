#!/usr/bin/env powershell
<#
.SYNOPSIS
    HousePricePrediction App Auto-Start Script
.DESCRIPTION
    Automatically compiles, builds, deploys, and starts the HousePricePrediction application
.EXAMPLE
    .\start-app.ps1
#>

param([switch]$NoWait = $false)

$Colors = @{ Success = "Green"; Error = "Red"; Info = "Cyan"; Warning = "Yellow" }

function Write-ColorOutput {
    param([string]$Message, [string]$Color = "White")
    Write-Host $Message -ForegroundColor $Color
}

$ProjectDir = Split-Path -Parent $MyInvocation.MyCommand.Path
$TomcatHome = "C:\tomcat"
$JavaHome = "C:\Program Files\Java\jdk-24"

Write-ColorOutput "============================================" $Colors.Info
Write-ColorOutput "HousePricePrediction Auto-Start" $Colors.Info
Write-ColorOutput "============================================" $Colors.Info
Write-Host ""

Write-ColorOutput "[1/6] Stopping Tomcat instances..." $Colors.Info
Get-Process java -ErrorAction SilentlyContinue | Stop-Process -Force 2>$null
Start-Sleep -Seconds 2

Write-ColorOutput "[2/6] Cleaning build directory..." $Colors.Info
Remove-Item -Path "$ProjectDir\build" -Recurse -Force -ErrorAction SilentlyContinue
Remove-Item -Path "$TomcatHome\webapps\HousePricePrediction*" -Recurse -Force -ErrorAction SilentlyContinue
Remove-Item -Path "$TomcatHome\work\Catalina\localhost\HousePricePrediction" -Recurse -Force -ErrorAction SilentlyContinue

Write-ColorOutput "[3/6] Creating build structure..." $Colors.Info
New-Item -ItemType Directory -Force -Path "$ProjectDir\build\classes", "$ProjectDir\build\webapp\WEB-INF\lib", "$ProjectDir\build\webapp\WEB-INF\classes" | Out-Null

Write-ColorOutput "[4/6] Copying files and dataset..." $Colors.Info
robocopy "$ProjectDir\webapp" "$ProjectDir\build\webapp" /E /NFL /NDL /NJH /NJS 2>&1 | Out-Null
Copy-Item "$ProjectDir\webapp\WEB-INF\classes\housing.arff" "$ProjectDir\build\webapp\WEB-INF\classes\housing.arff" -Force

Write-ColorOutput "[5/6] Compiling and building WAR..." $Colors.Info
Push-Location $ProjectDir

$Jars = @(Get-ChildItem -Path ".\webapp\WEB-INF\lib" -Filter "*.jar" | ForEach-Object { $_.FullName })
$Classpath = $Jars -join ";"
$JavaFiles = @(Get-ChildItem -Path .\src -Recurse -Filter "*.java" | ForEach-Object { $_.FullName })

javac -d build\classes -cp "$Classpath" $JavaFiles 2>&1 | Where-Object { $_ -like "*error*" } | ForEach-Object {
    Write-ColorOutput $_ $Colors.Error
}

if ($LASTEXITCODE -ne 0) {
    Write-ColorOutput "ERROR: Compilation failed!" $Colors.Error
    Pop-Location
    Read-Host "Press Enter to exit"
    exit 1
}

robocopy build\classes build\webapp\WEB-INF\classes /E /NFL /NDL /NJH /NJS 2>&1 | Out-Null

Push-Location build\webapp
jar -cvf ..\..\HousePricePrediction.war . 2>&1 | Out-Null
Pop-Location

Copy-Item "HousePricePrediction.war" "$TomcatHome\webapps\HousePricePrediction.war" -Force
Pop-Location

Write-ColorOutput "✓ Build successful!" $Colors.Success

Write-ColorOutput "[6/6] Starting Tomcat..." $Colors.Info
$Env:CATALINA_HOME = $TomcatHome
$Env:JAVA_HOME = $JavaHome
& "$TomcatHome\bin\startup.bat" 2>&1 | Out-Null

Write-Host ""
Write-ColorOutput "============================================" $Colors.Success
Write-ColorOutput "✓ Application is running!" $Colors.Success
Write-ColorOutput "============================================" $Colors.Success
Write-Host ""
Write-ColorOutput "Access at: http://localhost:8080/HousePricePrediction/" $Colors.Info
Write-Host ""
Write-ColorOutput "To stop: & 'C:\tomcat\bin\shutdown.bat'" $Colors.Warning
Write-Host ""

if (-not $NoWait) { Read-Host "Press Enter to exit" }
