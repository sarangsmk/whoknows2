package gq.smktech.whoknows;

public class comment {
    private String comment;
    private String postedBy;
    private String postedOn;
    private String dp;

    public comment() {

    }
    public comment(String comment,String postedBy ,String postedOn,String dp) {
        this.comment = comment;
        this.postedBy = postedBy;
        this.postedOn = postedOn;
        this.dp = dp;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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