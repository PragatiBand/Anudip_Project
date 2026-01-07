CREATE DATABASE mmt;
USE mmt;

-- FLIGHT BOOKINGS
CREATE TABLE flight_booking (
    booking_id INT PRIMARY KEY,
    passenger_name VARCHAR(100),
    flight_name VARCHAR(100),
    source VARCHAR(50),
    destination VARCHAR(50),
    seat_class VARCHAR(50),
    status VARCHAR(50)
);

-- HOTEL BOOKINGS
CREATE TABLE hotel_booking (
    booking_id INT PRIMARY KEY,
    customer_name VARCHAR(100),
    hotel_name VARCHAR(100),
    room_type VARCHAR(50),
    checkin_date VARCHAR(20),
    checkout_date VARCHAR(20),
    status VARCHAR(50)
);

-- BUS BOOKINGS
CREATE TABLE bus_booking (
    booking_id INT PRIMARY KEY,
    passenger_name VARCHAR(100),
    bus_name VARCHAR(100),
    source VARCHAR(50),
    destination VARCHAR(50),
    seat_no VARCHAR(10),
    status VARCHAR(50)
);
