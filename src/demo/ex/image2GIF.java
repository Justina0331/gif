package demo.ex;


import java.io.File;
import java.awt.FlowLayout; // specifies how components are arranged
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;
import java.util.concurrent.TimeUnit; //延遲用

import java.awt.Image;

public class image2GIF extends JFrame
{
    private final JLabel label = new JLabel();
    public ArrayList<ImageIcon> pictures = new ArrayList<ImageIcon>();
    // image2GIF(){  }

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

    public void makeGIF(File[] files, String height, String width) { //圖片轉GIF

        File ffmpeg = new File("bin\\ffmpeg\\bin\\ffmpeg.exe");

        ArrayList<String> commands = new ArrayList<String>();
        commands.add(ffmpeg.getAbsolutePath());    //指令路徑

        //圖片路徑(目前僅適用有規律的檔名(影片轉GIF),會再修正)
        String picturesPath = files[0].getPath();
        picturesPath = picturesPath.substring(0, picturesPath.lastIndexOf("\\"));
        commands.add("-i");
        commands.add(picturesPath + "\\capture%d.jpg");

        commands.add("-r");        //幀數

        commands.add("10");

        //commands.add("scale = " + height + "：" + width); //調整大小

        commands.add("-y");			//若檔案存在, 覆蓋檔案

        commands.add("test.gif");

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
    }


} // end class image2GIF