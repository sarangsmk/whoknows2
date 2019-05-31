package gq.smktech.whoknows;


public class post {
    private String question;
    private String description;
    private String mediaLink;
    private String postedBy;
    private String postedOn;
    private String dp;
    private String postId;
    private String mobile;
    public post() {

    }

    public post(String question, String description, String mediaLink, String postedBy,String postedOn,String dp,String postId,String mobile) {
        this.question = question;
        this.description = description;
        this.mediaLink = mediaLink;
        this.postedBy = postedBy;
        this.postedOn = postedOn;
        this.dp = dp;
        this.postId = postId;
        this.mobile=mobile;
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

    public String getDp() {
        return dp;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setDp(String dp) {
        this.dp = dp;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

}