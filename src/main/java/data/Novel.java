package data;

//NovelListの項目定義
public class Novel {
    private String title;       // タイトル
    private String author;      //作者名
    private String ncode;       // 小説のコード
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

    public String getNcode() {
        return ncode;
    }

    public void setNcode(String ncode) {
        this.ncode = ncode;
    }

    public Novel(String title, String author, String ncode, int nowReading, int allStory){
        this.title = title;
        this.author = author;
        this.ncode = ncode;
        this.nowReading = nowReading;
        this.allStory = allStory;
    }


}
