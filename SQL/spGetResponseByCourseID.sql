DELIMITER $$

DROP PROCEDURE IF EXISTS spGetResponseByCourseID;

CREATE PROCEDURE spGetResponseByCourseID (
	IN courseID BIGINT
)
BEGIN
	SELECT * FROM Response WHERE SurveyID =(SELECT surveyID
    FROM Course
    WHERE id = courseID);
END$$
DELIMITER ;