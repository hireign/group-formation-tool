package CSCI5308.GroupFormationTool.QuestionManager;

import java.sql.Timestamp;

public class Question 
{
	private long id;
	private String title;
	private String text;
	private QuestionType type;
	private Timestamp timestamp;
	private Options options;

	public Question() 
	{
		setDefaults();
	}
	
	public void setDefaults() 
	{
		id=-1;
		title="";
		text="";
		type=null;
		timestamp=null;
	}
	
	public Timestamp getTimestamp() 
	{
		return timestamp;
	}
	
	public void setTimestamp(Timestamp timestamp) 
	{
		this.timestamp = timestamp;
	}
	
	public long getId() 
	{
		return id;
	}
	
	public void setId(long id) 
	{
		this.id = id;
	}
	
	public String getTitle() 
	{
		return title;
	}
	
	public void setTitle(String title) 
	{
		this.title = title;
	}
	
	public String getText() 
	{
		return text;
	}
	
	public void setText(String text) 
	{
		this.text = text;
	}
	
	public QuestionType getType() 
	{
		return type;
	}
	
	public void setType(QuestionType type) 
	{
		this.type = type;
	}
	
	public void deleteQuestion(IQuestionPersistence questionDB, long questionID) throws Exception 
	{
		questionDB.deleteQuestionByQuestionId(questionID);
	}
	
	public long createQuestion(IQuestionPersistence questionDB, String bannerID) throws Exception 
	{
		return questionDB.createQuestion(this, bannerID);
	}

	public Options getOptions()
	{
		return options;
	}

	public void setOptions(Options options)
	{
		this.options = options;
	}
	
}
