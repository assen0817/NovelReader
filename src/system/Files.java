package system;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

//ファイルの操作全般
public class Files {
    public static final String novels = "src/novels/";
    public static final String datafile = "/.NovelData";
//    小説の情報をファイルに書き込む
    public static void NovelWriter(String ncode, BufferedReader data) throws IOException {
        String dir =  novels + ncode;
        File folder = new File(dir);
        if(!folder.exists()) folder.mkdir();
        String file = dir + datafile;
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
        String line="";
        while((line = data.readLine()) != null)
            pw.println(line);
        pw.close();
    }
//    ファイルに保存された小説情報を返す
    public static HashMap<String, String> NovelReader(String ncode) {
        HashMap<String, String> map = new HashMap<String, String>();//[1]
        String dir =  novels + ncode;
        File folder = new File(dir);
        if(!folder.exists()) return map;
        String fileDir = dir + datafile;

        File file = new File(fileDir);
        if(!file.exists()) return map;

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            String key = "";
            StringBuilder value = new StringBuilder();
            while ((line = br.readLine()) != null) {
                if (line.contains(":")) {
                    System.out.println(key);
                    System.out.println(value);
                    map.put(key, value.toString());
                    key = "";
                    value = new StringBuilder();
                    String[] str = line.split(":");
                    int cnt = 0;
                    key = str[0].trim();
                    if(str.length >= 2)
                        value.append(str[1].trim());
                    continue;
                }
                if (!key.equals("")) {
                    value.append(line.trim()).append(" ");
                }
            }
            map.put(key, String.valueOf(value));

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
//    小説の章のデータを書き込む
    public static void NovelColumnsWriter(PrintWriter pw, BufferedReader data){

    }
//    小説の章（本文）をファイルから読み取る
    public static void NovelColumnReader(String ncode, int index){

    }
}