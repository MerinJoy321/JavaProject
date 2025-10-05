-- Schema for Smart Campus Database

-- Create buildings table
CREATE TABLE IF NOT EXISTS buildings (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

-- Create floors table
CREATE TABLE IF NOT EXISTS floors (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    building_id INTEGER NOT NULL,
    floor_number INTEGER NOT NULL,
    FOREIGN KEY (building_id) REFERENCES buildings(id) ON DELETE CASCADE
);

-- Create destinations table
CREATE TABLE IF NOT EXISTS destinations (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    floor_id INTEGER NOT NULL,
    code VARCHAR(50) NOT NULL,
    name VARCHAR(255) NOT NULL,
    x DOUBLE NOT NULL,
    y DOUBLE NOT NULL,
    FOREIGN KEY (floor_id) REFERENCES floors(id) ON DELETE CASCADE
);

-- Create indexes for better performance
CREATE INDEX IF NOT EXISTS idx_floors_building_id ON floors(building_id);
CREATE INDEX IF NOT EXISTS idx_destinations_floor_id ON destinations(floor_id);
