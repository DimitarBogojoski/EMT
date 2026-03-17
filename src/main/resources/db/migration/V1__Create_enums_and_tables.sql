CREATE TYPE accommodation_category AS ENUM ('ROOM', 'HOUSE', 'FLAT', 'APARTMENT', 'HOTEL', 'MOTEL');
CREATE TYPE accommodation_condition AS ENUM ('GOOD', 'BAD');

CREATE TABLE countries (
                           id BIGSERIAL PRIMARY KEY,
                           name VARCHAR(255) NOT NULL,
                           continent VARCHAR(255) NOT NULL
);

CREATE TABLE hosts (
                       id BIGSERIAL PRIMARY KEY,
                       created_at TIMESTAMP,
                       updated_at TIMESTAMP,
                       name VARCHAR(255) NOT NULL,
                       surname VARCHAR(255) NOT NULL,
                       country_id BIGINT REFERENCES countries(id)
);

CREATE TABLE accommodations (
                                id BIGSERIAL PRIMARY KEY,
                                created_at TIMESTAMP,
                                updated_at TIMESTAMP,
                                name VARCHAR(255) NOT NULL,
                                category accommodation_category NOT NULL,
                                host_id BIGINT REFERENCES hosts(id),
                                num_rooms INTEGER NOT NULL,
                                condition accommodation_condition NOT NULL DEFAULT 'GOOD'
);
