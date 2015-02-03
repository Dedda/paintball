DROP TABLE IF EXISTS    guest, 
                        reservation, 
                        room, 
                        room_category, 
                        service_reservation, 
                        staff_room, 
                        staff, 
                        staff_service, 
                        staff_category;

CREATE TABLE guest (
    id SERIAL PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    surname VARCHAR(30) NOT NULL
);

CREATE TABLE room_category (
    id SERIAL PRIMARY KEY,
    price INTEGER NOT NULL
);

CREATE TABLE room (
    id SERIAL PRIMARY KEY,
    category INTEGER REFERENCES room_category(id) NOT NULL
);

CREATE TABLE staff_category (
    id SERIAL PRIMARY KEY,
    salary INTEGER NOT NULL,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE staff (
    id SERIAL PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    surname VARCHAR(30) NOT NULL,
    category INTEGER REFERENCES staff_category(id) NOT NULL
);

CREATE TABLE staff_room (
    staff INTEGER REFERENCES staff(id),
    room INTEGER REFERENCES room(id),
    PRIMARY KEY (staff, room)
);

CREATE TABLE optional_service (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
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
    reservation_group INTEGER NOT NULL,
    room INTEGER REFERENCES room(id) NOT NULL,
    guest INTEGER REFERENCES guest(id) NOT NULL,
    arrival DATE NOT NULL,
    departure DATE NOT NULL
);

CREATE TABLE service_reservation (
    service INTEGER REFERENCES optional_service(id),
    reservation INTEGER REFERENCES reservation(id),
    PRIMARY KEY (service, reservation)
);
