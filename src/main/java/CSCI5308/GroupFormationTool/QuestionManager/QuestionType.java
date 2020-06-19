package CSCI5308.GroupFormationTool.QuestionManager;

public enum QuestionType {
    TEXT {
        public String toString()
        {
            return "TEXT";
        }
    },
    NUMBER {
        public String toString()
        {
            return "NUMBER";
        }
    },
    MCQ {
        public String toString()
        {
            return "MCQ";
        }
    },
    CHECKBOX {
        public String toString() { return "CHECKBOX"; }
    },
}
