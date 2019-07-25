package windowUi;

public class Novel {
    private String title;       // タイトル
    private String author;      //作者名
    private int nowReading;     // 現在読んでいる話数
    private int allStory;       // 小説のすべての話数

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getNowReading() {
        return nowReading;
    }

    public void setNowReading(int nowReading) {
        this.nowReading = nowReading;
    }

    public int getAllStory() {
        return allStory;
    }

    public void setAllStory(int allStory) {
        this.allStory = allStory;
    }

    public Novel( String title ,String author ,int nowReading, int allStory ){
        this.title = title;
        this.author = author;
        this.nowReading = nowReading;
        this.allStory = allStory;
    }
}
