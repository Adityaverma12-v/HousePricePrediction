# 🚀 Quick Start: Compile & Run

## **5-Minute Setup**

### **Step 1: Verify Java is Installed**

```powershell
java -version
```

**Expected:** Java 11 or higher (you have 24.0.2 ✓)

---

## **Option A: Run Pre-Compiled JAR (Fastest)**

Already compiled and ready to go!

### Windows:
```powershell
java -jar bin\output\HousePricePredictionDemo.jar
```

### Linux/Mac:
```bash
java -jar bin/output/HousePricePredictionDemo.jar
```

**That's it!** The app will start and show all features.

---

## **Option B: Rebuild from Source**

### Windows (Batch Script):
```powershell
.\build.bat
```

Then run:
```powershell
.\run.bat
```

### Windows (PowerShell):
```powershell
.\build.ps1
```

### Manual Compilation:
```powershell
javac -d bin\classes -sourcepath src\main\java `
    src\main\java\com\houseprice\*.java `
    src\main\java\com\houseprice\model\*.java `
    src\main\java\com\houseprice\dao\*.java `
    src\main\java\com\houseprice\service\*.java `
    src\main\java\com\houseprice\util\*.java `
    src\main\java\com\houseprice\exception\*.java
```

---

## **What You'll See**

```
╔════════════════════════════════════════╗
║  House Price Prediction System - Demo  ║
║  Compiled with Simple Java (No Maven)  ║
╚════════════════════════════════════════╝

█ STEP 1: Creating Properties (Polymorphism)
Created 3 sample properties:

█ STEP 2: Property Details & Price Calculations
  ┌─ RESIDENTIAL
  │  Address: 123 Oak Street
  │  💰 Calculated Price: $3,176,000.00

█ STEP 3: Price Statistics (Streams & Generics)
  Total Properties: 3
  Average Price: $7,269,600.00

█ STEP 4: Generic Type Filtering
  Residential Properties Found: 1

█ STEP 5: Concurrent Price Predictions (Multithreading)
  LINEAR_REGRESSION: $3,334,800.00 (93% accurate)
  PROPERTY_FEATURES: $2,527,800.00 (88% accurate)
  MARKET_ANALYSIS: $3,652,400.00 (86% accurate)

✅ Demo Completed Successfully!
```

---

## **File Structure**

```
project/
├── bin/
│   └── output/
│       ├── HousePricePredictionDemo.jar    ← Run this!
│       └── HousePricePrediction.jar
├── src/
│   └── main/java/com/houseprice/
│       ├── 15 Java source files
│       ├── model/
│       ├── dao/
│       ├── service/
│       └── ...
└── build.bat / build.ps1                   ← Build scripts
```

---

## **Two JAR Options**

| JAR File | Main Class | Requires DB | Use When |
|----------|-----------|-------------|----------|
| **HousePricePredictionDemo.jar** | HousePriceApplicationDemo | ❌ No | First time / Testing |
| **HousePricePrediction.jar** | HousePriceApplication | ✅ Yes (Optional) | With MySQL |

---

## **Troubleshooting**

### "java: command not found"
→ Install Java from java.com or use: `choco install openjdk`

### "file not found"
→ Make sure you're in the project directory:
```powershell
cd "c:\Users\Aditya Verma\new house price preduction"
```

### Build fails
→ Run the PowerShell script:
```powershell
powershell -ExecutionPolicy Bypass -File .\build.ps1
```

---

## **Features in Demo**

✅ **OOP:** Inheritance, Polymorphism, Interfaces
✅ **Collections:** ArrayList, Streams, Generics  
✅ **Multithreading:** 3 concurrent algorithms
✅ **Exception Handling:** Custom exceptions
✅ **Price Calculations:** Real algorithms

---

## **Commands Cheat Sheet**

```powershell
# View Java version
java -version

# Run demo (fastest)
java -jar bin\output\HousePricePredictionDemo.jar

# Rebuild everything
.\build.ps1

# Rebuild (batch)
.\build.bat

# Manual compile
javac -d bin\classes -sourcepath src\main\java src\main\java\com\houseprice\*.java

# Create JAR manually
jar cfm bin\output\HousePricePredictionDemo.jar bin\classes\MANIFEST_DEMO.MF -C bin\classes .
```

---

## **Summary**

**Fastest way (1 command):**
```powershell
java -jar bin\output\HousePricePredictionDemo.jar
```

**Rebuild (1 command):**
```powershell
.\build.ps1
```

**Then run:**
```powershell
java -jar bin\output\HousePricePredictionDemo.jar
```

---

That's it! No Maven, no complex setup. Pure Java. 🎉
