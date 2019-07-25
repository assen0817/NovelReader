package ListCell;

import app.Main;
import data.Novel;
import javafx.event.EventHandler;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import layout.NovelColumnsLayout;

import java.util.ArrayList;

public class NovelsList extends ListCell<Novel> {
    private final Text title;       // タイトル
    private final Text author;      //作者名
    private final Text nowParStory;       // 現在読んでいる話数/小説のすべての話数を表す
    private final VBox vbox;
    public NovelsList(){
        vbox = new VBox(5);
        title = new Text();
        title.setFont(new Font("System Bold", 18));
        author = new Text();
        nowParStory = new Text();
        VBox.setVgrow(title, Priority.NEVER);
        VBox.setVgrow(author, Priority.ALWAYS);
        VBox.setVgrow(nowParStory, Priority.ALWAYS);
        vbox.getChildren().addAll(title, author, nowParStory);
        // ユーザ名、コメントをVBoxの幅-5で折り返すようにバインドしとく
        title.wrappingWidthProperty().bind(vbox.widthProperty().subtract(5));
        author.wrappingWidthProperty().bind(vbox.widthProperty().subtract(5));
        nowParStory.wrappingWidthProperty().bind(vbox.widthProperty().subtract(5));
    }

    @Override
    protected void updateItem(Novel item, boolean empty) {
        super.updateItem(item, empty);
        if(empty) {
            setText(null);
            setGraphic(null);
        } else {
            title.setText(item.getTitle());
            author.setText(item.getAuthor());
            nowParStory.setText(item.getNowReading() + "/" + item.getAllStory());
            setGraphic(vbox);
        }
    }
}
