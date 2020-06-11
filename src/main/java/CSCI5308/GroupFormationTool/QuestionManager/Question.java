package CSCI5308.GroupFormationTool.QuestionManager;

import java.util.Date;

import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;

public class Question {
	private long id;
	private String title;
	private String text;
	private String type;
	private long instructorId;
	private Date date;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(long instructorId) {
		this.instructorId = instructorId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public boolean delete(IQuestionPersistence questionDB)
	{
		return questionDB.delete(id);
	}
	
	public boolean create(IQuestionPersistence questionDB)
	{
		return questionDB.create(this);
	}
}
