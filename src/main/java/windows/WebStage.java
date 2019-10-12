package windows;

import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import system.Novels;

import java.io.IOException;

public class WebStage extends Stage {
    WebView webView;
    private BorderPane pane;
    private WebEngine engine;
    private TextField urlBox;
    //Webサイト画面用のWindowを生成
    public WebStage() throws Exception{
        String indexURL = "https://yomou.syosetu.com";
        webView = new WebView();

        engine = webView.getEngine();
        engine.load(indexURL);


        pane = new BorderPane();
        pane.setCenter(webView);

        Scene scene = new Scene(pane,1200,800);
        setScene(scene);
        show();

        // アドレスバー
        urlBox = new TextField();
        urlBox.setText(indexURL);
        urlBox.setFont(new Font("sanserif", 12));
        urlBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadUrl();
            }
        });
        engine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (Worker.State.SUCCEEDED.equals(newValue)) {
                urlBox.setText(engine.getLocation());
            }
        });

        // ページの更新ボタン
        Button updateButton = new Button("開く");
        updateButton.setFont(new Font("sanserif", 12));
        updateButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                loadUrl();
            }
        });

//        ページの小説をダウンロードボタン
        Button downloadButton = new Button("追加");
        downloadButton.setFont(new Font("sanserif", 12));
        downloadButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                    Novels.add(engine.getLocation());
            }
        });

//        ページの小説をダウンロードボタン
        Button backButton = new Button("<");
        downloadButton.setFont(new Font("sanserif", 12));
        downloadButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                onBack();
            }
        });

//
        Button nextButton = new Button(">");
        downloadButton.setFont(new Font("sanserif", 12));
        downloadButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                onNext();
            }
        });

        AnchorPane anPane = new AnchorPane();
        anPane.getChildren().addAll( backButton, nextButton ,urlBox , updateButton, downloadButton );
        AnchorPane.setLeftAnchor(backButton, 0.);
        AnchorPane.setLeftAnchor(nextButton, 25.);
        AnchorPane.setLeftAnchor(urlBox, 50.);
        AnchorPane.setRightAnchor(urlBox, 90.);
        AnchorPane.setRightAnchor(updateButton, 60.);
        AnchorPane.setRightAnchor(downloadButton, 10.);
        pane.setTop(anPane);
    }

    // ページのロード
    private void loadUrl() {
        String url = urlBox.getText();
        if (url != null && !url.trim().isEmpty()) {
            engine.load(url);
        }
    }

    //戻るボタン
    public void onBack() {
        WebEngine engine = webView.getEngine();
        WebHistory history = engine.getHistory();
        if ( history.getCurrentIndex() > 0 ) history.go(-1);
    }

    //進むボタン
    public void onNext() {
        WebEngine engine = webView.getEngine();
        WebHistory history = engine.getHistory();
        if ( history.getCurrentIndex() < history.getEntries().size() - 1 ) history.go(1);
    }

}
