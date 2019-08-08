package layout;

        import app.Main;
        import data.NovelColumn;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.event.EventHandler;
        import javafx.scene.control.ListView;
        import javafx.scene.input.MouseButton;
        import javafx.scene.input.MouseEvent;
        import javafx.scene.layout.VBox;
        import ListCell.NovelColumnsList;
        import windows.BookPageWindow;
        import windows.WebStage;

        import java.io.File;
        import java.util.ArrayList;

public class NovelColumnsLayout {
    private ListView<NovelColumn> listView;
    private final ObservableList<NovelColumn> items = FXCollections.observableArrayList();
    private VBox vbox;
    private static ArrayList<NovelColumnsLayout> objects = new ArrayList<NovelColumnsLayout>();

    //小説の章のリストのレイアウト定義
    public NovelColumnsLayout(){
        vbox = new VBox(5);

        listView = new ListView<NovelColumn>();
        listView.setItems(items);
        listView.setCellFactory(param -> new NovelColumnsList());
        listView.setMinHeight(700- listView.getLayoutY());
        objects.add(this);

        EventHandler<MouseEvent> mouseClick = this::mouseClick;
        listView.setOnMouseClicked(mouseClick);
    }

//   章リストに表示したい小説（ncode）をセット
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

    public ListView<NovelColumn> getLayout(){return listView;}
//ダブルクリック時に読書画面を起動する
    private void mouseClick(MouseEvent event) {
        NovelColumn column = listView.getSelectionModel().getSelectedItem();
        boolean doubleClicked
                = event.getButton().equals(MouseButton.PRIMARY)
                && event.getClickCount() == 2;
        if(doubleClicked) {
            try {
                BookPageWindow bookPageWindow = new BookPageWindow(column.getSubTitle());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}