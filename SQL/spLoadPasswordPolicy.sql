DELIMITER $$

DROP PROCEDURE IF EXISTS spLoadPasswordPolicy $$

CREATE PROCEDURE spLoadPasswordPolicy()
BEGIN
	SELECT
		P.Minimum_length AS Minimum_length,
		P.Maximum_length AS Maximum_length,
        P.Minimum_uppercase_Chars AS Minimum_uppercase_Chars,
        P.Minimum_lowercase_Chars AS Minimum_lowercase_Chars,
        P.Minimum_special_characters AS Minimum_special_characters,
        P.Disallowed_Chars AS Disallowed_Chars,
        P.Password_History_Constraint_No AS Password_History_Constraint_No
	FROM PasswordPolicies P
    WHERE Enable_Policy=true;
END $$

DELIMITER ;