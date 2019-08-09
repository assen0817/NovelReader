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

import java.io.File;
import java.util.HashMap;

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
        File file = new File("src/novels");
        File[] files = file.listFiles();

        if(files == null) return;

        //取得した一覧を表示する
        for (File value : files) {
            String[] ncode = value.toString().split("\\\\");
            HashMap<String, String> map = Files.NovelReader(ncode[ncode.length -1]);
            if(map.size() == 0)items.add(new Novel(ncode[ncode.length -1], "aaaa", ncode[ncode.length -1], 1, 10));
            else items.add(new Novel(map.get("title"), map.get("writer"), ncode[ncode.length -1], 1, Integer.parseInt(map.get("general_all_no").trim())));

        }
        listView.setMinHeight(700- listView.getLayoutY());

        EventHandler<MouseEvent> mouseClick = this::mouseClick;
        listView.setOnMouseClicked(mouseClick);
    }
    private void mouseClick(MouseEvent event) {
        Novel novel = listView.getSelectionModel().getSelectedItem();
        if(novel != null) {
            novelColumn.setItem(novel.getNcode());
        }
    }

    public ListView<Novel> getLayout(){return listView;}

    //リストの更新
    public void update(){

    }


}
