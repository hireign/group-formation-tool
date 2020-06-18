DELIMITER $$
DELIMITER $$

DROP PROCEDURE IF EXISTS spLoadAllQuestionsByInstructor $$

CREATE PROCEDURE spLoadAllQuestionsByInstructor (
	IN requestingID BIGINT
)
BEGIN
	SELECT id, title, text, type, createdDate FROM Question where instructorId = requestingID;
END $$

DELIMITER ;