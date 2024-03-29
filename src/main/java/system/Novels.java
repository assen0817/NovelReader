package system;

import data.NovelColumn;
import javafx.application.Platform;
import layout.NovelsListLayout;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import windows.MessageWindow;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Novels {
    private static String novelNcodeSite = "ncode.syosetu.com";
    private static String novelAPI = "https://api.syosetu.com/novelapi/api/";
    private static NovelsListLayout novelsListLayouts;
    private static Boolean thread = false;
    private static int threadTimer = 0;

//更新する小説リストを登録する
    public static void setNovelList(NovelsListLayout novelsListLayouts){
        Novels.novelsListLayouts = novelsListLayouts;
    }

    //urlから小説を追加
    public static void add(String url){
        MessageWindow mes = new MessageWindow();
        if (!checkURL(url)) {
            mes.Error("このページからはダウンロードできません。");
            return;
        }
        String ncode = getNcode(url);

        if(ncode == null) {
            mes.Error("このページからはダウンロードできません。");
            return;
        }

        if(checkWhetherNovel(ncode)) {
            mes.Error("この小説は既に登録されています。");
            return;
        }


        if(!NovelALLInfo(ncode)) {
            mes.Error("小説を追加できませんでした。");
            return;
        }
        NovelColums(ncode);
        if(novelsListLayouts != null) novelsListLayouts.update();
        mes.finishNotice("小説が正常に追加されました。");

    }

    public static Boolean checkURL(String url){
        return url != null || !url.trim().isEmpty();
    }

    //URLからNcodeを取得
    public static String getNcode(String url){
        String[] urls = url.split("/");
        boolean nextNovel = false;
        for (String u : urls) {
            if(nextNovel){
                if(u.indexOf("n") == 0){
                    return u;
                }
                nextNovel = false;
            }
            if(u.equals(novelNcodeSite)){
                nextNovel = true;
            }
        }
        return null;
    }

//  追加したい小説がもう追加されているかどうかをチェック
    public static Boolean checkWhetherNovel(String ncode){
        List<String> localNcode = Files.getLocalNcode();
        if (localNcode == null) return false;
        for(String str: localNcode) if(str.equals(ncode))return true;
        return false;
    }

    public static Boolean NovelALLInfo(String ncode){
        return NovelAPI(ncode, "");
    }

    // 追加したい小説（ncodeで判別）の情報をAPIで取得する
    private static Boolean NovelAPI(String ncode, String parame) {
        try{
//             URLを作成してGET通信を行う
            URL url = new URL( novelAPI + "?ncode=" + ncode + parame);
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            http.setRequestMethod("GET");
            http.connect();
//             サーバーからのレスポンスを標準出力へ出す
            BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream(),"UTF-8"));
            Files.NovelWriter(ncode, reader);
            reader.close();
//            ボタン連打によるDOS攻撃防止（API提供サーバー負荷対策）
            Thread.sleep(1000);
            return true;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void NovelColums(String ncode){
        try {
            Document document = Jsoup.connect("https://" + novelNcodeSite + "/" + ncode).get();
            Files.NovelColumnsWriter(ncode, document);
//            ボタン連打によるDOS攻撃防止（API提供サーバー負荷対策）
            Thread.sleep(1000);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void NovelUpdate(){
        MessageWindow mes = new MessageWindow();
        if(thread) {
            mes.finishNotice("現在ダウンロード中：残り" + threadTimer + "秒");
            return;
        }
        thread = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> list = Files.getLocalNcode();
                if(list == null) {
                    thread = false;
                    return;
                }
                threadTimer = list.size() * 2;

                Platform.runLater(
                        () ->mes.finishNotice("ダウンロードを開始しました。" + threadTimer + "秒かかります。"));
                for(String ncode : list) {
                    NovelColums(ncode);
                    threadTimer--;
                    NovelALLInfo(ncode);
                    threadTimer--;
                }
                if(novelsListLayouts != null) {
                    Platform.runLater(
                            () -> novelsListLayouts.update());
                }

                thread = false;
            }
        }).start();

    }
}
