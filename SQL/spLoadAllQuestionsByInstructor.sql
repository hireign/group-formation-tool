DELIMITER $$

DROP PROCEDURE IF EXISTS spLoadAllQuestionsByInstructor $$

CREATE PROCEDURE spLoadAllQuestionsByInstructor (
	IN id BIGINT
)
BEGIN
	SELECT id, title, text, type, createdDate FROM Question where instructorId = id;
END $$

DELIMITER ;