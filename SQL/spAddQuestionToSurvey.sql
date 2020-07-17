DELIMITER $$

DROP PROCEDURE IF EXISTS spAddQuestionToSurvey $$

CREATE PROCEDURE spAddQuestionToSurvey (
	IN questionParamId BIGINT,
    IN courseParamId BIGINT,
	IN instructorParamId BIGINT
)
BEGIN

INSERT INTO Survey (SurveyID, QuestionID, UserID, Active) VALUES(courseParamId,questionParamId,instructorParamId,0);

END $$

DELIMITER ;