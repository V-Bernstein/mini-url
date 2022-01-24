CREATE TABLE url (
    id INT NOT NULL AUTO_INCREMENT, 
    mini VARCHAR(6) NOT NULL,
    full_url VARCHAR(255) NOT NULL,
    updated_timestamp TIMESTAMP NOT NULL,
    PRIMARY KEY(id)
);

CREATE UNIQUE INDEX mini_idx ON url (mini);