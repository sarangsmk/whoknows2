package gq.smktech.whoknows;

public class post {
    private String question;
    private String description;
    private String mediaLink;
    private String postedBy;

    public post() {

    }

    public post(String question, String description, String mediaLink, String postedBy) {
        this.question = question;
        this.description = description;
        this.mediaLink = mediaLink;
        this.postedBy = postedBy;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMediaLink() {
        return mediaLink;
    }

    public void setMediaLink(String mediaLink) {
        this.mediaLink = mediaLink;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }
}