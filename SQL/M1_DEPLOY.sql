CREATE TABLE User (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    bannerID VARCHAR(20) NOT NULL,
    password VARCHAR(76) NOT NULL
);

CREATE TABLE UserContactInfo (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    userID BIGINT NOT NULL,
    firstName VARCHAR(100) NULL,
    lastName VARCHAR(100) NULL,
    email VARCHAR(320) NOT NULL,
    FOREIGN KEY (userID) REFERENCES User(id)
);

CREATE TABLE Role (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    role VARCHAR(10)
);

CREATE TABLE Course (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200)
);

CREATE TABLE CourseRole (
	courseID BIGINT NOT NULL,
    roleID BIGINT NOT NULL,
    userID BIGINT NOT NULL,
    FOREIGN KEY (courseID) REFERENCES Course(id),
    FOREIGN KEY (roleID) REFERENCES Role(id),
    FOREIGN KEY (userID) REFERENCES User(id)
);

CREATE TABLE SystemRole (
	roleID BIGINT NOT NULL,
    userID BIGINT NOT NULL,
    FOREIGN KEY (roleID) REFERENCES Role(id),
    FOREIGN KEY (userID) REFERENCES User(id)
);

CREATE TABLE UserPasswordHistory (
  userID bigint NOT NULL,
  password varchar(76) NOT NULL,
  timestamp timestamp NOT NULL DEFAULT current_timestamp(),
  KEY passwordhistory_idx (userID),
  FOREIGN KEY (userID) REFERENCES User (id)
);

