DELIMITER $$

DROP PROCEDURE IF EXISTS spFetchPreviousPasswords $$

CREATE PROCEDURE `spFetchPreviousPasswords`(
IN id VARCHAR(20), 
IN historyCount INT (10)
)
BEGIN
	
	SELECT User.password
	FROM UserPasswordHistory
	JOIN User ON UserPasswordHistory.userID = user.id
	WHERE User.bannerID = id
	ORDER BY UserPassWordHistory.timestamp DESC
	LIMIT historyCount;

END$$
DELIMITER ;

