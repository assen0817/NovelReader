package ListCell;

        import data.NovelColumn;
        import javafx.geometry.Pos;
        import javafx.scene.control.ListCell;
        import javafx.scene.layout.HBox;
        import javafx.scene.layout.Priority;
        import javafx.scene.layout.VBox;
        import javafx.scene.text.Font;
        import javafx.scene.text.Text;

public class NovelColumnsList extends ListCell<NovelColumn> {
    private final Text subTitle;       // タイトル
    private final Text chapterTitle;
    private final Text updateDay;
    private final Text columnNumber;
    private final VBox vbox;
    private final VBox vbox2;
    private final HBox hbox;
    //一つ一つの項目の雛形
    public NovelColumnsList(){
        vbox = new VBox(5);
        vbox2 = new VBox(5);
        hbox = new HBox(5);

        subTitle = new Text();
        chapterTitle = new Text();
        updateDay = new Text();
        columnNumber = new Text();

        subTitle.setFont(new Font("System Bold", 18));
        chapterTitle.setFont(new Font("System Bold", 40));

        VBox.setVgrow(subTitle, Priority.NEVER);
        VBox.setVgrow(updateDay, Priority.ALWAYS);
        HBox.setHgrow(vbox, Priority.NEVER);
        HBox.setHgrow(columnNumber, Priority.ALWAYS);
        vbox.getChildren().addAll(subTitle, updateDay);
        hbox.getChildren().addAll(columnNumber, vbox);

        VBox.setVgrow(chapterTitle, Priority.NEVER);
        vbox2.getChildren().addAll(chapterTitle);

        // ユーザ名、コメントをVBoxの幅-5で折り返すようにバインドしとく
        subTitle.wrappingWidthProperty().bind(vbox.widthProperty().subtract(5));
        updateDay.wrappingWidthProperty().bind(vbox.widthProperty().subtract(5));
        chapterTitle.wrappingWidthProperty().bind(vbox.widthProperty().subtract(5));
    }

    //スクロールなどの更新処理
    @Override
    protected void updateItem(NovelColumn item, boolean empty) {
        super.updateItem(item, empty);
        if(empty) {
            setText(null);
            setGraphic(null);
        } else {
            if(item.getPostDay().equals("")) {
                chapterTitle.setText(item.getTitle());
                setGraphic(vbox2);
            }else{
                subTitle.setText(item.getTitle());
                updateDay.setText(item.getPostDay() + "(" + item.getLastUpdateDay() + ")");
                columnNumber.setText(Integer.toString(item.getColumnNumber()));
                setGraphic(hbox);
            }
        }
    }
}