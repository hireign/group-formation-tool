DELIMITER $$

DROP PROCEDURE IF EXISTS spLoadConstraintByValidator $$

CREATE PROCEDURE spLoadConstraintByValidator(
id BIGINT(20)
)
BEGIN
	SELECT value 
	FROM PasswordValidatorInfo
	WHERE PasswordValidatorInfo.validatorID = id;
END$$
DELIMITER ;
