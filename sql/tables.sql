DROP TABLE IF EXISTS    guest, 
                        reservation, 
                        room, 
                        room_category, 
                        service_reservation, 
                        staff_room, 
                        staff, 
                        staff_service, 
                        staff_category,
                        optional_service,
                        room_reservation
CASCADE;

CREATE TABLE guest (
    id SERIAL PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    surname VARCHAR(30) NOT NULL
);

CREATE TABLE room_category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(30) NOT NULL UNIQUE,
    price INTEGER NOT NULL
);

CREATE TABLE room (
    id SERIAL PRIMARY KEY,
    people INTEGER NOT NULL,
    category INTEGER REFERENCES room_category(id) NOT NULL
);

CREATE TABLE staff_category (
    id SERIAL PRIMARY KEY,
    salary INTEGER NOT NULL,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE staff (
    id SERIAL PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    surname VARCHAR(30) NOT NULL,
    category INTEGER REFERENCES staff_category(id) NOT NULL,
    recruitement DATE NOT NULL,
    firing DATE
);

CREATE TABLE staff_room (
    staff INTEGER REFERENCES staff(id),
    room INTEGER REFERENCES room(id),
    PRIMARY KEY (staff, room)
);

CREATE TABLE optional_service (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    price INTEGER NOT NULL
);

CREATE TABLE staff_service (
    staff INTEGER REFERENCES staff(id),
    service INTEGER REFERENCES optional_service(id),
    PRIMARY KEY (staff, service)
);

CREATE TABLE reservation (
    id SERIAL PRIMARY KEY,
    additional_info TEXT,
    guest INTEGER REFERENCES guest(id) NOT NULL,
    people INTEGER NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    canceled DATE,
    payed DATE
);

CREATE TABLE room_reservation (
    room INTEGER REFERENCES room(id),
    reservation INTEGER REFERENCES reservation(id),
    PRIMARY KEY (room, reservation)
);

CREATE TABLE service_reservation (
    service INTEGER REFERENCES optional_service(id),
    reservation INTEGER REFERENCES reservation(id),
    amount INTEGER NOT NULL DEFAULT 1,
    PRIMARY KEY (service, reservation)
);
