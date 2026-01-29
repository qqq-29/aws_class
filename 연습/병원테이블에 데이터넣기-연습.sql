LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/1.csv'
INTO TABLE hos
CHARACTER SET utf8mb4
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
IGNORE 1 ROWS
(@col1, hospital_name, tel, address);
