DELIMITER $$

DROP PROCEDURE IF EXISTS spCreateOptions $$

CREATE PROCEDURE spCreateOptions(
IN questionID BIGINT(20),
IN text VARCHAR(500),
IN storedAs VARCHAR(500),
IN displayOrder INT(11)
)
BEGIN

	INSERT into QuestionChoice (displayText, storedAs) values (text, storedAs);
	SET @choiceID = LAST_INSERT_ID();
    
    INSERT INTO QuestionOption values (questionID, @choiceID, displayOrder);

END$$
DELIMITER ;
