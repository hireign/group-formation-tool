DELIMITER $$

DROP PROCEDURE IF EXISTS spLoadSurveyQuestions $$

CREATE PROCEDURE spLoadSurveyQuestions (
	IN courseID BIGINT
)
BEGIN

SELECT Survey.SurveyID, Survey.QuestionID, Survey.Active, Survey.Created_at, QuestionType.type, QuestionTitle.title, QuestionText.text FROM Survey
    JOIN Question ON Survey.QuestionID = Question.id
    JOIN QuestionType ON Question.type = QuestionType.id
    JOIN QuestionTitle ON Question.title = QuestionTitle.id
    JOIN QuestionText ON Question.text = QuestionText.id
    WHERE Survey.SurveyID = (SELECT Course.surveyID FROM Course
    WHERE Course.id = courseID)
    order by Survey.Created_at;

END $$

DELIMITER ;