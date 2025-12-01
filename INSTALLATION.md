## Installation & Setup Guide

### Prerequisites
- **Java Development Kit (JDK):** Version 11 or higher
- **MySQL Server:** Version 8.0 or higher
- **Maven:** Version 3.6 or higher
- **Apache Tomcat:** Version 9.0 or higher (for web deployment)
- **Git:** For version control (optional)

### Step 1: Clone/Download Project
```bash
cd path/to/project
```

### Step 2: Database Setup

#### Using MySQL Command Line:
```sql
mysql -u root -p
```

```sql
CREATE DATABASE IF NOT EXISTS houseprice_db;
USE houseprice_db;

-- Copy and execute the contents of src/main/resources/database.sql
```

Or execute the SQL script directly:
```bash
mysql -u root -p houseprice_db < src/main/resources/database.sql
```

### Step 3: Update Database Configuration

Edit `src/main/java/com/houseprice/util/DatabaseConnection.java`:
```java
private static final String DB_URL = "jdbc:mysql://localhost:3306/houseprice_db";
private static final String DB_USER = "your_mysql_username";
private static final String DB_PASSWORD = "your_mysql_password";
```

### Step 4: Build the Project

```bash
# Clean and compile
mvn clean compile

# Run tests
mvn test

# Create WAR package (for web deployment)
mvn clean package

# Or build JAR (for command line application)
mvn clean package -DskipTests
```

### Step 5a: Run as Console Application

```bash
mvn exec:java -Dexec.mainClass="com.houseprice.HousePriceApplication"
```

### Step 5b: Deploy as Web Application

1. **Using Tomcat:**
```bash
# Copy the generated WAR file to Tomcat's webapps directory
cp target/house-price-prediction-1.0-SNAPSHOT.war $TOMCAT_HOME/webapps/

# Start Tomcat
$TOMCAT_HOME/bin/startup.sh (Linux/Mac)
# or
$TOMCAT_HOME/bin/startup.bat (Windows)
```

2. **Access the application:**
```
http://localhost:8080/house-price-prediction-1.0-SNAPSHOT
```

### Step 6: Verify Installation

1. **Test Database Connection:**
```bash
mvn compile
mvn exec:java -Dexec.mainClass="com.houseprice.util.DatabaseConnection"
```

2. **Run Tests:**
```bash
mvn test
```

3. **Check Logs:**
Look for successful initialization messages in console output

## Troubleshooting

### Issue: MySQL Connection Failed
**Solution:**
- Verify MySQL server is running
- Check username/password credentials
- Ensure database exists: `SHOW DATABASES;`
- Check port: `SHOW VARIABLES LIKE 'port';`

### Issue: Maven Build Fails
**Solution:**
- Update Maven: `mvn -v`
- Clear repository: `mvn clean`
- Check Java version: `javac -version`

### Issue: Servlet Not Found (404 Error)
**Solution:**
- Verify WAR deployment: Check `$TOMCAT_HOME/webapps/`
- Check web.xml configuration
- Restart Tomcat service
- Check URL mapping in servlet configuration

### Issue: Out of Memory
**Solution:**
- Increase Heap Size:
```bash
export CATALINA_OPTS="-Xms512M -Xmx1024M"
```

### Issue: JDBC Driver Not Found
**Solution:**
- Verify pom.xml has MySQL connector dependency
- Rebuild: `mvn clean compile`
- Check library download: `~/.m2/repository/mysql/`

## Configuration Files

### pom.xml
- Maven build configuration
- Dependency management
- Plugin configuration

### src/main/resources/database.sql
- Database schema creation
- Sample data insertion
- View definitions

### src/main/resources/application.properties
- Database connection parameters
- Thread pool settings
- Cache configuration

### src/main/webapp/WEB-INF/web.xml
- Servlet mapping
- Application configuration
- Welcome file list

## Development Workflow

1. **Make Code Changes**
```bash
# Edit source files in src/main/java
```

2. **Compile & Test**
```bash
mvn clean test
```

3. **Build Package**
```bash
mvn package
```

4. **Deploy**
```bash
# Copy WAR to Tomcat
cp target/*.war $TOMCAT_HOME/webapps/
```

5. **Verify**
```bash
# Open browser and test
http://localhost:8080/house-price-prediction-1.0-SNAPSHOT
```

## Performance Tuning

### Database Optimization
- Add indexes: Already included in database.sql
- Use prepared statements: Implemented in DAO
- Connection pooling: Singleton pattern in DatabaseConnection

### Application Optimization
- Thread pool sizing: Configured in PricePredictionEngine
- Memory settings: Adjust CATALINA_OPTS
- Garbage collection: Use -XX:+UseG1GC for better performance

## Security Considerations

1. **SQL Injection:** Uses prepared statements (✓)
2. **Input Validation:** Implemented in ValidationUtil (✓)
3. **Exception Handling:** Custom exceptions prevent info leakage (✓)
4. **Session Security:** HttpOnly cookies configured (✓)

## Deployment Options

### Local Development
```bash
mvn tomcat7:run
```

### Production Deployment
- Package as WAR
- Deploy to application server
- Configure external database
- Enable SSL/TLS
- Set up logging and monitoring
