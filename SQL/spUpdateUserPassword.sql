DELIMITER $$

DROP PROCEDURE IF EXISTS spUpdateUserPassword $$

CREATE PROCEDURE `spUpdateUserPassword`(
	IN UserID BIGINT,
	IN NewPASSWORD VARCHAR(76)
)
BEGIN
Update User set password = NewPASSWORD
where id = UserID;
INSERT INTO UserPasswordArchive(userID, password)
VALUES (UserID, NewPASSWORD);
END$$
DELIMITER ;