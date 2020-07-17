DELIMITER $$

DROP PROCEDURE IF EXISTS spDeleteQuestionsByQuestionID $$

CREATE PROCEDURE spDeleteQuestionsByQuestionID(
	IN questionID BIGINT
)
BEGIN
	declare title BIGINT(20);
    declare text BIGINT(20);
	declare choice BIGINT(20);
    
    SELECT Question.title,
    Question.text
    INTO title, text
	FROM Question
	WHERE Question.id = questionID;
	
    SET @choices = (SELECT GROUP_CONCAT(QuestionOption.choiceID) 
					FROM QuestionOption
					WHERE QuestionOption.questionID = questionID);

    DELETE FROM QuestionOption
    WHERE QuestionOption.questionID = questionID;
    
    DELETE FROM QuestionChoice
    WHERE FIND_IN_SET(QuestionChoice.id, @choices);
    
    DELETE from QuestionBank
    WHERE QuestionBank.questionID = questionID;
    
    DELETE from Question
    WHERE Question.id = questionID;
    
    DELETE from QuestionTitle
    WHERE QuestionTitle.id = title;
    
    DELETE from QuestionText
    WHERE QuestionText.id = text;
    
    DELETE from Response
    WHERE Response.questionID = questionID;
    
END$$
DELIMITER ;
