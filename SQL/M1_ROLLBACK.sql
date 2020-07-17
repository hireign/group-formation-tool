DROP PROCEDURE IF EXISTS spAddInstructorToCourse;
DROP PROCEDURE IF EXISTS spCreateCourse;
DROP PROCEDURE IF EXISTS spCreateUser;
DROP PROCEDURE IF EXISTS spDeleteCourse;
DROP PROCEDURE IF EXISTS spFindUserByBannerID;
DROP PROCEDURE IF EXISTS spFindUserByEmail;
DROP PROCEDURE IF EXISTS spFindUsersByName;
DROP PROCEDURE IF EXISTS spFindUsersWithCourseRole;
DROP PROCEDURE IF EXISTS spFindUsersWithSystemRole;
DROP PROCEDURE IF EXISTS spLoadUser;
DROP PROCEDURE IF EXISTS spUpdateUser;
DROP PROCEDURE IF EXISTS spLoadAllCourses;
DROP PROCEDURE IF EXISTS spEnrollUser;
DROP PROCEDURE IF EXISTS spFindCourseByID;
DROP PROCEDURE IF EXISTS spFindUsersWithoutCourseRole;
DROP PROCEDURE IF EXISTS spLoadUserRolesForCourse;
DROP PROCEDURE IF EXISTS spLoadActivePasswordValidators;
DROP PROCEDURE IF EXISTS spLoadConstraintByValidator;
DROP PROCEDURE IF EXISTS spFetchPreviousPasswords;
DROP PROCEDURE IF EXISTS spCreateOptions;
DROP PROCEDURE IF EXISTS spCreateQuestion;
DROP PROCEDURE IF EXISTS spDeleteQuestionsByQuestionID;
DROP PROCEDURE IF EXISTS spFindSortedQuestionsByDate;
DROP PROCEDURE IF EXISTS spFindSortedQuestionsByTitle;

DROP TABLE UserPasswordHistory;

DROP TABLE PasswordValidatorInfo;

DROP TABLE PasswordValidator;

DROP TABLE QuestionOption;

DROP TABLE QuestionChoice;

DROP TABLE QuestionBank;

DROP TABLE Response;

DROP TABLE Question;

DROP TABLE QuestionTitle;

DROP TABLE QuestionText;

DROP TABLE QuestionType;

DROP TABLE SystemRole;

DROP TABLE CourseRole;

DROP TABLE Course;

DROP TABLE Role;

DROP TABLE UserContactInfo;

DROP TABLE User;

DROP TABLE UserPasswordHistory;

DROP TABLE PasswordValidatorInfo;

DROP TABLE PasswordValidator;