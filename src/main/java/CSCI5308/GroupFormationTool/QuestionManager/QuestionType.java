package CSCI5308.GroupFormationTool.QuestionManager;

public enum QuestionType
{
	TEXT
	{
		public String toString()
		{
		   return "Text";
		}
	},
	NUMERIC
	{
		public String toString()
		{
		   return "Numeric";
		}
	},
	MCQONE
	{
		public String toString()
		{
		   return "MCQOne";
		}
	},
	MCQMULTIPLE
	{
		public String toString()
		{
		   return "MCQMultiple";
		}
	}
	
}

