DELIMITER $$

DROP PROCEDURE IF EXISTS spFindUsersWithQuestionID $$

CREATE PROCEDURE spFindUsersWithQuestionID (
	IN surveyID BIGINT,
	IN questionID BIGINT
)
BEGIN
	SELECT response
    FROM Response
    WHERE Response.surveyID = surveyID AND Response.questionID=questionID;

END $$

DELIMITER ;