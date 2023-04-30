DROP TABLE IF EXISTS STATEMENT;

CREATE TABLE STATEMENT (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    YEAR INT NOT NULL,
    MONTH INT NOT NULL,
    STATUS VARCHAR(50) NOT NULL,
    RESIDENT_ID INT,
    FOREIGN KEY (RESIDENT_ID) REFERENCES RESIDENT(ID)
);