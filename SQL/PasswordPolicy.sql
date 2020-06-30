INSERT INTO `PasswordPolicies`
(`idPasswordPolicies`, `Minimum_length`, `Maximum_length`, `Minimum_uppercase_Chars`, `Minimum_lowercase_Chars`, `Minimum_special_characters`, `Disallowed_Chars`, `Password_History_Constraint_No`, `Enable_Policy`)
VALUES
(1, 4, 20, 1, 2, 1, ',/', 3, 1);

commit;