# HousePricePrediction

Comprehensive guide to set up, develop, build, and run the HousePricePrediction Spring MVC web application that predicts house prices using a linear regression model (Weka).

## Repository layout (important files)

- `kc_house_data.csv` — raw dataset included in the repo.
- `src/` — Java source code
  - `src/com/uity/HomeController.java` — Spring MVC controller (entry points / mappings).
  - `src/com/uity/model/RegConvert.java` — helper/utility to convert CSV -> ARFF (useful for Weka).
  - `src/com/uity/model/Regression.java` — model/helper used by JSPs to hold coefficients and compute predictions.
  - `src/com/uity/model/RegressionAnalysis.java` — loads ARFF, trains `LinearRegression` (Weka), computes R² and outputs coefficients.
- `webapp/` — web application resources and JSPs
  - `webapp/WEB-INF/jsp/output.jsp` — page that calculates and displays predicted price.
  - `webapp/WEB-INF/jsp/evaluate.jsp` — page that displays model evaluation (R²) using `RegressionAnalysis`.
  - `webapp/WEB-INF/web.xml` — servlet mappings / application configuration.
  - `webapp/WEB-INF/applicationContext.xml` — Spring configuration.
  - `webapp/WEB-INF/lib/` — third-party jars (add `weka.jar` here if not present).

Other folders:
- `resources/` — app resources and properties
- `work/` — servlet container compiled JSPs (generated at runtime by Tomcat)

## Goals of this README

- Explain prerequisites and environment setup.
- Show how to convert dataset and where to place it.
- Show how to configure the app for development and production.
- Provide step-by-step commands for Windows PowerShell to compile/run the project without an IDE.
- Provide troubleshooting and recommended improvements.

## Prerequisites

- Java JDK 8 or newer (JAVA_HOME must be set and `javac`/`java` should be on PATH).
- Apache Tomcat 8.x or newer (or another Servlet 3.0+ compatible container).
- Weka library (`weka.jar`). Download from https://www.cs.waikato.ac.nz/ml/weka/ if not included.
- (Recommended) An IDE that supports Java Web projects (Eclipse/STS, IntelliJ IDEA, or NetBeans).

If you plan to use the command-line build steps below, ensure the `jar`, `javac`, and `javap` utilities present in the JDK bin are accessible from PowerShell.

## Important configuration notes (before running)

1. Weka: Place `weka.jar` inside `webapp/WEB-INF/lib/`. If you prefer dependency management (Maven/Gradle), add Weka accordingly.

2. Dataset/ARFF path in `RegressionAnalysis`:
   - Open `src/com/uity/model/RegressionAnalysis.java` and search for the loader source path (it may be an absolute path).
   - Replace absolute paths with either a relative path (project path) or make it configurable (environment variable or context param).
   - Recommended place to put `housing.arff` is `webapp/WEB-INF/classes/` (class path) or `webapp/WEB-INF/data/` and reference it with a context-based path.

3. JSP logic: Currently `output.jsp` computes `price` directly using coefficients. For maintainability, consider moving the computation into a controller or helper class.

## Development - IDE workflow (recommended)

1. Import the project into your IDE as an existing Java web project or Maven project (if you add a `pom.xml`).
2. Add `weka.jar` to the project's classpath (Project -> Properties -> Java Build Path -> Libraries) or put it into `webapp/WEB-INF/lib/`.
3. Make sure `webapp` is set as the web resources folder and `src` is the source folder.
4. Configure an application server in the IDE (Tomcat) and run/debug the project directly.

Visit http://localhost:8080/<context>/ (context is typically the project name unless configured differently).

## Development - Command-line build & run (PowerShell)

The repository does not include a build system (Maven/Gradle) by default. The following steps show how to compile and produce a WAR manually using the JDK tools.

1) Create output directories

```powershell
# Run from the repository root (where README.md is located)
New-Item -ItemType Directory -Force -Path build\classes, build\webapp\WEB-INF\lib, build\webapp\WEB-INF\classes
```

2) Copy static webapp files into build area

```powershell
# Copy the webapp folder contents to build\webapp
robocopy .\webapp .\build\webapp /E /NFL /NDL /NJH /NJS
```

3) Make sure `weka.jar` is present under `webapp\WEB-INF\lib`. If not, download it and copy:

```powershell
# Example: copy downloaded weka.jar into the project lib
Copy-Item -Path C:\path\to\weka.jar -Destination .\webapp\WEB-INF\lib\weka.jar -Force
```

4) Compile Java sources

```powershell
$weka = ".\webapp\WEB-INF\lib\weka.jar"
$src = Get-ChildItem -Path .\src -Recurse -Filter "*.java" | ForEach-Object { $_.FullName }
$classpath = "$weka"
javac -d build\classes -cp $classpath $src

# Note: If compilation fails because of missing dependencies (e.g., Spring libs), add those jars to the classpath or compile inside the IDE where the full classpath is configured.
```

5) Copy compiled classes into `build\webapp\WEB-INF\classes`

```powershell
robocopy build\classes build\webapp\WEB-INF\classes /E /NFL /NDL /NJH /NJS
```

6) Ensure third-party libs are copied to `build\webapp\WEB-INF\lib` (the step above `robocopy` for webapp copied these already).

