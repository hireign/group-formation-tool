DELIMITER $$

DROP PROCEDURE IF EXISTS spCreateOption $$

CREATE PROCEDURE spCreateOption (
	IN questionId BIGINT,
    IN text VARCHAR(255),
    IN value VARCHAR(20)
)
BEGIN
	INSERT INTO Options(questionId, text, value)
    VALUES (questionId, text, value);
END $$

DELIMITER ;