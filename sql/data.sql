-- Guests
INSERT INTO guest(name, surname)
VALUES('Nicht', 'Vorhanden');

INSERT INTO guest(name, surname)
VALUES('Hans', 'Zimmer');

INSERT INTO guest(name, surname)
VALUES('Horst', 'Klein');

-- Room Categories
INSERT INTO room_category(name, price)
VALUES('Basic', 500);

INSERT INTO room_category(name, price)
VALUES('Luxus', 2000);

INSERT INTO room_category(name, price)
VALUES('Giga', 10000);

-- Rooms
INSERT INTO room(people, category)
VALUES(2, 1);

INSERT INTO room(people, category)
VALUES(300, 3);

INSERT INTO room(people, category)
VALUES(5, 2);

--Staff Categories
INSERT INTO staff_category(salary, name)
VALUES(0, 'Nicht Vorhanden');

INSERT INTO staff_category(salary, name)
VALUES(20000, 'Bozz');

INSERT INTO staff_category(salary, name)
VALUES(800, 'Putze');

INSERT INTO staff_category(salary, name)
VALUES(1500, 'Receptionist');

-- Staff
INSERT INTO staff(name, surname, category, recruitement)
VALUES('Nicht', 'Vorhanden', 1, '1990-01-01');

INSERT INTO staff(name, surname, category, recruitement)
VALUES('Herbert', 'Herbertsen', 2, '1961-06-16');

INSERT INTO staff(name, surname, category, recruitement, firing)
VALUES('Hassan', 'Assi', 3, '1990-01-01', '2010-01-01');

INSERT INTO staff(name, surname, category, recruitement)
VALUES('Mustafa', 'Kanacke', 3, '1990-01-01');

-- Optional Services
INSERT INTO optional_service(name, price)
VALUES('Bootstour', 45);

INSERT INTO optional_service(name, price)
VALUES('Massage', 20);

-- Staff to Room

INSERT INTO staff_room(staff, room)
VALUES(2, 1);

INSERT INTO staff_room(staff, room)
VALUES(2, 2);

INSERT INTO staff_room(staff, room)
VALUES(3, 3);

INSERT INTO staff_room(staff, room)
VALUES(4, 3);

-- Staff to Services

INSERT INTO staff_service(staff, service)
VALUES(2, 1);

INSERT INTO staff_service(staff, service)
VALUES(3, 2);

-- Reservations

INSERT INTO reservation(additional_info, reservation_group, guest, people, start_date, end_date)
VALUES('', 1, 3, 12, '2015-01-01', '2015-12-31');

INSERT INTO reservation(additional_info, reservation_group, guest, people, start_date, end_date)
VALUES('', 2, 2, 3, '2015-03-02', '2015-06-01');

-- Rooms to Reservations

INSERT INTO room_reservation(room, reservation)
VALUES(2, 1);

INSERT INTO room_reservation(room, reservation)
VALUES(1, 2);

-- Services to Reservations

INSERT INTO service_reservation(service, reservation)
VALUES(2, 1);

