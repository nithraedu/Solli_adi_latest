package nithra.tamil.word.game.solliadi;

import android.content.Context;

public class Table_data_insert {


    private Context context;
    private String gameid;
    private String questionid;
    private String question;
    private String answer;
    private String splitword;
    private String playtime;
    private String isfinish;
    private String daily;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getGameid() {
        return gameid;
    }

    public void setGameid(String gameid) {
        this.gameid = gameid;
    }

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getSplitword() {
        return splitword;
    }

    public void setSplitword(String splitword) {
        this.splitword = splitword;
    }

    public String getPlaytime() {
        return playtime;
    }

    public void setPlaytime(String playtime) {
        this.playtime = playtime;
    }

    public String getIsfinish() {
        return isfinish;
    }

    public void setIsfinish(String isfinish) {
        this.isfinish = isfinish;
    }

    public String getDaily() {
        return daily;
    }

    public void setDaily(String daily) {
        this.daily = daily;
    }

    public void TableTable_data_insert_data_insert(Context context, String gameid, String questionid, String question, String answer, String splitword, String playtime, String isfinish, String daily) {

        this.context = context;
        this.gameid = gameid;
        this.questionid = questionid;
        this.question = question;
        this.answer = answer;
        this.splitword = splitword;
        this.playtime = playtime;
        this.isfinish = isfinish;
        this.daily = daily;
    }
}
