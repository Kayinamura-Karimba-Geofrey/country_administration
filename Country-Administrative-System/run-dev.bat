@echo off
echo Starting Country Administrative System with H2 Database...
echo.
echo This will use an in-memory H2 database for development.
echo You can access the H2 console at: http://localhost:8080/h2-console
echo.
echo Press Ctrl+C to stop the application
echo.

cd /d "%~dp0"
.\mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=dev

pause
