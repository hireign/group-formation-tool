package CSCI5308.GroupFormationTool.PasswordValidation;

public enum PasswordValidatorType
{
	MINLENGTH
	{
		public String toString()
		{
		   return "Minimum Length";
		}
	},
	MAXLENGTH
	{
		public String toString()
		{
		   return "Maximum Length";
		}
	},
	MINUPPERCASE
	{
		public String toString()
		{
		   return "Minimum Uppercase";
		}
	},
	MINLOWERCASE
	{
		public String toString()
		{
		   return "Minimum Lowercase";
		}
	},
	MINSYMBOLS
	{
		public String toString()
		{
		   return "Minimum Symbols";
		}
	},
	RESTRICTEDCHAR
	{
		public String toString()
		{
		   return "Restricted Characters";
		}
	},
	PASSWORDHISTORY
	{
		public String toString()
		{
		   return "Password History";
		}
	}
	
}

