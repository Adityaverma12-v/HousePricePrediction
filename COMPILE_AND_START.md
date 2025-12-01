# 📖 COMPILE & START GUIDE

## **⚡ FASTEST WAY (30 seconds)**

```powershell
java -jar bin\output\HousePricePredictionDemo.jar
```

That's it! The application is already compiled and ready to run.

---

## **3 Ways to Compile & Run**

### **1️⃣ Use Pre-Compiled JAR (Recommended)**
**Time: < 2 seconds**

```powershell
# Navigate to project
cd "c:\Users\Aditya Verma\new house price preduction"

# Run it!
java -jar bin\output\HousePricePredictionDemo.jar
```

✅ No compilation needed  
✅ Instant execution  
✅ All features working  

---

### **2️⃣ Rebuild with PowerShell Script**
**Time: 5-10 seconds**

```powershell
# Step 1: Build
.\build.ps1

# Step 2: Run
java -jar bin\output\HousePricePredictionDemo.jar
```

✅ Recompiles from source  
✅ Automatic JAR creation  
✅ Clean build  

---

### **3️⃣ Rebuild with Batch Script**
**Time: 5-10 seconds**

```powershell
# Step 1: Build
.\build.bat

# Step 2: Run
.\run.bat
```

✅ Windows-native build  
✅ Simple commands  
✅ Full rebuild  

---

## **Manual Compilation (Advanced)**

If you want to compile manually without scripts:

### Compile all Java files:
```powershell
javac -d bin\classes -sourcepath src\main\java `
    src\main\java\com\houseprice\*.java `
    src\main\java\com\houseprice\model\*.java `
    src\main\java\com\houseprice\dao\*.java `
    src\main\java\com\houseprice\service\*.java `
    src\main\java\com\houseprice\util\*.java `
    src\main\java\com\houseprice\exception\*.java
```

### Create JAR file:
```powershell
jar cfm bin\output\HousePricePredictionDemo.jar `
    bin\classes\MANIFEST_DEMO.MF -C bin\classes .
```

### Run the JAR:
```powershell
java -jar bin\output\HousePricePredictionDemo.jar
```

---

## **What You'll See When Running**

The demo will output:

```
╔════════════════════════════════════════╗
║  House Price Prediction System - Demo  ║
║  Compiled with Simple Java (No Maven)  ║
╚════════════════════════════════════════╝

█ STEP 1: Creating Properties (Polymorphism)
  Created 3 sample properties

█ STEP 2: Property Details & Price Calculations
  RESIDENTIAL: $3,176,000.00
  COMMERCIAL: $10,523,000.00
  INDUSTRIAL: $8,109,800.00

█ STEP 3: Price Statistics (Streams & Generics)
  Total Properties: 3
  Average Price: $7,269,600.00

█ STEP 4: Generic Type Filtering
  Found 1 Residential property

█ STEP 5: Concurrent Price Predictions (Multithreading)
  LINEAR_REGRESSION: $3,334,800.00 (93%)
  PROPERTY_FEATURES: $2,527,800.00 (88%)
  MARKET_ANALYSIS: $3,652,400.00 (86%)

✅ Demo Completed Successfully!
```

---

## **File Structure**

```
project/
├── bin/
│   ├── classes/              (All compiled .class files)
│   └── output/
│       ├── HousePricePredictionDemo.jar    ✓ Run this!
│       └── HousePricePrediction.jar
│
├── src/
│   └── main/java/com/houseprice/
│       ├── HousePriceApplicationDemo.java  (Demo entry point)
│       ├── HousePriceApplication.java      (Full app entry point)
│       ├── model/         (5 classes)
│       ├── dao/           (3 classes)
│       ├── service/       (2 classes)
│       ├── util/          (3 classes)
│       └── exception/     (3 classes)
│
├── build.ps1               (PowerShell build script)
├── build.bat               (Batch build script)
├── run.bat                 (Run script)
├── run.sh                  (Unix run script)
└── QUICKSTART.md           (This guide)
```

---

## **Troubleshooting**

### ❌ "java: command not found"
→ Java is not installed. Install from:
- java.com (official)
- Or: `choco install openjdk`

### ❌ "Cannot find file"
→ Make sure you're in the right directory:
```powershell
cd "c:\Users\Aditya Verma\new house price preduction"
```

### ❌ Build script fails
→ Use PowerShell with ExecutionPolicy:
```powershell
powershell -ExecutionPolicy Bypass -File .\build.ps1
```

### ❌ JAR not found
→ Rebuild first:
```powershell
.\build.ps1
```

---

## **Command Reference**

| Command | Purpose |
|---------|---------|
| `java -version` | Check Java version |
| `.\build.ps1` | Rebuild (PowerShell) |
| `.\build.bat` | Rebuild (Batch) |
| `java -jar bin\output\HousePricePredictionDemo.jar` | Run demo |
| `java -jar bin\output\HousePricePrediction.jar` | Run full app |
| `javac -d bin\classes ...` | Manual compile |
| `jar cfm ... .` | Create JAR manually |

---

## **Summary**

**🎯 Just run this ONE command:**
```powershell
java -jar bin\output\HousePricePredictionDemo.jar
```

**If you need to rebuild:**
```powershell
.\build.ps1
```

**Then run the same command again.**

That's all! 🎉

---

## **Features Demonstrated in Demo**

✅ **OOP:** Inheritance, Polymorphism, Interfaces  
✅ **Collections:** ArrayList, Streams, Lambda  
✅ **Generics:** Type-safe operations with <T>  
✅ **Multithreading:** 3 concurrent algorithms  
✅ **Exception Handling:** Custom exceptions  
✅ **Design Patterns:** Singleton, DAO, Factory  

---

## **Requirements**

- Java 11+ (you have 24.0.2 ✓)
- 50 MB RAM
- Windows/Linux/Mac

No Maven, no external dependencies needed! 🚀
