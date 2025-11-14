# Country Administrative System

A Spring Boot application for managing Rwandan administrative divisions (Provinces, Districts, Sectors, Cells, and Villages).

## Features

- **Hierarchical Data Management**: Complete administrative structure from provinces down to villages
- **RESTful API**: Full CRUD operations for all administrative levels
- **PostgreSQL Database**: Robust data storage with proper relationships
- **Data Initialization**: Automatic loading of administrative data from text files
- **Search Functionality**: Search by name across all administrative levels
- **Statistics**: Get counts and statistics for all administrative divisions

## Data Structure

The system manages the following administrative levels:

1. **Provinces** (5 total): Kigali, Southern, Western, Northern, Eastern
2. **Districts** (30 total): Each province contains multiple districts
3. **Sectors** (437 total): Each district contains multiple sectors
4. **Cells** (2,172 total): Each sector contains multiple cells
5. **Villages** (10,000+ total): Each cell contains multiple villages

## Prerequisites

- Java 21 or higher
- Maven 3.6 or higher
- PostgreSQL 12 or higher

## Database Setup

1. Install PostgreSQL on your system
2. Create a database named `country_db`:
   ```sql
   CREATE DATABASE country_db;
   ```
3. Update the database credentials in `src/main/resources/application.properties` if needed:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/country_db
   spring.datasource.username=postgres
   spring.datasource.password=postgres
   ```

## Running the Application

### In IntelliJ IDEA (Recommended)

#### Method 1: Run Main Class
1. **Open IntelliJ IDEA** and open the project folder: `Country-Administrative-System`
2. **Wait for Maven import** to complete
3. **Navigate to** `src/main/java/com/example/Country/Administrative/System/CountryAdministrativeSystemApplication.java`
4. **Right-click** on the file and select `Run 'CountryAdministrativeSystemApplication'`
5. **Or click the green play button** (▶️) next to the main method

#### Method 2: Run with H2 Database (No PostgreSQL needed)
1. **Right-click** on `CountryAdministrativeSystemApplication.java`
2. **Select** `Run 'CountryAdministrativeSystemApplication'`
3. **In the Run Configuration**, add `--spring.profiles.active=dev` to Program Arguments
4. **Click Run**

#### Method 3: Using Maven in IntelliJ
1. **Open Maven tool window**: `View` → `Tool Windows` → `Maven`
2. **Expand**: `Country-Administrative-System` → `Plugins` → `spring-boot`
3. **Double-click**: `spring-boot:run`

### From Command Line

#### Option 1: With PostgreSQL
```bash
# Navigate to the project directory
cd Country-Administrative-System

# Run the application
./mvnw spring-boot:run
```

#### Option 2: With H2 Database (No PostgreSQL needed)
```bash
# Run with dev profile (H2 database)
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

#### Option 3: Using batch files (Windows)
```bash
# For PostgreSQL
run-app.bat

# For H2 database (no setup needed)
run-dev.bat
```

The application will start on `http://localhost:8080`

### Database Access

#### H2 Console (Development)
When running with the `dev` profile, you can access the H2 database console at:
- **URL**: `http://localhost:8080/h2-console`
- **JDBC URL**: `jdbc:h2:mem:countrydb`
- **Username**: `sa`
- **Password**: (leave empty)

## API Endpoints

### Provinces
- `GET /api/provinces` - Get all provinces
- `GET /api/provinces/{code}` - Get province by code
- `GET /api/provinces/search?name={name}` - Search provinces by name
- `POST /api/provinces` - Create new province
- `PUT /api/provinces/{code}` - Update province
- `DELETE /api/provinces/{code}` - Delete province

### Districts
- `GET /api/districts` - Get all districts
- `GET /api/districts/{code}` - Get district by code
- `GET /api/districts/province/{provinceCode}` - Get districts by province
- `GET /api/districts/search?name={name}` - Search districts by name
- `GET /api/districts/search/province?provinceCode={code}&name={name}` - Search districts by province and name

### Sectors
- `GET /api/sectors` - Get all sectors
- `GET /api/sectors/{code}` - Get sector by code
- `GET /api/sectors/district/{districtCode}` - Get sectors by district
- `GET /api/sectors/province/{provinceCode}` - Get sectors by province
- `GET /api/sectors/search?name={name}` - Search sectors by name
- `GET /api/sectors/search/district?districtCode={code}&name={name}` - Search sectors by district and name