7) Create the WAR

```powershell
# From repo root, create WAR using jar tool from JDK
Set-Location -Path .\build\webapp
jar -cvf ..\..\HousePricePrediction.war *
Set-Location -Path ..\..
```

8) Deploy WAR to Tomcat

```powershell
# Copy WAR to Tomcat's webapps folder (adjust TOMCAT_HOME)
Copy-Item -Path .\HousePricePrediction.war -Destination "C:\path\to\tomcat\webapps\HousePricePrediction.war" -Force

# Start Tomcat (example - adjust to your install)
& "C:\path\to\tomcat\bin\startup.bat"
```

9) Open the app in browser:

http://localhost:8080/HousePricePrediction/

Notes about the command-line flow:
- If you rely on Spring libraries (likely present in `webapp/WEB-INF/lib`), ensure they are present or the compile step will fail. The simplest approach is to compile in an IDE which manages classpath or to add missing jars into `webapp/WEB-INF/lib` before compiling.

## Convert CSV -> ARFF (for Weka)

`RegConvert` is included at `src/com/uity/model/RegConvert.java`. You can either run it from your IDE or compile + run from the command line.

Command-line example (after compiling classes as shown above):

```powershell
# Run the conversion utility from the compiled classes folder, making sure weka.jar is on the classpath
$weka = ".\webapp\WEB-INF\lib\weka.jar"
java -cp ".\build\classes;$weka" com.uity.model.RegConvert input\kc_house_data.csv output\housing.arff

# Move the resulting ARFF to a place the app will read (e.g., webapp\WEB-INF\classes\housing.arff or webapp\WEB-INF\data\housing.arff)
Copy-Item -Path .\output\housing.arff -Destination .\webapp\WEB-INF\classes\housing.arff -Force
```

Adjust the `input` and `output` paths above to match how `RegConvert` expects arguments; open the source to confirm the expected parameter order.

## Configuring `RegressionAnalysis` dataset path

`RegressionAnalysis` loads the ARFF dataset using a path inside the class. Find where `ArffLoader` is given the file path. Replace hard-coded absolute paths with a classpath resource or a context-param (recommended). Example strategies:

- Put `housing.arff` in `webapp/WEB-INF/classes/` and load it via

```java
InputStream is = getClass().getResourceAsStream("/housing.arff");
// or load from servlet context path
```

- Or read an environment variable / system property and use that as the dataset location.

If you prefer the quick route, copy `housing.arff` to the same absolute path expected by the current code.

## How prediction works (technical summary)

- The JSP `output.jsp` reads input form parameters (bedrooms, bathrooms, lot size, living area, etc.).
- It gets coefficients provided by a `Regression` helper (which holds the array of coefficients `d[]`) and computes price by evaluating a polynomial-like combination of those coefficients and the input values.
- `RegressionAnalysis` is responsible for training a `LinearRegression` using Weka and extracting the coefficients; `evaluate.jsp` shows the R² value computed.

## Endpoints and pages

- `index.jsp` — landing page (form input)
- `output.jsp` — shows predicted price
- `evaluate.jsp` — shows model evaluation metrics (R²)

Paths are relative to the application context. For example, if the webapp context is `HousePricePrediction`, the evaluate page might be at:

http://localhost:8080/HousePricePrediction/WEB-INF/jsp/evaluate.jsp

However, note: JSPs under `WEB-INF/jsp/` are often forwarded to by controllers and not directly exposed.

## Troubleshooting

- ClassNotFound / NoClassDefFoundError (Weka or Spring): make sure the corresponding `*.jar` files are in `webapp/WEB-INF/lib/`.
- FileNotFound for ARFF: ensure the ARFF file is in the path referenced by `RegressionAnalysis` (or change the code to reference a relative/classpath resource).
- JSP errors at runtime: check Tomcat logs (`TOMCAT_HOME/logs/`) and the generated JSP Java files under `work/` for stack traces. The generated Java files shown in `work/org/apache/jsp/` can help locate JSP compilation issues.

## Quick checklist for first run

1. Add `weka.jar` to `webapp/WEB-INF/lib`.
2. Convert `kc_house_data.csv` to `housing.arff` using `RegConvert` and place `housing.arff` in `webapp/WEB-INF/classes` (or update `RegressionAnalysis` path).
3. Start Tomcat and deploy WAR (or run from IDE).
4. Open the application in a browser and test the form.

## Recommended small improvements (development)

1. Make the ARFF/dataset path configurable via `applicationContext.xml` or environment variable.
2. Move business logic out of JSPs into controllers/service classes.
3. Add a tiny `pom.xml` / `build.gradle` to manage dependencies and enable reproducible builds.
4. Add a tiny test harness (JUnit) for `RegressionAnalysis` using a small ARFF test file.

## Next steps and contribution notes

- If you'd like, I can:
  - Add a `pom.xml` and convert the project to a Maven project.
  - Update `RegressionAnalysis` to load dataset via classpath and remove absolute paths.
  - Move JSP logic into a controller and add REST endpoints for JSON predictions.

Please tell me which of these you'd like me to implement next.

---

Last updated: November 18, 2025
