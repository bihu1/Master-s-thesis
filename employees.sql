CREATE TABLE employees (
                            id                  SERIAL PRIMARY KEY,
                            first_name          VARCHAR(100) NOT NULL,
                            last_name           VARCHAR(100) NOT NULL,
                            birth_day           VARCHAR(100) NOT NULL,
                            social_security     INT NOT NULL,
                            active              BOOLEAN NOT NULL,
                            gender              VARCHAR(100) NOT NULL,
                            house_number        VARCHAR(100) NOT NULL,
                            street              VARCHAR(100) NOT NULL,
                            city                VARCHAR(100) NOT NULL,
                            zip_code            VARCHAR(100) NOT NULL
);