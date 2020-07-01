DELIMITER $$

DROP PROCEDURE IF EXISTS spLoadActivePasswordValidators $$

CREATE PROCEDURE spLoadActivePasswordValidators()
BEGIN
	SELECT PasswordValidator.id, 
    	PasswordValidator.name 
    	FROM PasswordValidator
	JOIN PasswordValidatorInfo ON PasswordValidator.id = PasswordValidatorInfo.validatorID
	WHERE PasswordValidatorInfo.isEnabled=1;
END$$
DELIMITER ;
