
DROP TABLE IF EXISTS trips;

CREATE TABLE trips (
    start_time			timestamp without time zone,
    stop_time 			timestamp without time zone,
    start_station_id 		integer,
    start_station_name 		text,
    start_station_latitude 	double precision,
    start_station_longitude 	double precision,
    end_station_id 		integer,
    end_station_name 		text,
    end_station_latitude 	double precision,
    end_station_longitude 	double precision,
    bike_id 			integer,
    user_type 			text,
    birth_year 			integer,
    gender 			integer
);

