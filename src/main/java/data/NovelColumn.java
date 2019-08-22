package data;

//NovelColumnsListの項目定義

public class NovelColumn {
    private String title;
    private String postDay;
    private String lastUpdateDay;
    private int columnNumber;


    public NovelColumn(){
        title = "";
        postDay = "";
        lastUpdateDay = "";
        columnNumber = 0;
    }


    public String getLastUpdateDay() {
        return lastUpdateDay;
    }

    public void setLastUpdateDay(String lastUpdateDay) {
        this.lastUpdateDay = lastUpdateDay;
    }

    public String getPostDay() {
        return postDay;
    }

    public void setPostDay(String postDay) {
        this.postDay = postDay;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }
}
