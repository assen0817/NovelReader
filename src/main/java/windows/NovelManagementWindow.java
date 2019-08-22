package windows;

import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import layout.NovelColumnsLayout;
import layout.NovelsListLayout;
import system.Novels;

public class NovelManagementWindow {

    private HBox hbox;

    public NovelManagementWindow(BorderPane pane){
        Novels.NovelColums("n6774eh");
        hbox = new HBox(5);
//        小説の章
        NovelColumnsLayout novelColumn = new NovelColumnsLayout();
//        小説
        NovelsListLayout novel = new NovelsListLayout(novelColumn);
        Novels.setNovelList(novel);

        HBox.setHgrow(novel.getLayout(), Priority.ALWAYS);
        HBox.setHgrow(novelColumn.getLayout(), Priority.ALWAYS);
        hbox.getChildren().addAll(novel.getLayout(), novelColumn.getLayout());

        pane.setCenter( hbox );
        setMenu(pane);
    }

    //メニューバーの設定
    private void setMenu(BorderPane pane){
        MenuBar menu = new MenuBar();
        Menu menu1 = new Menu("一般");
        MenuItem menuItem11 = new MenuItem("更新");
        MenuItem menuItem12 = new MenuItem("設定");
        menuItem11.addEventHandler( ActionEvent.ANY , e -> mouseClick_update ());
        menu1.getItems().add(menuItem11);
        menu1.getItems().add(menuItem12);

        Menu menu2 = new Menu("小説追加");
        MenuItem menuItem21 = new MenuItem("サイト");
        MenuItem menuItem22 = new MenuItem("URL");
        menuItem21.addEventHandler( ActionEvent.ANY , e -> mouseClick_webPage());
        menu2.getItems().add(menuItem21);
        menu2.getItems().add(menuItem22);

        menu.getMenus().add(menu1);
        menu.getMenus().add(menu2);
        pane.setTop(menu);
    }


    private void mouseClick_webPage() {
        try {
            WebStage nst2 = new WebStage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mouseClick_update() {

    }
}
