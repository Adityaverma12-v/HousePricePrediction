# House Price Prediction System

A comprehensive Java application demonstrating advanced OOP concepts, JDBC database connectivity, multithreading, and web integration through servlets.

## Project Overview

This project implements a **House Price Prediction System** that uses multiple machine learning algorithms to estimate property prices based on various factors. It serves as a complete educational example of enterprise Java development.

## Requirements Met

### 1. **OOP Implementation (10 marks)**
- **Inheritance:** Abstract `Property` base class with `ResidentialProperty`, `CommercialProperty`, and `IndustrialProperty` subclasses
- **Polymorphism:** Abstract `calculatePrice()` method implemented differently in each property type
- **Interfaces:** `IRepository<T>` generic interface for CRUD operations
- **Exception Handling:** Custom exception hierarchy (`HousePriceException`, `DatabaseException`, `ValidationException`)

### 2. **Collections & Generics (6 marks)**
- Generic `IRepository<T>` interface for type-safe data operations
- `List<T>` collections with type parameters
- Stream API usage with `Collectors.toList()` for filtering
- Synchronized collections for thread-safe operations

### 3. **Multithreading & Synchronization (4 marks)**
- `ExecutorService` with thread pool for concurrent predictions
- `Future<T>` for asynchronous operations
- `synchronized` methods and blocks for thread safety
- `ConcurrentModificationException` prevention with synchronized list

### 4. **Classes for Database Operations (7 marks)**
- `DatabaseConnection` - JDBC connection management
- `PropertyDAO` - Data Access Object for properties
- `PredictionResultDAO` - Data Access Object for predictions
- `PropertyService` - Business logic layer
- Proper resource management with try-with-resources

### 5. **Database Connectivity (JDBC) (3 marks)**
- MySQL JDBC driver integration
- Connection pooling with singleton pattern
- PreparedStatements for SQL injection prevention
- ResultSet mapping to domain objects

### 6. **JDBC Implementation (3 marks)**
- Complete CRUD operations via `PropertyDAO` and `PredictionResultDAO`
- SQL queries with proper parameter binding
- Exception handling with meaningful error messages

### 7. **Problem Understanding & Solution Design (8 marks)**
- Well-structured architecture (MVC pattern)
- Clear separation of concerns (Model, Service, DAO, Servlet)
- Comprehensive documentation and comments
- Real-world property price prediction algorithm

### 8. **Core Java Concepts (10 marks)**
- Inheritance and polymorphism demonstration
- Abstract classes and methods
- Interface implementation
- Collections framework usage
- Stream API and functional programming
- Exception handling best practices
- Object-oriented design principles

### 9. **Database Integration (JDBC) (8 marks)**
- Full JDBC implementation for all database operations
- Transaction management
- Connection lifecycle management
- Prepared statements for security
- Result set mapping and data persistence

