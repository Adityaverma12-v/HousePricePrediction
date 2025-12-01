## Project Architecture Documentation

### Design Patterns Used

#### 1. Model-View-Controller (MVC)
- **Model:** Property, PredictionResult entities
- **View:** HTML pages, Servlet responses
- **Controller:** PropertyServlet handles requests/responses

#### 2. Data Access Object (DAO)
- Abstract generic interface: `IRepository<T>`
- Concrete implementations: `PropertyDAO`, `PredictionResultDAO`
- Isolates database logic from business logic

#### 3. Singleton Pattern
- `DatabaseConnection` ensures single database connection
- Thread-safe implementation with synchronization

#### 4. Factory Pattern
- Property objects created based on type
- `mapResultSetToProperty()` creates appropriate subclass

#### 5. Service Layer Pattern
- `PropertyService` encapsulates business logic
- `PricePredictionEngine` handles complex operations

#### 6. Thread Pool Pattern
- `ExecutorService` manages concurrent predictions
- Fixed thread pool prevents resource exhaustion

### Class Hierarchy (OOP Concepts)

```
Property (Abstract Base Class)
├── ResidentialProperty
├── CommercialProperty
└── IndustrialProperty

HousePriceException (Custom Exception)
├── DatabaseException
└── ValidationException

IRepository<T> (Generic Interface)
├── PropertyDAO (implements)
└── PredictionResultDAO (implements)
```

### Data Flow

```
Client Request (Web/Console)
    ↓
Servlet/Application Entry Point
    ↓
PropertyService (Business Logic)
    ↓
DAO Layer (PropertyDAO/PredictionResultDAO)
    ↓
JDBC (SQL Queries)
    ↓
MySQL Database
    ↓
ResultSet → Entity Objects
    ↓
Response to Client
```

### Multithreading Flow

```
predictPrice() method
    ↓
Submit 3 tasks to ExecutorService
    ├── predictUsingLinearRegression()
    ├── predictUsingPropertyFeatures()
    └── predictUsingMarketAnalysis()
    ↓
Future<PredictionResult> objects
    ↓
Collect results with get()
    ↓
Cache results (synchronized)
    ↓
Return to caller
```

### Database Schema

```
Properties Table
├── property_id (PK)
├── address
├── area
├── bedrooms
├── bathrooms
├── year_built
├── property_type
├── status
└── created_date

Prediction_Results Table
├── prediction_id (PK)
├── property_id (FK)
├── predicted_price
├── actual_price
├── accuracy
├── algorithm
├── status
└── prediction_date

Views
├── property_statistics
└── prediction_statistics
```

### Exception Handling Hierarchy

```
Exception
└── HousePriceException (Custom)
    ├── DatabaseException
    └── ValidationException

Each exception includes:
- Custom error message
- Error code
- Cause/Root exception
```

### Synchronization Strategy

**Thread Safety Mechanisms:**
1. **Synchronized Collections:** `predictionCache` uses `Collections.synchronizedList()`
2. **Synchronized Methods:** `getCachedPredictions()` synchronized
3. **Synchronized Blocks:** `cacheLock` object for explicit synchronization
4. **Future Objects:** Wait for async tasks completion
5. **ExecutorService:** Manages thread lifecycle

### Performance Considerations

#### Connection Management
- Singleton pattern prevents connection leak
- try-with-resources ensures closure
- Single connection reused safely

#### Caching Strategy
- In-memory cache for frequent predictions
- Synchronized access for thread safety
- Clear option to manage memory

#### Database Optimization
- Indexed columns for faster queries
- Prepared statements prevent parsing overhead
- Batch operations for bulk inserts

#### Thread Management
- Fixed thread pool size (4)
- Timeout handling (10 seconds)
- Graceful shutdown mechanism

### Key Features Implementation

#### 1. Polymorphism
```java
// Each subclass implements differently
public abstract double calculatePrice();

// Linear regression algorithm
private PredictionResult predictUsingLinearRegression()

// Feature-based algorithm
private PredictionResult predictUsingPropertyFeatures()

// Market analysis algorithm
private PredictionResult predictUsingMarketAnalysis()
```

#### 2. Generics
```java
public interface IRepository<T> {
    boolean save(T entity);
    T findById(int id);
    List<T> findAll();
}

public <T extends Property> List<T> getPropertiesByType(Class<T> type)
```

#### 3. Collections
```java
List<Property> properties = new ArrayList<>();
List<Future<PredictionResult>> futures = new ArrayList<>();
Map<Integer, Property> propertyCache = new HashMap<>();
```

#### 4. Exception Handling
```java
try {
    // Database operation
} catch (HousePriceException e) {
    // Handle application exception
} catch (SQLException e) {
    // Handle database error
} finally {
    // Cleanup resources
}
```

#### 5. Multithreading
```java
ExecutorService executor = Executors.newFixedThreadPool(4);
Future<PredictionResult> future = executor.submit(task);
PredictionResult result = future.get(10, TimeUnit.SECONDS);
```

### Configuration Management

**DatabaseConnection.java:**
- Database URL, username, password
- Driver name
- Connection instance

**application.properties:**
- External configuration
- Environment-specific settings
- Performance tuning parameters

**web.xml:**
- Servlet mapping
- Context configuration
- Security settings

### Testing Strategy

**Unit Tests:**
- PropertyServiceTest: Business logic
- PricePredictionEngineTest: Multithreading

**Integration Tests:**
- Database connectivity
- Servlet request/response
- End-to-end workflow

**Test Coverage:**
- OOP concepts validation
- Exception handling scenarios
- Concurrent operation correctness

### Deployment Architecture

**Development:**
```
IDE → Maven → Compiler → Console/Tomcat (Local)
```

**Production:**
```
Source Code → Build Server → Maven → Docker → Cloud Server
```

### Scalability Considerations

1. **Database:** Switch to connection pool (e.g., HikariCP)
2. **Caching:** Implement Redis for distributed cache
3. **Load Balancing:** Deploy multiple Tomcat instances
4. **Monitoring:** Add application performance monitoring
5. **Microservices:** Separate prediction engine into microservice

---

For detailed code documentation, refer to Javadoc comments in source files.
