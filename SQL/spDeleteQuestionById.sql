DELIMITER $$

DROP PROCEDURE IF EXISTS spDeleteQuestionById $$

CREATE PROCEDURE spDeleteQuestionById (
    IN queId BIGINT
)
BEGIN
	Delete from Options where questionId = queId;
	Delete from Question where id = queId;
END $$

DELIMITER ;