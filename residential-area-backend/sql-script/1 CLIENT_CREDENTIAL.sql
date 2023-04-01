DROP TABLE IF EXISTS CLIENT_CREDENTIAL;

CREATE TABLE CLIENT_CREDENTIAL (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    USERNAME VARCHAR(50) NOT NULL UNIQUE,
    PASSWORD VARCHAR(100) NOT NULL,
    CREATED_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CREATED_BY VARCHAR(100) NOT NULL
);

--Insert multiple sample data
INSERT INTO CLIENT_CREDENTIAL (USERNAME, PASSWORD, CREATED_DATE, CREATED_BY)
VALUES ("root", "root", NOW(), "SYSTEM");