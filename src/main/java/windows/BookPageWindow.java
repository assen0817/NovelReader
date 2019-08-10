package windows;

import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class BookPageWindow extends Stage {
    private Stage page;
    private FlowPane pane;

    //読書画面用のWindowを生成
    public BookPageWindow(String pageTitle) throws Exception{
        page = new Stage();
        pane = new FlowPane();
        Scene scene = new Scene(pane, 1000, 750);
        page = new Stage();

        page.setTitle(pageTitle);
        page.setScene(scene);
        page.show();
    }

}