DELIMITER $$

DROP PROCEDURE IF EXISTS spLoadAllQuestionsByInstructor $$

CREATE PROCEDURE spLoadAllQuestionsByInstructor (
	IN id BIGINT
)
BEGIN
	SELECT id, title, text, type, createdDate FROM Question where instructorId = id;
-- 	SELECT q.id, q.title, s.type, q.createdDate
-- 	from Question q
-- 	where instructorId = id
-- 	order by Question.type;
END $$

DELIMITER ;