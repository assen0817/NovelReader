package layout;

import data.Novel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import ListCell.NovelsList;
import system.Files;
import system.Novels;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class NovelsListLayout {
    private ListView<Novel> listView;
    private final ObservableList<Novel> items = FXCollections.observableArrayList();
    private VBox vbox;
    NovelColumnsLayout novelColumn;

    //小説のリストのレイアウト定義
    public NovelsListLayout(NovelColumnsLayout novelColumn){
        this.novelColumn = novelColumn;
        vbox = new VBox(5);

        listView = new ListView<Novel>();
        listView.setItems(items);
        listView.setCellFactory(param -> new NovelsList());
        listView.setMinHeight(700- listView.getLayoutY());

        EventHandler<MouseEvent> mouseClick = this::mouseClick;
        listView.setOnMouseClicked(mouseClick);

        update();
    }
    private void mouseClick(MouseEvent event) {
        Novel novel = listView.getSelectionModel().getSelectedItem();
        if(novel != null) {
            novelColumn.update(novel.getNcode());
        }
    }

    public ListView<Novel> getLayout(){return listView;}

    //リストの更新
    public void update(){
        List<String> list = Files.getLocalNcode();
        if(list == null) return;
        items.clear();

        for(String ncode : list){
            HashMap<String, String> map = Files.NovelReader(ncode);
            if(map.size() == 0) items.add(new Novel(ncode, "xxxx", ncode, 1, 1));
            else items.add(new Novel(map.get("title"), map.get("writer"), ncode, 1, Integer.parseInt(map.get("general_all_no").trim())));
        }

    }
}
