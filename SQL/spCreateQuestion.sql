DELIMITER $$

DROP PROCEDURE IF EXISTS spCreateQuestion $$

CREATE PROCEDURE spCreateQuestion (
	IN title VARCHAR(50),
    IN text VARCHAR(255),
    IN type VARCHAR(20),
    IN instructorId BIGINT,
    OUT id BIGINT
)
BEGIN
	INSERT INTO Question(title, text, type, instructorId)
    VALUES (title, text, type, instructorId);
	SET id = LAST_INSERT_ID();
END $$

DELIMITER ;