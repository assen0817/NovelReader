package windowUi;

        import app.Main;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.event.EventHandler;
        import javafx.scene.control.Button;
        import javafx.scene.control.ListView;
        import javafx.scene.input.MouseEvent;
        import javafx.scene.layout.FlowPane;
        import javafx.scene.layout.Priority;
        import javafx.scene.layout.VBox;
        import javafx.stage.Stage;
        import webWindow.WebStage;

        import java.io.File;

public class NovelColumnsLayout {
    private ListView<NovelColumn> listView;
    private final ObservableList<NovelColumn> items = FXCollections.observableArrayList();
    private VBox vbox;

    public NovelColumnsLayout(Main main){
        vbox = new VBox(5);

        listView = new ListView<NovelColumn>();
        listView.setItems(items);
        listView.setCellFactory(param -> new NovelColumnsList());



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
    public void setItem(String path){
        items.clear();
        File file = new File("src/novels/"+path);
        File[] files = file.listFiles();

        if(files != null) {
            int i = 0;
            //取得した一覧を表示する
            for (File value : files) {
                String[] column = value.toString().split("\\\\");
                items.add(new NovelColumn(column[column.length - 1], "", "", i++));
            }
        }
    }


//    private void mouseClick(MouseEvent event) {
//        try {
//            WebStage nst2 = new WebStage( stage );
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}