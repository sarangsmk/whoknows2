package gq.smktech.whoknows;

import android.net.Uri;

public class post {
    private String question;
    private String description;
    private String mediaLink;
    private String postedBy;
    private String postedOn;
    private String dp;

    public post() {

    }

    public post(String question, String description, String mediaLink, String postedBy,String postedOn,String dp) {
        this.question = question;
        this.description = description;
        this.mediaLink = mediaLink;
        this.postedBy = postedBy;
        this.postedOn = postedOn;
        this.dp = dp;
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

    public void setDp(String dp) {
        this.dp = dp;
    }

}