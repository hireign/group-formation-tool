package CSCI5308.GroupFormationTool.QuestionManager;

import java.sql.Timestamp;

public class Question implements IQuestion 
{
	private long id;
	private String title;
	private String text;
	private QuestionType type;
	private Timestamp timestamp;
	private IOptions options;

	public Question() 
	{
		setDefaults();
	}
	
	@Override
	public void setDefaults() 
	{
		id=-1;
		title="";
		text="";
		type=null;
		timestamp=null;
	}
	
	@Override
	public Timestamp getTimestamp() 
	{
		return timestamp;
	}
	
	@Override
	public void setTimestamp(Timestamp timestamp) 
	{
		this.timestamp = timestamp;
	}
	
	@Override
	public long getId() 
	{
		return id;
	}
	
	@Override
	public void setId(long id) 
	{
		this.id = id;
	}
	
	@Override
	public String getTitle() 
	{
		return title;
	}
	
	@Override
	public void setTitle(String title) 
	{
		this.title = title;
	}
	
	@Override
	public String getText() 
	{
		return text;
	}
	
	@Override
	public void setText(String text) 
	{
		this.text = text;
	}
	
	@Override
	public QuestionType getType() 
	{
		return type;
	}
	
	@Override
	public void setType(QuestionType type) 
	{
		this.type = type;
	}
	
	@Override
	public void deleteQuestion(IQuestionPersistence questionDB, long questionID) throws Exception 
	{
		questionDB.deleteQuestionByQuestionId(questionID);
	}
	
	@Override
	public long createQuestion(IQuestionPersistence questionDB, String bannerID) throws Exception 
	{
		return questionDB.createQuestion(this, bannerID);
	}

	public IOptions getOptions()
	{
		return options;
	}

	public void setOptions(IOptions options)
	{
		this.options = options;
	}
	
}
