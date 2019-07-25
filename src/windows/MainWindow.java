package windows;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import layout.NovelColumnsLayout;
import layout.NovelsListLayout;

public class MainWindow {

    private NovelColumnsLayout novelColumn;
    private HBox hbox;
    private VBox vbox;

    public MainWindow(FlowPane pane){
        vbox = new VBox(5);
        hbox = new HBox(5);
        novelColumn = new NovelColumnsLayout();
        NovelsListLayout novel = new NovelsListLayout(novelColumn);



        Button b1 = new Button("サイト");
//        マウスがクリックされたときのイベント
        EventHandler<MouseEvent> mouseClick = this::mouseClick;
        b1.addEventHandler( MouseEvent.MOUSE_CLICKED , mouseClick );


        VBox.setVgrow(b1, Priority.NEVER);
        HBox.setHgrow(vbox, Priority.NEVER);
        HBox.setHgrow(novel.getLayout(), Priority.ALWAYS);
        HBox.setHgrow(novelColumn.getLayout(), Priority.ALWAYS);
        hbox.getChildren().addAll(novel.getLayout(), novelColumn.getLayout());
        vbox.getChildren().addAll(b1,hbox);
        pane.getChildren().add( vbox );
    }

    private void mouseClick(MouseEvent event) {
        try {
            WebStage nst2 = new WebStage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
