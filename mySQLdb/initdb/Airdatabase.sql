DROP DATABASE IF EXISTS airdatabase;
CREATE DATABASE airdatabase;
USE airdatabase;

-- Table for AppUser (matches @Table(name = "app_user"))
CREATE TABLE app_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('ADMIN', 'USER') NOT NULL -- Using ENUM for stricter data integrity
);

-- Table for Passenger (matches @Table(name = "passenger"))
CREATE TABLE passenger (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

-- Table for LogisticUser (matches @Table(name = "logistic_user"))
CREATE TABLE logistic_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    createDate DATETIME -- Matches @Column(name = "createDate")
);

-- Table for Airport (matches @Table(name = "airport"))
CREATE TABLE airport (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    country VARCHAR(50),
    iata_code VARCHAR(10)
);

-- Table for Aircraft (matches @Table(name = "aircraft"))
CREATE TABLE aircraft (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    model VARCHAR(100),
    manufacture VARCHAR(100),
    gewicht VARCHAR(100),
    createdate DATETIME -- Matches @Column(name = "createdate")
);

-- Table for Flight (matches @Table(name = "Flight"))
CREATE TABLE Flight (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    flight_number VARCHAR(20),
    departure_time DATETIME,
    arrival_time DATETIME,
    aircraft_id BIGINT,
    departure_airport_id BIGINT,
    arrival_airport_id BIGINT,
    logistic_user_id BIGINT,
    FOREIGN KEY (aircraft_id) REFERENCES aircraft(id),
    FOREIGN KEY (departure_airport_id) REFERENCES airport(id),
    FOREIGN KEY (arrival_airport_id) REFERENCES airport(id),
    FOREIGN KEY (logistic_user_id) REFERENCES logistic_user(id)
);

-- Table for Booking (matches @Table(name = "booking"))
CREATE TABLE booking (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    flight_id BIGINT,
    passenger_id BIGINT,
    booking_date DATETIME,
    FOREIGN KEY (flight_id) REFERENCES Flight(id),
    FOREIGN KEY (passenger_id) REFERENCES passenger(id)
);

-- Insert Test Users (Password: admin123)
INSERT INTO app_user (username, email, password, role) VALUES
('admin', 'admin@example.com', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xdqD1RPHKQZWG392', 'ADMIN'),
('user', 'user@example.com', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xdqD1RPHKQZWG392', 'USER');

-- Insert Airports
INSERT INTO airport (name, country, iata_code) VALUES
('Berlin Brandenburg', 'Germany', 'BER'),
('Zurich Airport', 'Switzerland', 'ZRH'),
('London Heathrow', 'United Kingdom', 'LHR');

-- Insert Aircraft
INSERT INTO aircraft (model, manufacture, gewicht, createdate) VALUES
('Boeing 737', 'Boeing', '41000', NOW()),
('Airbus A320', 'Airbus', '42000', NOW()),
('Embraer 190', 'Embraer', '28000', NOW());

-- Insert Logistic Users
INSERT INTO logistic_user (firstname, lastname, email, createDate) VALUES
('Frodo', 'Beutlin', 'frodo.beutlin@gmail.com', NOW()),
('Elias', 'Kaiser', 'elias@example.com', NOW());

-- Insert Passengers
INSERT INTO passenger (firstname, lastname, email) VALUES
('Lena', 'Schmidt', 'lena.schmidt@example.com'),
('Jonas', 'Müller', 'jonas.mueller@example.com'),
('Mia', 'Schneider', 'mia.schneider@example.com');

-- Insert Flights
INSERT INTO Flight (flight_number, departure_time, arrival_time, aircraft_id, departure_airport_id, arrival_airport_id, logistic_user_id) VALUES
('AB123', '2024-05-01 08:00:00', '2024-05-01 10:00:00', 1, 1, 2, 1),
('CD456', '2024-05-02 09:30:00', '2024-05-02 11:30:00', 2, 2, 3, 2);

-- Insert Bookings
INSERT INTO booking (flight_id, passenger_id, booking_date) VALUES
(1, 1, '2024-04-20 10:00:00'),
(1, 2, '2024-04-21 11:00:00');
