package gq.smktech.whoknows;

public class Ask {

    String question;
    String description;
    String uName;

    public Ask()
    {

    }

    public Ask(String question, String description, String uName) {
        this.question = question;
        this.description = description;
        this.uName = uName;
    }

    public String getQuestion() {
        return question;
    }

    public String getDescription() {
        return description;
    }
    public String getUserName() {
        return uName;
    }
}
