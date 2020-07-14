DELIMITER $$

DROP PROCEDURE IF EXISTS spFindQuestionsByCourseID $$

CREATE PROCEDURE spFindQuestionsByCourseID (
	IN courseID BIGINT
)
BEGIN
	SELECT Survey.SurveyID, Survey.QuestionID, Survey.Active, Survey.Created_at, QuestionType.type, Question.title, Question.text, Question.timestamp FROM Survey 
    JOIN Question ON Survey.QuestionID = Question.id JOIN QuestionType ON Question.type = QuestionType.id WHERE Survey.SurveyID = (SELECT Course.surveyID
    FROM Course
    WHERE Course.id = courseID);
END $$

DELIMITER ;