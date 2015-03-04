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