-- Guests
INSERT INTO guest 
VALUES(1, 'Hans', 'Zimmer');

INSERT INTO guest
VALUES(2, 'Horst', 'Klein');

-- Room Categories
INSERT INTO room_category
VALUES(1, 'Basic', 500);

INSERT INTO room_category
VALUES(2, 'Luxus', 2000);

INSERT INTO room_category
VALUES(3, 'Giga', 10000);

-- Rooms
INSERT INTO room
VALUES(1, 2, 1);

INSERT INTO room
VALUES(2, 300, 3);

INSERT INTO room
VALUES(3, 5, 2);

--Staff Categories
INSERT INTO staff_category
VALUES(1, 20000, 'Bozz');

INSERT INTO staff_category
VALUES(2, 50, 'Putze');

-- Staff
INSERT INTO staff (id, name, surname, category, recruitement)
VALUES(1, 'Herbert', 'Herbertsen', 1, '1961-06-16');

INSERT INTO staff
VALUES(2, 'Hassan', 'Assi', 2, '1990-01-01', '2010-01-01');

INSERT INTO staff (id, name, surname, category, recruitement)
VALUES(3, 'Mustafa', 'Kanacke', 2, '1990-01-01');