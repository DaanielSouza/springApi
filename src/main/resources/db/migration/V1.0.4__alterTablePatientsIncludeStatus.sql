ALTER TABLE pacientes ADD status tinyint(1);

UPDATE pacientes SET status = 1 WHERE STATUS IS NULL;

ALTER TABLE pacientes MODIFY status tinyint(1) NOT NULL;