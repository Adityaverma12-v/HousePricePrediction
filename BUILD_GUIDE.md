# Simple Java Build & Run Guide (No Maven Required)

## Quick Start

### Windows

**Build:**
```powershell
.\build.bat
```

**Run:**
```powershell
.\run.bat
```

Or directly:
```powershell
java -jar bin\output\HousePricePrediction.jar
```

### Linux/Mac

**Build:**
```bash
chmod +x build.sh
./build.sh
```

**Run:**
```bash
chmod +x run.sh
./run.sh
```

Or directly:
```bash
java -jar bin/output/HousePricePrediction.jar
```

## Project Structure

```
project/
├── src/
│   ├── main/java/com/houseprice/
│   │   ├── HousePriceApplication.java      (Main entry point)
│   │   ├── model/                          (5 model classes)
│   │   ├── dao/                            (3 DAO classes)
│   │   ├── service/                        (2 service classes)
│   │   ├── util/                           (3 utility classes)
│   │   └── exception/                      (3 exception classes)
│   └── resources/
│       ├── database.sql                    (Database schema)
│       └── application.properties          (Configuration)
├── bin/
│   ├── classes/                            (Compiled .class files)
│   └── output/
│       └── HousePricePrediction.jar        (Executable JAR)
├── build.ps1                               (PowerShell build script)
├── build.bat                               (Batch build script)
├── run.bat                                 (Run script)
└── run.sh                                  (Unix run script)
```

## Build Details

The build scripts compile:
- All 15 Java source files
- Creates `/bin/classes/` for compiled classes
- Packages everything into `HousePricePrediction.jar`
- Includes manifest with main class entry point

## Compilation Command

Equivalent manual compilation:
```powershell
javac -d bin\classes -sourcepath src\main\java `
    src\main\java\com\houseprice\*.java `
    src\main\java\com\houseprice\model\*.java `
    src\main\java\com\houseprice\dao\*.java `
    src\main\java\com\houseprice\service\*.java `
    src\main\java\com\houseprice\util\*.java `
    src\main\java\com\houseprice\exception\*.java
```

## Features Compiled

✅ 15 Java source files
✅ OOP: Inheritance, Polymorphism, Interfaces, Exception Handling
✅ Collections & Generics
✅ Multithreading with ExecutorService
✅ Exception Handling with custom exceptions
✅ Business logic and utility classes

## Output

The compiled JAR includes:
- All application classes
- Proper manifest for execution
- Ready to run with Java 11+

## Notes

- No external dependencies required for compilation
- Requires Java 11+ to run
- MySQL database optional for full features
- Servlet features not included in JAR (console app only)
