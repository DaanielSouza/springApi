ALTER TABLE medicos ADD status tinyint(1);

UPDATE medicos SET status = 1 WHERE STATUS IS NULL;

ALTER TABLE medicos MODIFY status tinyint(1) NOT NULL;