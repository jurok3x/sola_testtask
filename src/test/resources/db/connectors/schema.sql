CREATE TABLE connectors (
    id UUID PRIMARY KEY,
    type VARCHAR(10),
    max_power INTEGER,
    power_station_id UUID
);