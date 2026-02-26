DROP DATABASE IF EXISTS airdatabase;
CREATE DATABASE airdatabase;
USE airdatabase;

CREATE TABLE passenger (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE logistic_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    create_date DATETIME
);

CREATE TABLE airport (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    country VARCHAR(50),
    iata_code VARCHAR(10)
);

CREATE TABLE aircraft (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    model VARCHAR(100),
    manufacture VARCHAR(100),
    gewicht VARCHAR(100),
    createdate DATETIME
);

CREATE TABLE flight (
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

CREATE TABLE booking (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    flight_id BIGINT,
    passenger_id BIGINT,
    booking_date DATETIME,
    FOREIGN KEY (flight_id) REFERENCES flight(id),
    FOREIGN KEY (passenger_id) REFERENCES passenger(id)
);

INSERT INTO airport (name, country, iata_code)
VALUES ('Berlin Brandenburg', 'Germany', 'BER');
INSERT INTO aircraft (model, manufacture, gewicht, createdate)
VALUES 
('Boeing 737', 'Boeing', '41000', NOW()),
('Airbus A320', 'Airbus', '42000', NOW()),
('Boeing B-17','Boeing','32.500', NOW()),
('Luftschraube 1','Da Vinci Air','50.000', NOW()),
('Embraer 190', 'Embraer', '28000', NOW());
-- Beispiel: Logistic User
INSERT INTO logistic_user (firstname, lastname, email, create_date) VALUES
('Frodo', 'Beutlin', 'frodo.beutlin@gmail.com', NOW()),
('Elias', 'Kaiser', 'elias@example.com', NOW());
-- Beispiel passenger
INSERT INTO passenger (firstname, lastname, email) VALUES
('Lena', 'Schmidt', 'lena.schmidt@example.com'),
('Jonas', 'Müller', 'jonas.mueller@example.com'),
('Sofia', 'Fischer', 'sofia.fischer@example.com'),
('David', 'Weber', 'david.weber@example.com'),
('Brand', 'Bombe', 'Royal.Airforce@example.com'),
('Spreng', 'Bombe', 'Royal.Airforce@example.com'),
('Mia', 'Schneider', 'mia.schneider@example.com');

-- Beispiele flights
INSERT INTO flight (flight_number, departure_time, arrival_time, aircraft_id, departure_airport_id, arrival_airport_id, logistic_user_id) VALUES
('AB123', '2024-05-01 08:00:00', '2024-05-01 10:00:00', 1, 1, 1, 1),
('CD456', '2024-05-02 09:30:00', '2024-05-02 11:30:00', 2, 1, 1, 2),
('EF789', '2024-05-03 14:00:00', '2024-05-03 16:30:00', 3, 1, 1, 1),
('GH012', '2024-05-04 07:15:00', '2024-05-04 09:45:00', 4, 1, 1, 2),
('IJ345', '2024-05-05 12:00:00', '2024-05-05 14:00:00', 5, 1, 1, 1);

-- Beispiele booking

INSERT INTO booking (flight_id, passenger_id, booking_date) VALUES
(1, 1, '2024-04-20 10:00:00'),
(2, 2, '2024-04-21 11:00:00'),
(3, 3, '2024-04-22 12:00:00'),
(4, 4, '2024-04-23 13:00:00'),
(5, 5, '2024-04-24 14:00:00');
