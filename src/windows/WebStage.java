package windows;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class WebStage extends Stage {
    WebView webView;
    private BorderPane pane;
    private WebEngine engine;
    private TextField urlBox;
    public WebStage() throws Exception{
        String indexURL = "https://yomou.syosetu.com";
        webView = new WebView();

        engine = webView.getEngine();
        pane = new BorderPane();
        pane.setCenter(webView);

        Scene scene = new Scene(pane,1200,800);
        setScene(scene);
        show();

        engine.load(indexURL);
        // テキスト入力
        urlBox = new TextField();
        urlBox.setText(indexURL);
        urlBox.setFont(new Font("sanserif", 12));
        urlBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadUrl();
            }
        });

        // ボタン
        Button button = new Button("開く");
        button.setFont(new Font("sanserif", 12));
        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                loadUrl();
            }
        });

        AnchorPane anPane = new AnchorPane();
        anPane.getChildren().addAll( urlBox , button );
        AnchorPane.setLeftAnchor(urlBox, 10.);
        AnchorPane.setRightAnchor(urlBox, 20.);
        AnchorPane.setRightAnchor(button, 20.);
        pane.setTop(anPane);
    }

    // ページのロード
    private void loadUrl() {
        String url = urlBox.getText();
        if (url != null && !url.trim().isEmpty()) {
            engine.load(url);
        }
    }

}
