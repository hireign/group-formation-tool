DELIMITER $$

DROP PROCEDURE IF EXISTS spFindSortedQuestionsByTitle $$

CREATE PROCEDURE spFindSortedQuestionsByTitle(
	IN bannerID VARCHAR(45)
)
BEGIN
    
    SELECT Question.id, 
    QuestionTitle.title, 
    QuestionText.text, 
    QuestionType.type, 
    Question.timestamp
    FROM User
    JOIN QuestionBank ON User.id = QuestionBank.userID
    JOIN Question ON Question.id = QuestionBank.questionID
    JOIN QuestionType ON Question.type = QuestionType.id
    JOIN QuestionTitle ON Question.title = QuestionTitle.id
    JOIN QuestionText ON Question.text = QuestionText.id
    WHERE User.bannerID = bannerID
    ORDER BY QuestionTitle.title;
    
END$$
DELIMITER ;
