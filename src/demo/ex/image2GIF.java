package demo.ex;

import java.io.File;
import java.awt.FlowLayout; // specifies how components are arranged
import java.util.ArrayList;
import javax.swing.*;
import java.util.concurrent.TimeUnit; //延遲用

import java.awt.Image;

public class image2GIF extends JFrame
{
    private final JLabel label = new JLabel();
    public ArrayList<ImageIcon> pictures = new ArrayList<ImageIcon>();
    public image2GIF(File[] files)      //新增所有圖片到array
    {
        super("image2GIF");
        setLayout(new FlowLayout());
        for (File file: files) {
            pictures.add(new ImageIcon(file.getPath()));
            //System.out.print(file.getPath());
        }
        showUploadedPictures(pictures);
        //new imageGUI();
    }

    public void showUploadedPictures(ArrayList<ImageIcon> pictures){    //顯示上傳圖片
        for (ImageIcon picture : pictures) {
            Image pic = picture.getImage().getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);   //設置圖片大小
            ImageIcon picAdjust = new ImageIcon(pic);
            JLabel label2 = new JLabel(picAdjust, SwingConstants.LEFT);
            label2.setToolTipText("This is " + picture);
            add(label2);
            //輪流彈出圖片
            JOptionPane.showInternalMessageDialog(null, null, "preview picture", JOptionPane.PLAIN_MESSAGE, picAdjust);
        }
        //失敗的delay
           /* try {
                TimeUnit.MILLISECONDS.sleep(2000);
                //System.out.println("執行緒執行...------------");
                //System.out.println("執行緒執行..." + picture);
            } catch (InterruptedException e) {
                System.out.println("執行緒中斷了...");
                return;
            }*/
    }
} // end class LabelFrame