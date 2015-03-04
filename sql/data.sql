-- Guests
INSERT INTO guest(id, name, surname)
VALUES(1, 'Hans', 'Zimmer');

INSERT INTO guest(id, name, surname)
VALUES(2, 'Horst', 'Klein');

-- Room Categories
INSERT INTO room_category(id, name, price)
VALUES(1, 'Basic', 500);

INSERT INTO room_category(id, name, price)
VALUES(2, 'Luxus', 2000);

INSERT INTO room_category(id, name, price)
VALUES(3, 'Giga', 10000);

-- Rooms
INSERT INTO room(id, people, category)
VALUES(1, 2, 1);

INSERT INTO room(id, people, category)
VALUES(2, 300, 3);

INSERT INTO room(id, people, category)
VALUES(3, 5, 2);

--Staff Categories
INSERT INTO staff_category(id, salary, name)
VALUES(1, 20000, 'Bozz');

INSERT INTO staff_category(id, salary, name)
VALUES(2, 50, 'Putze');

-- Staff
INSERT INTO staff(id, name, surname, category, recruitement)
VALUES(1, 'Herbert', 'Herbertsen', 1, '1961-06-16');

INSERT INTO staff(id, name, surname, category, recruitement, firing)
VALUES(2, 'Hassan', 'Assi', 2, '1990-01-01', '2010-01-01');

INSERT INTO staff(id, name, surname, category, recruitement)
VALUES(3, 'Mustafa', 'Kanacke', 2, '1990-01-01');

-- Optional Services
INSERT INTO optional_service(id, name, price)
VALUES(1, 'Bootstour', 45);

INSERT INTO optional_service(id, name, price)
VALUES(2, 'Massage', 20);

-- Staff to Room

INSERT INTO staff_room(staff, room)
VALUES(2, 1);

INSERT INTO staff_room(staff, room)
VALUES(2, 2);

INSERT INTO staff_room(staff, room)
VALUES(3, 3);

-- Staff to Services

INSERT INTO staff_service(staff, service)
VALUES(2, 1);

INSERT INTO staff_service(staff, service)
VALUES(3, 2);

-- Reservations

INSERT INTO reservation(id, additional_info, reservation_group, room, guest, people, start_date, end_date)
VALUES(1, '', 1, 2, 2, 12, '2015-01-01', '2015-12-31');

INSERT INTO reservation(id, additional_info, reservation_group, room, guest, people, start_date, end_date)
VALUES(2, '', 2, 1, 1, 3, '2015-03-02', '2015-06-01');

-- Services to Reservations

INSERT INTO service_reservation(service, reservation)
VALUES(2, 1);