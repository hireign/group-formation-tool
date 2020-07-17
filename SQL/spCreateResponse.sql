DELIMITER $$

DROP PROCEDURE IF EXISTS spCreateResponse $$

CREATE PROCEDURE spCreateResponse(
IN id BIGINT,
IN questionID BIGINT,
IN userID BIGINT,
IN response VARCHAR(500),
IN surveyID BIGINT
)
BEGIN
	INSERT into Response (id, questionID, userID, response, surveyID) values (id, questionID, userID, response, surveyID);
END$$
DELIMITER ;
