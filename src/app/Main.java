package app;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import windowUi.NovelColumnsLayout;
import windowUi.NovelsListLayout;

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

//        Button b1 = new Button("子ステージ開く");
//        マウスがクリックされたときのイベント
//        EventHandler<MouseEvent> mouseClick = this::mouseClick;
//        b1.addEventHandler( MouseEvent.MOUSE_CLICKED , mouseClick );
//        pane.getChildren().add( b1 );


        stage.setTitle("Hello World");
        stage.setScene(scene);
        stage.show();
    }

//    private void mouseClick(MouseEvent event) {
//        novelColumn.setItem("n9511bs");
//    }
    public static void main(String[] args) {
        launch(args);
    }
    public Stage getStage() {return stage;}
    public FlowPane getFlowPane() {return pane;}

    public NovelColumnsLayout getNovelColumn() {
        return novelColumn;
    }
}