### 10. **Servlets & Web Integration (7 marks)**
- `PropertyServlet` handling GET and POST requests
- HTML form processing and data submission
- Dynamic HTML generation
- URL parameter handling
- Servlet lifecycle management
- Session and cookie handling

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/houseprice/
│   │       ├── model/
│   │       │   ├── Property.java (Abstract base class)
│   │       │   ├── ResidentialProperty.java
│   │       │   ├── CommercialProperty.java
│   │       │   ├── IndustrialProperty.java
│   │       │   └── PredictionResult.java
│   │       ├── dao/
│   │       │   ├── IRepository.java (Generic interface)
│   │       │   ├── PropertyDAO.java
│   │       │   └── PredictionResultDAO.java
│   │       ├── service/
│   │       │   ├── PropertyService.java
│   │       │   └── PricePredictionEngine.java
│   │       ├── servlet/
│   │       │   └── PropertyServlet.java
│   │       ├── util/
│   │       │   └── DatabaseConnection.java
│   │       ├── exception/
│   │       │   ├── HousePriceException.java
│   │       │   ├── DatabaseException.java
│   │       │   └── ValidationException.java
│   │       └── HousePriceApplication.java
│   ├── webapp/
│   │   ├── WEB-INF/
│   │   │   └── web.xml
│   │   └── index.html
│   └── resources/
│       └── database.sql
└── pom.xml
```

## Technologies Used

- **Java 11+** - Core language
- **MySQL 8.0** - Database
- **JDBC** - Database connectivity
- **Servlets 4.0** - Web framework
- **Maven** - Build tool
- **JUnit 4** - Testing framework

## Key Features

### 1. **Polymorphic Price Calculation**
Each property type calculates price differently based on its characteristics:
- **Residential:** Based on area, bedrooms, and amenities
- **Commercial:** Based on rental income and occupancy potential
- **Industrial:** Based on load capacity and zoning

### 2. **Concurrent Price Predictions**
- Three algorithms run simultaneously in separate threads
- Thread pool management with ExecutorService
- Timeout handling for long-running predictions

### 3. **Type-Safe Collections**
- Generic repository pattern for all entities
- Type parameters ensure compile-time safety
- Stream API for functional-style operations

### 4. **Comprehensive Exception Handling**
- Custom exception hierarchy with error codes
- Specific exceptions for different error scenarios
- Proper resource cleanup in finally blocks

## Setup Instructions

### Prerequisites
- Java Development Kit (JDK) 11+
- MySQL Server 8.0+
- Maven 3.6+
- Apache Tomcat 9.0+ (for web deployment)

### Database Setup
1. Open MySQL client
2. Execute `database.sql` to create tables and sample data
3. Update `DatabaseConnection.java` with your credentials

### Build and Run

**Command Line:**
```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="com.houseprice.HousePriceApplication"
```

**Web Deployment:**
```bash
mvn clean package
# Deploy the WAR file to Tomcat
```

## API Documentation

### PropertyDAO Methods
- `save(Property)` - Add new property
- `findById(int)` - Retrieve property by ID
- `findAll()` - Get all properties
- `update(Property)` - Update property details
- `delete(int)` - Remove property
- `count()` - Total properties count

### PropertyService Methods
- `addProperty(Property)` - Business logic for adding
- `getAllProperties()` - Retrieve all with validation
- `updateProperty(Property)` - Update with checks
- `getPropertiesByType(Class<T>)` - Generic filtering
- `getAveragePredictedPrice()` - Statistics calculation

### PricePredictionEngine Methods
- `predictPrice(Property)` - Concurrent predictions
- `getCachedPredictions(int)` - Retrieve cached results
- `clearCache()` - Clear prediction cache
- `shutdown()` - Graceful service shutdown

## Servlet Endpoints

- `GET /property` - Display menu
- `GET /property?action=list` - List all properties
- `GET /property?action=view&id=X` - View property details
- `GET /property?action=count` - Get property count
- `POST /property?action=add` - Add new property
- `POST /property?action=predict&propertyId=X` - Predict price
- `POST /property?action=delete&id=X` - Delete property

## Code Examples

### Using the Prediction Engine
```java
PropertyService service = new PropertyService();
PricePredictionEngine engine = new PricePredictionEngine(4);

Property property = service.getPropertyById(1);
List<PredictionResult> predictions = engine.predictPrice(property);

for (PredictionResult pred : predictions) {
    System.out.println(pred.getAlgorithm() + ": $" + pred.getPredictedPrice());
}
```

### Working with Generic DAO
```java
// Type-safe collection operations
List<ResidentialProperty> residential = 
    service.getPropertiesByType(ResidentialProperty.class);
```

## Testing

Run unit tests with:
```bash
mvn test
```

## Performance Considerations

- Connection pooling in `DatabaseConnection`
- Thread pool limiting for prediction engine
- Synchronized collections for concurrent access
- Prepared statements to reduce parsing overhead
- Indexes on frequently queried columns

## Future Enhancements

- Machine learning model integration
- REST API endpoints
- Spring Framework integration
- Real-time property market data
- Advanced caching strategies
- Unit and integration tests
- Docker containerization

## Author & License

This project is created for educational purposes demonstrating Java enterprise development.

---

**For questions or improvements, refer to the code documentation and comments.**
