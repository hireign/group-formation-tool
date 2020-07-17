DELIMITER $$

DROP PROCEDURE IF EXISTS spDeleteQuestionFromSurvey $$

CREATE PROCEDURE spDeleteQuestionFromSurvey (
	IN questionParamId BIGINT,
	IN courseParamId BIGINT
)
BEGIN

SET SQL_SAFE_UPDATES = 0;
SET FOREIGN_KEY_CHECKS=0;
DELETE FROM Survey WHERE (QuestionID = questionParamId AND surveyID = courseParamId);
DELETE FROM Response WHERE (questionID = questionParamId AND surveyID = courseParamId);
SET SQL_SAFE_UPDATES = 1;
SET FOREIGN_KEY_CHECKS=1;

END