package layout;

import app.Main;
import data.Novel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import ListCell.NovelsList;

import java.io.File;

public class NovelsListLayout {
    private ListView<Novel> listView;
    private final ObservableList<Novel> items = FXCollections.observableArrayList();
    private VBox vbox;

    private Main main;

    public NovelsListLayout(Main main){
        this.main = main;
        vbox = new VBox(5);

        listView = new ListView<Novel>();
        listView.setItems(items);
        listView.setCellFactory(param -> new NovelsList(main));
        File file = new File("src/novels");
        File[] files = file.listFiles();

        if(files == null) return;

        //取得した一覧を表示する
        for (File value : files) {
            String[] ncode = value.toString().split("\\\\");
            items.add(new Novel(ncode[ncode.length -1], "aaaa", 1, 10));
        }

//        Button b1 = new Button("子ステージ開く");
//        //マウスがクリックされたときのイベント
//        EventHandler<MouseEvent> mouseClick = this::mouseClick;
//        b1.addEventHandler( MouseEvent.MOUSE_CLICKED , mouseClick );
//
//        VBox.setVgrow(b1, Priority.NEVER);
//        VBox.setVgrow(listView, Priority.ALWAYS);
//        vbox.getChildren().addAll(b1, listView);

        listView.setMinHeight(700- listView.getLayoutY());

        main.getFlowPane().getChildren().add( listView );
    }



}
