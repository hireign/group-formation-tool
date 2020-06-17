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

SELECT id
INTO @adminRoleID
FROM Role
WHERE role = 'Admin';

INSERT INTO SystemRole(roleID, userID)
VALUES (@adminRoleID, @adminID);

SELECT * FROM Role;
SELECT * FROM User;

CREATE TABLE `UserPasswordArchive` (
  `UserID` bigint(20) NOT NULL,
  `Password` varchar(80) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ;

CREATE TABLE `PasswordPolicies` (
  `idPasswordPolicies` int(11) NOT NULL AUTO_INCREMENT,
  `Minimum_length` int(11) NOT NULL,
  `Maximum_length` int(11) NOT NULL,
  `Minimum_uppercase_Chars` int(11) NOT NULL,
  `Minimum_lowercase_Chars` int(11) NOT NULL,
  `Minimum_special_characters` int(11) NOT NULL,
  `Disallowed_Chars` varchar(80) DEFAULT NULL,
  `Password_History_Constraint_No` int(11) NOT NULL,
  `Enable_Policy` tinyint(4) NOT NULL,
  PRIMARY KEY (`idPasswordPolicies`)
); 

CREATE TABLE `Question` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `text` varchar(255) NOT NULL,
  `type` varchar(20) NOT NULL,
  `instructorId` bigint(20) NOT NULL,
  `createdDate` date DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `instructorId` (`instructorId`),
  CONSTRAINT `Question_ibfk_1` FOREIGN KEY (`instructorId`) REFERENCES `User` (`id`)
);

CREATE TABLE `Response` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `text` varchar(255) NOT NULL,
  `type` varchar(20) NOT NULL,
  `studentId` bigint(20) NOT NULL,
  `questionId` bigint(20) NOT NULL,
  `createdDate` date DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `studentId` (`studentId`),
  KEY `questionId` (`questionId`),
  CONSTRAINT `Response_ibfk_1` FOREIGN KEY (`studentId`) REFERENCES `User` (`id`),
  CONSTRAINT `Response_ibfk_2` FOREIGN KEY (`questionId`) REFERENCES `Question` (`id`)
);

CREATE TABLE Options (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    questionId BIGINT NOT NULL,
    text VARCHAR(255) NOT NULL,
    FOREIGN KEY (questionId) REFERENCES Question(id)
); 
