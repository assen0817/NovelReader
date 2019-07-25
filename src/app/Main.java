package app;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import layout.NovelColumnsLayout;
import layout.NovelsListLayout;
import windows.WebStage;

public class Main extends Application {
    private Stage stage;
    private FlowPane pane;
    private NovelColumnsLayout novelColumn;
    private HBox hbox;
    private VBox vbox;
    @Override
    public void start(Stage primaryStage) throws Exception{
        vbox = new VBox(5);
        hbox = new HBox(5);
        stage = primaryStage;
        pane = new FlowPane();
        Scene scene = new Scene(pane, 1000, 750);

        NovelsListLayout novel = new NovelsListLayout(this);
        novelColumn = new NovelColumnsLayout(this );

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

        stage.setTitle("Hello World");
        stage.setScene(scene);
        stage.show();
    }

    private void mouseClick(MouseEvent event) {
        try {
            WebStage nst2 = new WebStage( stage );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
    public Stage getStage() {return stage;}
    public FlowPane getFlowPane() {return pane;}

    public NovelColumnsLayout getNovelColumn() {
        return novelColumn;
    }
}