### Cells
- `GET /api/cells` - Get all cells
- `GET /api/cells/{code}` - Get cell by code
- `GET /api/cells/sector/{sectorCode}` - Get cells by sector
- `GET /api/cells/district/{districtCode}` - Get cells by district
- `GET /api/cells/province/{provinceCode}` - Get cells by province
- `GET /api/cells/search?name={name}` - Search cells by name
- `GET /api/cells/search/sector?sectorCode={code}&name={name}` - Search cells by sector and name

### Villages
- `GET /api/villages` - Get all villages
- `GET /api/villages/{code}` - Get village by code
- `GET /api/villages/cell/{cellCode}` - Get villages by cell
- `GET /api/villages/sector/{sectorCode}` - Get villages by sector
- `GET /api/villages/district/{districtCode}` - Get villages by district
- `GET /api/villages/province/{provinceCode}` - Get villages by province
- `GET /api/villages/search?name={name}` - Search villages by name
- `GET /api/villages/search/cell?cellCode={code}&name={name}` - Search villages by cell and name

### Statistics
- `GET /api/statistics` - Get all statistics
- `GET /api/statistics/provinces` - Get province count
- `GET /api/statistics/districts` - Get district count
- `GET /api/statistics/sectors` - Get sector count
- `GET /api/statistics/cells` - Get cell count
- `GET /api/statistics/villages` - Get village count

## Data Initialization

The application automatically loads data from the following files in `src/main/resources/`:
- `province.txt` - Province data
- `district.txt` - District data
- `sector.txt` - Sector data
- `cell.txt` - Cell data
- `village.txt` - Village data (limited to first 10,000 for performance)

## Example Usage

### Get all provinces:
```bash
curl http://localhost:8080/api/provinces
```

### Get districts in Kigali province:
```bash
curl http://localhost:8080/api/districts/province/01
```

### Search sectors by name:
```bash
curl "http://localhost:8080/api/sectors/search?name=Kigali"
```

### Get statistics:
```bash
curl http://localhost:8080/api/statistics
```

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/example/Country/Administrative/System/
│   │       ├── controller/          # REST controllers
│   │       ├── entity/             # JPA entities
│   │       ├── repository/         # Data repositories
│   │       ├── service/            # Business logic
│   │       └── CountryAdministrativeSystemApplication.java
│   └── resources/
│       ├── application.properties  # Configuration
│       ├── province.txt           # Province data
│       ├── district.txt           # District data
│       ├── sector.txt             # Sector data
│       ├── cell.txt               # Cell data
│       └── village.txt            # Village data
└── test/
    └── java/                      # Test classes
```

## Troubleshooting

### Main Method Not Runnable in IntelliJ
If you see "Main method not runnable" in IntelliJ:

1. **Check Maven Import**: Right-click on `pom.xml` → `Maven` → `Reload project`
2. **Clean and Rebuild**: `Build` → `Clean` → `Build` → `Rebuild Project`
3. **Check Java Version**: Ensure you're using Java 17+ (`File` → `Project Structure` → `Project` → `Project SDK`)
4. **Use H2 Profile**: Run with `--spring.profiles.active=dev` to avoid PostgreSQL dependency

### Database Connection Issues
- **PostgreSQL not running**: Use the H2 profile instead (`--spring.profiles.active=dev`)
- **Database doesn't exist**: Create it with `CREATE DATABASE country_db;`
- **Connection refused**: Check if PostgreSQL is running on port 5432

### Application Won't Start
- **Check logs**: Look at the console output for specific error messages
- **Try H2 profile**: Use `--spring.profiles.active=dev` to run without PostgreSQL
- **Check port 8080**: Ensure no other application is using port 8080

## Technologies Used

- **Spring Boot 3.5.6** - Main framework
- **Spring Data JPA** - Data access layer
- **PostgreSQL** - Database (Production)
- **H2 Database** - In-memory database (Development)
- **Maven** - Build tool
- **Jakarta Validation** - Input validation
- **Spring Web** - REST API

## License

This project is for educational purposes.
