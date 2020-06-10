package CSCI5308.GroupFormationTool.QuestionManager;

public enum QuestionType {
    TEXT {
        public String toString()
        {
            return "text";
        }
    },
    NUMBER {
        public String toString()
        {
            return "number";
        }
    },
    MCQ {
        public String toString()
        {
            return "mcq";
        }
    },
    CHECKBOX {
        public String toString() { return "checkbox"; }
    },
}
