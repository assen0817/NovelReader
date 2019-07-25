package ListCell;

        import data.NovelColumn;
        import windows.BookPageWindow;
        import javafx.event.EventHandler;
        import javafx.scene.control.ListCell;
        import javafx.scene.input.MouseButton;
        import javafx.scene.input.MouseEvent;
        import javafx.scene.layout.HBox;
        import javafx.scene.layout.Priority;
        import javafx.scene.layout.VBox;
        import javafx.scene.text.Font;
        import javafx.scene.text.Text;

public class NovelColumnsList extends ListCell<NovelColumn> {
    private final Text subTitle;       // タイトル
    private final Text updateDay;
    private final Text columnNumber;
    private final VBox vbox;
    private final HBox hbox;
    private String pageTitle;
    public NovelColumnsList(){
        vbox = new VBox(5);
        hbox = new HBox(5);
        subTitle = new Text();
        subTitle.setFont(new Font("System Bold", 18));
        updateDay = new Text();
        columnNumber = new Text();
        VBox.setVgrow(subTitle, Priority.NEVER);
        VBox.setVgrow(updateDay, Priority.ALWAYS);
        HBox.setHgrow(vbox, Priority.NEVER);
        HBox.setHgrow(columnNumber, Priority.ALWAYS);
        vbox.getChildren().addAll(subTitle, updateDay);
        hbox.getChildren().addAll(columnNumber, vbox);
        // ユーザ名、コメントをVBoxの幅-5で折り返すようにバインドしとく
        subTitle.wrappingWidthProperty().bind(vbox.widthProperty().subtract(5));
        updateDay.wrappingWidthProperty().bind(vbox.widthProperty().subtract(5));
    }

    @Override
    protected void updateItem(NovelColumn item, boolean empty) {
        super.updateItem(item, empty);
        if(empty) {
            setText(null);
            setGraphic(null);
        } else {
            pageTitle = item.getSubTitle();
            subTitle.setText(item.getSubTitle());
            updateDay.setText(item.getPostDay()+"(" + item.getLastUpdateDay()+")");
            columnNumber.setText(Integer.toString(item.getColumnNumber()));
            EventHandler<MouseEvent> mouseClick = this::mouseClick;
            vbox.addEventHandler( MouseEvent.MOUSE_CLICKED , mouseClick );
            setGraphic(hbox);
        }
    }
    private void mouseClick(MouseEvent event) {
        boolean doubleClicked
                = event.getButton().equals(MouseButton.PRIMARY)
                && event.getClickCount() == 2;
        if(doubleClicked) {
            try {
                BookPageWindow bookPageWindow = new BookPageWindow(pageTitle);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}