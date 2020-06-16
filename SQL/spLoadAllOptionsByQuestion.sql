DELIMITER $$

DROP PROCEDURE IF EXISTS spLoadAllOptionsByQuestion $$

CREATE PROCEDURE spLoadAllOptionsByQuestion (
	IN id BIGINT
)
BEGIN
	SELECT questionId, text, value FROM Options where questionId = id;
END $$

DELIMITER ;