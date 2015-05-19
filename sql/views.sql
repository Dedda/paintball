-- reservations at any date which aren't payed yet.

DROP VIEW IF EXISTS not_payed_reservations;
CREATE VIEW not_payed_reservations AS
SELECT * FROM reservation
WHERE payed IS NULL;

-- currently hired staff

DROP VIEW IF EXISTS current_staff;
CREATE VIEW current_staff AS
SELECT * FROM staff
WHERE recruitement < now()
AND firing IS NULL
OR firing > now();

-- get open reservations
DROP VIEW IF EXISTS open_reservations;
CREATE VIEW open_reservations AS
SELECT * FROM reservation
WHERE canceled IS NULL
AND payed IS NULL;

-- all guests responsible for all current bookings

DROP VIEW IF EXISTS current_guests;
CREATE VIEW current_guests AS
SELECT guest.* FROM guest
INNER JOIN reservation
ON guest.id = reservation.guest 
WHERE now() BETWEEN reservation.start_date AND reservation.end_date
AND reservation.canceled IS NULL;

-- number of guests in the whole hotel

DROP VIEW IF EXISTS current_guest_number;
CREATE VIEW current_guest_number AS
SELECT sum(people) FROM reservation
WHERE now() BETWEEN reservation.start_date AND reservation.end_date
AND reservation.canceled IS NULL;

-- rooms with categories

DROP VIEW IF EXISTS rooms_with_category;
CREATE VIEW rooms_with_category AS
SELECT 
    r.id AS room_id, 
    r.people AS people,
    cat.id AS category_id,
    cat.name AS category_name,
    cat.price AS price FROM room r
INNER JOIN room_category cat
ON r.category = cat.id;

-- staff with category
DROP VIEW IF EXISTS staff_with_category;
CREATE VIEW staff_with_category AS
SELECT
    s.id AS staff_id,
    s.name AS name,
    s.surname AS surname,
    s.recruitement AS recruitement,
    s.firing AS firing,
    cat.id AS cat_id,
    cat.salary AS salary,
    cat.name AS cat_name FROM staff s
INNER JOIN staff_category cat
ON s.category = cat.id;

-- services for reservations
DROP VIEW IF EXISTS services_for_reservation;
CREATE VIEW services_for_reservation AS
SELECT
    s.id AS service_id,
    s.name AS service_name,
    s.price AS service_price,
    reservation.id AS reservation_id FROM optional_service s
INNER JOIN service_reservation
ON service_reservation.service = s.id
INNER JOIN reservation
ON service_reservation.reservation = reservation.id;

-- reservations for rooms
DROP VIEW IF EXISTS reservations_for_room;
CREATE VIEW reservations_for_room AS
SELECT
    reservation.id AS reservation_id,
    room.id AS room_id;
    FROM reservation
INNER JOIN room_reservation
ON room_reservation.reservation = reservation.id
INNER JOIN room
ON room_reservation.room = room.id;