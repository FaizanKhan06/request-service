CREATE TABLE request_entity
(
    request_id INT
    AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR
    (255) NOT NULL,
    community_id INT NOT NULL,
    request_reason VARCHAR
    (255),
    amount DOUBLE,
    request_date_time TIMESTAMP,
    status VARCHAR
    (50)
);