CREATE TABLE PasswordValidator (
  id bigint NOT NULL AUTO_INCREMENT,
  name varchar(70) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE PasswordValidatorInfo (
  validatorID bigint NOT NULL,
  value varchar(50) NOT NULL,
  isEnabled tinyint NOT NULL,
  KEY PasswordValidatorInfofk_1 (validatorID),
  FOREIGN KEY (validatorID) REFERENCES PasswordValidator (id)
);

CREATE TABLE QuestionType (
  id bigint NOT NULL AUTO_INCREMENT,
  type varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE QuestionTitle (
  id bigint NOT NULL AUTO_INCREMENT,
  title varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE QuestionText (
  id bigint NOT NULL AUTO_INCREMENT,
  text varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE Question (
  id bigint NOT NULL AUTO_INCREMENT,
  type bigint NOT NULL,
  title bigint NOT NULL,
  text bigint NOT NULL,
  timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  KEY `question_fk2_idx` (`type`),
  KEY `question_fk2_idx1` (`title`),
  KEY `question_fk3_idx` (`text`),
  CONSTRAINT `question_fk1` FOREIGN KEY (`type`) REFERENCES `QuestionType` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `question_fk2` FOREIGN KEY (`title`) REFERENCES `QuestionTitle` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `question_fk3` FOREIGN KEY (`text`) REFERENCES `QuestionText` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE QuestionChoice (
  id bigint NOT NULL AUTO_INCREMENT,
  displayText varchar(500) NOT NULL,
  storedAs varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE QuestionOption (
  questionID bigint NOT NULL,
  choiceID bigint NOT NULL,
  displayOrder int NOT NULL,
  KEY `QuestionOptions_fk1_idx` (`questionID`),
  KEY `QuestionOption_fk2_idx` (`choiceID`),
  CONSTRAINT `questionoption_fk1` FOREIGN KEY (`questionID`) REFERENCES `Question` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `questionoption_fk2` FOREIGN KEY (`choiceID`) REFERENCES `QuestionChoice` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE QuestionBank (
  questionID bigint NOT NULL,
  userID bigint NOT NULL,
  KEY `questionbank_fk1_idx` (`questionID`),
  KEY `questionbank_fk2_idx` (`userID`),
  CONSTRAINT `questionbank_fk1` FOREIGN KEY (`questionID`) REFERENCES `Question` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `questionbank_fk2` FOREIGN KEY (`userID`) REFERENCES `User` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE Response (
  id bigint NOT NULL AUTO_INCREMENT,
  questionID bigint NOT NULL,
  userID bigint NOT NULL,
  response varchar(500) NOT NULL,
  PRIMARY KEY (`id`,`response`),
  KEY `response_fk1_idx` (`userID`),
  KEY `response_fk2_idx` (`questionID`),
  CONSTRAINT `response_fk1` FOREIGN KEY (`userID`) REFERENCES `User` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `response_fk2` FOREIGN KEY (`questionID`) REFERENCES `Question` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
);


INSERT INTO Role(role)
VALUES
    ('Admin'),
	('Guest'),
    ('Student'),
    ('Instructor'),
    ('TA');

/*
	This is not how you would do this in the real world, it would not be safe to have passwords
    or accounts stored in files in git.  This creates the admin user with an empty password.
*/
INSERT INTO User(bannerID, password)
VALUES ('B-000000', '1234');

SELECT LAST_INSERT_ID()
INTO @adminID;

INSERT INTO UserContactInfo(userID, firstName, lastName, email)
VALUES (@adminID, 'Rob', 'Hawkey', 'rhawkey@dal.ca');

INSERT INTO QuestionType VALUES (1,"Text");
INSERT INTO QuestionType VALUES (2,"Numeric");
INSERT INTO QuestionType VALUES (3,"MCQOne");
INSERT INTO QuestionType VALUES (4,"MCQMultiple");

INSERT INTO PasswordValidator (name) VALUES  ('Minimum Length');
INSERT INTO PasswordValidator (name) VALUES  ('Maximum Length');
INSERT INTO PasswordValidator (name) VALUES  ('Minimum Uppercase');
INSERT INTO PasswordValidator (name) VALUES  ('Minimum Lowercase');
INSERT INTO PasswordValidator (name) VALUES  ('Minimum Symbols');
INSERT INTO PasswordValidator (name) VALUES  ('Restricted Characters');
INSERT INTO PasswordValidator (name) VALUES  ('Password History');

INSERT INTO PasswordValidatorInfo VALUES (1, '5',1);
INSERT INTO PasswordValidatorInfo VALUES (2, '5',0);
INSERT INTO PasswordValidatorInfo VALUES (3, '1',0);
INSERT INTO PasswordValidatorInfo VALUES (4, '1',0);
INSERT INTO PasswordValidatorInfo VALUES (5, '1',0);
INSERT INTO PasswordValidatorInfo VALUES (6, '.,#*',0);
INSERT INTO PasswordValidatorInfo VALUES (7, '5',0);

SELECT id
INTO @adminRoleID
FROM Role
WHERE role = 'Admin';

INSERT INTO SystemRole(roleID, userID)
VALUES (@adminRoleID, @adminID);

SELECT * FROM Role;
SELECT * FROM User;
SELECT * FROM QuestionType;
SELECT * FROM PasswordValidator;
SELECT * FROM PasswordValidatorInfo;

INSERT INTO PasswordValidator (name) VALUES  ('Minimum Uppercase');
INSERT INTO PasswordValidator (name) VALUES  ('Minimum Lowercase');
INSERT INTO PasswordValidator (name) VALUES  ('Minimum Symbols');
INSERT INTO PasswordValidator (name) VALUES  ('Restricted Characters');
INSERT INTO PasswordValidator (name) VALUES  ('Password History');

INSERT INTO PasswordValidatorInfo VALUES (1, '5',1);
INSERT INTO PasswordValidatorInfo VALUES (2, '5',0);
INSERT INTO PasswordValidatorInfo VALUES (3, '1',0);
INSERT INTO PasswordValidatorInfo VALUES (4, '1',0);
INSERT INTO PasswordValidatorInfo VALUES (5, '1',0);
INSERT INTO PasswordValidatorInfo VALUES (6, '.,#*',0);
INSERT INTO PasswordValidatorInfo VALUES (7, '5',0);