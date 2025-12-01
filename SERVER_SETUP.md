# 🌐 WEB SERVER - HOSTED ON LOCALHOST

## ✅ Server is Running!

**URL:** http://localhost:9000

**Status:** ✅ Active and Running

---

## 📊 What's Included

### Home Page (`/`)
- Welcome message
- Feature overview
- Technology stack
- Requirements checklist
- API endpoints

### API Endpoint
- **`GET /api/properties`** - Returns sample properties in JSON format

Example response:
```json
{
  "properties": [
    {
      "id": 1,
      "type": "RESIDENTIAL",
      "address": "123 Oak Street",
      "area": 2000,
      "bedrooms": 3,
      "calculatedPrice": 3176000
    },
    ...
  ],
  "statistics": {
    "totalProperties": 3,
    "totalValue": 21808800,
    "averagePrice": 7269600
  }
}
```

---

## 🚀 How It Works

1. **Compilation**
   ```powershell
   javac -d bin\classes -sourcepath src\main\java src\main\java\com\houseprice\SimpleHttpServer.java
   ```

2. **Running**
   ```powershell
   java -cp bin\classes com.houseprice.SimpleHttpServer
   ```

3. **Access**
   - Open browser: http://localhost:9000
   - Or fetch API: http://localhost:9000/api/properties

---

## 📋 Features

✅ **HTTP Server** - Built with Java HttpServer API  
✅ **REST API** - JSON response for properties  
✅ **Web UI** - Professional HTML interface  
✅ **Statistics** - Property statistics endpoint  
✅ **Responsive** - Works on all browsers  

---

## 🔧 Server Details

- **Host:** localhost
- **Port:** 9000
- **Status:** Active
- **Main Class:** com.houseprice.SimpleHttpServer
- **Dependencies:** None (Java built-in HttpServer)

---

## 📍 Key URLs

| URL | Purpose |
|-----|---------|
| `http://localhost:9000/` | Home page |
| `http://localhost:9000/api/properties` | API endpoint |

---

## 📝 Stop the Server

To stop the server, press `Ctrl+C` in the terminal.

---

## 💡 Next Steps

1. ✅ Server is running on http://localhost:9000
2. Open the page in your browser
3. Check the API endpoint for properties
4. Explore the web interface

Enjoy your House Price Prediction System! 🏠💰
