DELIMITER $$

DROP PROCEDURE IF EXISTS spLoadHistoricalPasswords $$

CREATE PROCEDURE `spLoadHistoricalPasswords`(
	IN ID BIGINT,
	IN historicalConstraint int
)
BEGIN
	SELECT Password FROM UserPasswordArchive
    WHERE UserID = ID
    order by created_at DESC
    Limit historicalConstraint;
END$$
DELIMITER ;