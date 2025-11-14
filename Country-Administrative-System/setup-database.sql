-- Database setup script for Country Administrative System
-- Run this script in PostgreSQL to create the database

-- Create database
CREATE DATABASE country_db;

-- Connect to the database
\c country_db;

-- Create user (optional, if you want a specific user)
-- CREATE USER admin_user WITH PASSWORD 'admin_password';
-- GRANT ALL PRIVILEGES ON DATABASE country_db TO admin_user;

-- The application will automatically create tables using JPA/Hibernate
-- No need to create tables manually as they will be generated from entities


