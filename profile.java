package gq.smktech.whoknows;

public class profile {

    private String name;
    private String email;
    private String tags;
    private String dp;

    public profile()
    {

    }

    public profile(String name, String email, String tags, String dp) {

        this.name = name;
        this.email = email;
        this.tags = tags;
        this.dp = dp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDp() {
        return dp;
    }

    public void setDp(String dp) {
        this.dp = dp;
    }
}