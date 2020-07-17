DELIMITER $$

DROP PROCEDURE IF EXISTS spFindOptionsByQuestionID $$

CREATE PROCEDURE spFindOptionsByQuestionID (
	IN qID BIGINT
)
BEGIN
	SELECT QuestionChoice.displayText, QuestionChoice.storedAs
    FROM QuestionChoice JOIN QuestionOption 
    ON QuestionChoice.id = QuestionOption.choiceID 
    WHERE QuestionOption.questionID = qID;
END $$

DELIMITER ;