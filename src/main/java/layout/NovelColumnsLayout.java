package layout;

        import data.NovelColumn;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.event.EventHandler;
        import javafx.scene.control.ListView;
        import javafx.scene.input.MouseButton;
        import javafx.scene.input.MouseEvent;
        import javafx.scene.layout.VBox;
        import ListCell.NovelColumnsList;
        import system.Files;
        import windows.BookPageWindow;

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
    public void update(String ncode){
        items.clear();
        ArrayList<String> columns = Files.NovelColumnsReader(ncode);
        int sumColumn = 0;
        for(int i = 1; i < columns.size(); i++){
            NovelColumn novelColumn = new NovelColumn();
            String[] column = columns.get(i).split(",");
            novelColumn.setTitle(column[0]);
            if(column.length > 1) {
                sumColumn++;
                novelColumn.setColumnNumber(sumColumn);
                novelColumn.setPostDay(column[1]);
                if(column.length > 2) novelColumn.setLastUpdateDay(column[2]);
            }
            items.add(novelColumn);

        }
    }

    public ListView<NovelColumn> getLayout(){return listView;}
//ダブルクリック時に読書画面を起動する
    private void mouseClick(MouseEvent event) {
        NovelColumn column = listView.getSelectionModel().getSelectedItem();
        if(column.getColumnNumber() == 0) return;
        boolean doubleClicked
                = event.getButton().equals(MouseButton.PRIMARY)
                && event.getClickCount() == 2;
        if(doubleClicked) {
            try {
                BookPageWindow bookPageWindow = new BookPageWindow(column.getTitle());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}