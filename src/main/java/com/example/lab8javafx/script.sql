CREATE TABLE continents (
    id int PRIMARY KEY,
    name varchar(30) NOT NULL UNIQUE
);

CREATE TABLE countries (
    id int PRIMARY KEY,
    name varchar(30) NOT NULL UNIQUE,
    code varchar(8) NOT NULL UNIQUE,
    continent_id int,
    FOREIGN KEY (continent_id) REFERENCES continents(id)
);

CREATE TABLE cities (
    id int PRIMARY KEY,
    name varchar(30) NOT NULL UNIQUE,
    capital bit NOT NULL,
    latitude decimal(10,8) NOT NULL,
    longitude decimal(11,8) NOT NULL,
    country_id int,
    FOREIGN KEY (country_id) REFERENCES countries(id)
);

