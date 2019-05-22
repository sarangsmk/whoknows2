package gq.smktech.whoknows;

public class post {
    private String question;
    private String description;
    private String mediaLink;
    private String postedBy;
    private String postedOn;

    public post() {

    }

    public post(String question, String description, String mediaLink, String postedBy,String postedOn) {
        this.question = question;
        this.description = description;
        this.mediaLink = mediaLink;
        this.postedBy = postedBy;
        this.postedOn = postedOn;
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

    public String getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(String postedOn) {
        this.postedOn = postedOn;
    }
}