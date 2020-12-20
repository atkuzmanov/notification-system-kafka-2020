CREATE DATABASE db_example;
CREATE USER 'springuser'@'%' IDENTIFIED BY 'ThePassword';
GRANT ALL ON db_example.* to 'springuser'@'%';
GRANT ALL ON db_example.* TO 'springuser'@'localhost';
GRANT ALL ON db_example.* TO 'springuser'@'genesysmysql_container';
GRANT ALL ON db_example.* TO 'springuser'@'genesysmysql';
