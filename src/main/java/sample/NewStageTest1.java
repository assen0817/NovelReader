package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
/**
 * 子Stageを開く試験
 * @author tomo
 */
public class NewStageTest1 extends Application {
    Stage stage;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage sstage) throws Exception {
        stage = sstage;
        FlowPane pane = new FlowPane();
        Scene scene = new Scene(pane, 280, 200);
        Button b1 = new Button("子ステージ開く");
        //マウスがクリックされたときのイベント
        EventHandler<MouseEvent> mouseClick = (event ) -> this.mouseClick( event );
        b1.addEventHandler( MouseEvent.MOUSE_CLICKED , mouseClick );
        pane.getChildren().add( b1 );
        stage.setScene(scene);
        stage.show();
    }

    private void mouseClick( MouseEvent e ){
        NewStageTest2 nst2 = new NewStageTest2( stage );
    }

}

class NewStageTest2 extends Stage {

    public NewStageTest2( Stage oya ){
        super();
        FlowPane pane = new FlowPane();
        Button b1 = new Button("子ステージ");
        pane.getChildren().add( b1 );
        Scene scene = new Scene(pane, 260, 150);
        this.setX( oya.getX() + 30);
        this.setY( oya.getY() + 30);
        this.setScene(scene);
        this.show();
    }

}