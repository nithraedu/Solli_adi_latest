
package nithra.tamil.word.game.solliadi;


import com.google.gson.annotations.SerializedName;

public class Downloading_pogo {
    @SerializedName("status")
    private String status;
    @SerializedName("id")
    private String id;
    @SerializedName("gameid")
    private String gameid;
    @SerializedName("levelid")
    private String levelid;
    @SerializedName("letters")
    private String letters;
    @SerializedName("answer")
    private String answer;
    @SerializedName("action")
    public String action;
    @SerializedName("lastqid")
    public String lastqid;
    @SerializedName("android_id")
    public String android_id;

    public Downloading_pogo(String action, String gameid, String lastqid, String android_id) {
        this.action = action;
        this.gameid = gameid;
        this.lastqid = lastqid;
        this.android_id = android_id;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setLastqid(String lastqid) {
        this.lastqid = lastqid;
    }

    public void setAndroid_id(String android_id) {
        this.android_id = android_id;
    }

    private String hints;

    public String getAction() {
        return action;
    }

    public String getLastqid() {
        return lastqid;
    }

    public String getAndroid_id() {
        return android_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGameid() {
        return gameid;
    }

    public void setGameid(String gameid) {
        this.gameid = gameid;
    }

    public String getLevelid() {
        return levelid;
    }

    public void setLevelid(String levelid) {
        this.levelid = levelid;
    }

    public String getLetters() {
        return letters;
    }

    public void setLetters(String letters) {
        this.letters = letters;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getHints() {
        return hints;
    }

    public void setHints(String hints) {
        this.hints = hints;
    }

}
