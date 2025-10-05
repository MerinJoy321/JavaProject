-- Initial data for Smart Campus Database

-- Insert buildings
INSERT INTO buildings (name) VALUES ('A Block');
INSERT INTO buildings (name) VALUES ('B Block');
INSERT INTO buildings (name) VALUES ('C Block');
INSERT INTO buildings (name) VALUES ('D Block');

-- Insert floors for A Block (id=1)
INSERT INTO floors (building_id, floor_number) VALUES (1, 0);
INSERT INTO floors (building_id, floor_number) VALUES (1, 1);

-- Insert floors for B Block (id=2)
INSERT INTO floors (building_id, floor_number) VALUES (2, 0);
INSERT INTO floors (building_id, floor_number) VALUES (2, 1);

-- Insert floors for C Block (id=3)
INSERT INTO floors (building_id, floor_number) VALUES (3, 0);
INSERT INTO floors (building_id, floor_number) VALUES (3, 1);

-- Insert floors for D Block (id=4)
INSERT INTO floors (building_id, floor_number) VALUES (4, 0);
INSERT INTO floors (building_id, floor_number) VALUES (4, 1);

-- Insert destinations for A Block Floor 0 (floor_id=1)
INSERT INTO destinations (floor_id, code, name, x, y) VALUES (1, 'A001', 'Room 001', 100, 110);
INSERT INTO destinations (floor_id, code, name, x, y) VALUES (1, 'A002', 'Lab 002', 400, 90);
INSERT INTO destinations (floor_id, code, name, x, y) VALUES (1, 'A003', 'Room 003', 100, 340);
INSERT INTO destinations (floor_id, code, name, x, y) VALUES (1, 'A004', 'Office 004', 400, 360);
INSERT INTO destinations (floor_id, code, name, x, y) VALUES (1, 'AST', 'Stairs', 250, 250);

-- Insert destinations for A Block Floor 1 (floor_id=2)
INSERT INTO destinations (floor_id, code, name, x, y) VALUES (2, 'A101', 'Room 101', 100, 120);
INSERT INTO destinations (floor_id, code, name, x, y) VALUES (2, 'A102', 'Lab 102', 400, 80);
INSERT INTO destinations (floor_id, code, name, x, y) VALUES (2, 'A103', 'Room 103', 100, 330);
INSERT INTO destinations (floor_id, code, name, x, y) VALUES (2, 'A104', 'Office 104', 400, 370);
INSERT INTO destinations (floor_id, code, name, x, y) VALUES (2, 'AST', 'Stairs', 250, 250);

-- Insert destinations for B Block Floor 0 (floor_id=3)
INSERT INTO destinations (floor_id, code, name, x, y) VALUES (3, 'B001', 'Room 001', 100, 110);
INSERT INTO destinations (floor_id, code, name, x, y) VALUES (3, 'B002', 'Lab 002', 400, 90);
INSERT INTO destinations (floor_id, code, name, x, y) VALUES (3, 'B003', 'Room 003', 100, 340);
INSERT INTO destinations (floor_id, code, name, x, y) VALUES (3, 'B004', 'Office 004', 400, 360);
INSERT INTO destinations (floor_id, code, name, x, y) VALUES (3, 'BST', 'Stairs', 250, 250);

-- Insert destinations for B Block Floor 1 (floor_id=4)
INSERT INTO destinations (floor_id, code, name, x, y) VALUES (4, 'B101', 'Room 101', 100, 120);
INSERT INTO destinations (floor_id, code, name, x, y) VALUES (4, 'B102', 'Lab 102', 400, 80);
INSERT INTO destinations (floor_id, code, name, x, y) VALUES (4, 'B103', 'Room 103', 100, 330);
INSERT INTO destinations (floor_id, code, name, x, y) VALUES (4, 'B104', 'Office 104', 400, 370);
INSERT INTO destinations (floor_id, code, name, x, y) VALUES (4, 'BST', 'Stairs', 250, 250);

-- Insert destinations for C Block Floor 0 (floor_id=5)
INSERT INTO destinations (floor_id, code, name, x, y) VALUES (5, 'C001', 'Room 001', 100, 110);
INSERT INTO destinations (floor_id, code, name, x, y) VALUES (5, 'C002', 'Lab 002', 400, 90);
INSERT INTO destinations (floor_id, code, name, x, y) VALUES (5, 'C003', 'Room 003', 100, 340);
INSERT INTO destinations (floor_id, code, name, x, y) VALUES (5, 'C004', 'Office 004', 400, 360);
INSERT INTO destinations (floor_id, code, name, x, y) VALUES (5, 'CST', 'Stairs', 250, 250);

-- Insert destinations for C Block Floor 1 (floor_id=6)
INSERT INTO destinations (floor_id, code, name, x, y) VALUES (6, 'C101', 'Room 101', 100, 120);
INSERT INTO destinations (floor_id, code, name, x, y) VALUES (6, 'C102', 'Lab 102', 400, 80);
INSERT INTO destinations (floor_id, code, name, x, y) VALUES (6, 'C103', 'Room 103', 100, 330);
INSERT INTO destinations (floor_id, code, name, x, y) VALUES (6, 'C104', 'Office 104', 400, 370);
INSERT INTO destinations (floor_id, code, name, x, y) VALUES (6, 'CST', 'Stairs', 250, 250);

-- Insert destinations for D Block Floor 0 (floor_id=7)
INSERT INTO destinations (floor_id, code, name, x, y) VALUES (7, 'D001', 'Room 001', 100, 110);
INSERT INTO destinations (floor_id, code, name, x, y) VALUES (7, 'D002', 'Lab 002', 400, 90);
INSERT INTO destinations (floor_id, code, name, x, y) VALUES (7, 'D003', 'Room 003', 100, 340);
INSERT INTO destinations (floor_id, code, name, x, y) VALUES (7, 'D004', 'Office 004', 400, 360);
INSERT INTO destinations (floor_id, code, name, x, y) VALUES (7, 'DST', 'Stairs', 250, 250);

-- Insert destinations for D Block Floor 1 (floor_id=8)
INSERT INTO destinations (floor_id, code, name, x, y) VALUES (8, 'D101', 'Room 101', 100, 120);
INSERT INTO destinations (floor_id, code, name, x, y) VALUES (8, 'D102', 'Lab 102', 400, 80);
INSERT INTO destinations (floor_id, code, name, x, y) VALUES (8, 'D103', 'Room 103', 100, 330);
INSERT INTO destinations (floor_id, code, name, x, y) VALUES (8, 'D104', 'Office 104', 400, 370);
INSERT INTO destinations (floor_id, code, name, x, y) VALUES (8, 'DST', 'Stairs', 250, 250);
