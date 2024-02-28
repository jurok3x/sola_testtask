ALTER TABLE connectors
ADD CONSTRAINT fk_power_station
FOREIGN KEY (power_station_id) REFERENCES power_stations(id);