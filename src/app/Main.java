package app;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import layout.NovelColumnsLayout;
import layout.NovelsListLayout;
import windows.WebStage;

public class Main extends Application {
    private Stage stage;
    private FlowPane pane;
    private NovelColumnsLayout novelColumn;
    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        pane = new FlowPane();
        Scene scene = new Scene(pane, 1000, 750);
        NovelsListLayout novel = new NovelsListLayout(this);
        novelColumn = new NovelColumnsLayout(this );

        Button b1 = new Button("子ステージ開く");
//        マウスがクリックされたときのイベント
        EventHandler<MouseEvent> mouseClick = this::mouseClick;
        b1.addEventHandler( MouseEvent.MOUSE_CLICKED , mouseClick );
        pane.getChildren().add( b1 );


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
