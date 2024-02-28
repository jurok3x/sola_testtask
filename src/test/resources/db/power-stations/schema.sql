CREATE TABLE power_stations (
    id UUID PRIMARY KEY,
    is_public BOOLEAN,
    title VARCHAR(255),
    description VARCHAR(255),
    address VARCHAR(255),
    coordinates VARCHAR(255)
);