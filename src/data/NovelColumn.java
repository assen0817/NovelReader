package data;

public class NovelColumn {
    private String subTitle;
    private String postDay;
    private String lastUpdateDay;
    private int columnNumber;



    public NovelColumn( String subTitle ,String postDay ,String lastUpdateDay, int columnNumber ){
        this.subTitle = subTitle;
        this.postDay = postDay;
        this.lastUpdateDay = lastUpdateDay;
        this.columnNumber = columnNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
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

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
}
