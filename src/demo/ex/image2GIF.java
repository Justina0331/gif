package demo.ex;

import java.awt.*;
import java.io.File;    //檔案讀寫
import java.io.FileWriter;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import java.util.concurrent.TimeUnit; //延遲用


public class image2GIF extends JFrame
{
    public ArrayList<ImageIcon> pictures = new ArrayList<ImageIcon>();

    /*public void showUploadedPictures(ArrayList<ImageIcon> pictures){    //顯示上傳圖片
        for (ImageIcon picture : pictures) {
            Image pic = picture.getImage().getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);   //設置圖片大小
            ImageIcon picAdjust = new ImageIcon(pic);
            JLabel label2 = new JLabel(picAdjust, SwingConstants.LEFT);
            label2.setToolTipText("This is " + picture);
            add(label2);
            //輪流彈出圖片
            JOptionPane.showInternalMessageDialog(null, null, "preview picture", JOptionPane.PLAIN_MESSAGE, picAdjust);
        }
    }*/

    public void makeGIF(File[] files, String height, String width, String time) { //圖片轉GIF

        File ffmpeg = new File("bin\\ffmpeg\\bin\\ffmpeg.exe");

        ArrayList<String> commands = new ArrayList<String>();
        commands.add(ffmpeg.getAbsolutePath());    //指令路徑

        //圖片路徑
        String picturesPath = files[0].getPath();
        picturesPath = picturesPath.substring(0, picturesPath.lastIndexOf("\\"));

        String pics = "";
        for(File f : files){
            pics += "file '" + f.getPath() + "'\r\n";
            pics += "duration " + time + "\r\n";
        }
        File pathTxt = new File(picturesPath + "\\path.txt");   //將檔案寫入路徑文件
        try {
            pathTxt.createNewFile();
            FileWriter writer = new FileWriter(picturesPath + "\\path.txt");
            writer.write(pics);
            writer.close();
            //System.out.println("Successfully wrote to the file.");
        }
        catch (IOException e){
            System.out.println("檔案建立失敗");
        }
        commands.add("-f");
        commands.add("concat");
        commands.add("-safe");
        commands.add("0");
        commands.add("-i");
        commands.add(picturesPath + "\\path.txt");
        System.out.println("Successfully deleted.");

        commands.add("-r");        //幀數
        commands.add("1");

        commands.add("-s"); //調整大小
        commands.add(Integer.parseInt(height) + "x" + Integer.parseInt(width));

        commands.add("-y");			//若檔案存在, 覆蓋檔案

        commands.add("test.gif");

        System.out.println(commands);
        try {
            ProcessBuilder builder = new ProcessBuilder(commands);
            builder.redirectErrorStream(true);
            Process process = builder.start();
            //readProcessOutput(process.getInputStream(), System.out);	//cmd執行結果(debug用)
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        pathTxt.delete();//刪除路徑txt，GIF製作完成
        commands.clear();

        //預覽播放
        File ffplay = new File("bin\\ffmpeg\\bin\\ffplay.exe");
        commands.add(ffplay.getAbsolutePath());
        commands.add("-framedrop");
        commands.add("-window_title");
        commands.add("執行兩秒就會當機的預覽播放");
        commands.add("test.gif");
        try {
            ProcessBuilder builder = new ProcessBuilder(commands);
            builder.redirectErrorStream(true);
            Process process = builder.start();
            //readProcessOutput(process.getInputStream(), System.out);	//cmd執行結果(debug用)
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} // end class image2GIF