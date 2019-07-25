package layout;

import app.Main;
import data.Novel;
import data.NovelColumn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import ListCell.NovelsList;
import windows.BookPageWindow;
import windows.WebStage;

import java.io.File;

public class NovelsListLayout {
    private ListView<Novel> listView;
    private final ObservableList<Novel> items = FXCollections.observableArrayList();
    private VBox vbox;
    NovelColumnsLayout novelColumn;
    public NovelsListLayout(NovelColumnsLayout novelColumn){
        this.novelColumn = novelColumn;
        vbox = new VBox(5);

        listView = new ListView<Novel>();
        listView.setItems(items);
        listView.setCellFactory(param -> new NovelsList());
        File file = new File("src/novels");
        File[] files = file.listFiles();

        if(files == null) return;

        //取得した一覧を表示する
        for (File value : files) {
            String[] ncode = value.toString().split("\\\\");
            items.add(new Novel(ncode[ncode.length -1], "aaaa", 1, 10));
        }
        listView.setMinHeight(700- listView.getLayoutY());

        EventHandler<MouseEvent> mouseClick = this::mouseClick;
        listView.setOnMouseClicked(mouseClick);
    }
    private void mouseClick(MouseEvent event) {
        Novel novel = listView.getSelectionModel().getSelectedItem();
        novelColumn.setItem(novel.getTitle());
    }

    public ListView<Novel> getLayout(){return listView;}


}
