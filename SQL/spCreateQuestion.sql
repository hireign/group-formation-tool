DELIMITER $$

DROP PROCEDURE IF EXISTS spCreateQuestion $$

CREATE PROCEDURE spCreateQuestion(
IN title VARCHAR(500),
IN text VARCHAR(500),
IN type VARCHAR(500),
IN bannerID VARCHAR(20)
)
BEGIN

	INSERT into QuestionTitle (title) values (title);
	SET @titleID = LAST_INSERT_ID();

	INSERT into QuestionText (text) values (text);
	SET @textID = LAST_INSERT_ID();

	SET@typeID = (SELECT id from QuestionType
					WHERE QuestionType.type = type);
    
	INSERT into Question (title, text, type) values (@titleID, @textID, @typeID);
	SET @questionID = LAST_INSERT_ID();
    
	SET@userID = (SELECT id from User
					WHERE User.bannerID= bannerID);

	INSERT INTO QuestionBank values (@questionID, @userID);
    
	SELECT @questionID;
    
END$$
DELIMITER ;
