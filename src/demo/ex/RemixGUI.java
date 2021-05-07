package demo.ex;

import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

@SuppressWarnings("serial")
public class RemixGUI extends javax.swing.JFrame {
    private JFileChooser fileChooser;
    private FileNameExtensionFilter filter;
    private File fileAudio;     //紀錄選取的音訊檔
    private File fileVideo;     //紀錄選取的影音檔
    private int fileSelectionMode = JFileChooser.FILES_AND_DIRECTORIES;  //選擇模式:檔案或目錄
    private JFrame demo; //面板
    private JLabel label;
    private JButton file1Button;      //控制上傳音訊(未設置檔案類型防錯)
    private JButton file2Button;      //控制上傳影音(未設置檔案類型防錯)
    private JButton start;            //控制開始編譯影片(未檢查是否兩檔案都上傳)
    private JPanel panel;
    private JLabel labelAudio;      //顯示是否上傳/上傳了的音訊檔名
    private JPanel panel2;
    private JLabel labelVideo;  //顯示是否上傳/上傳了的音訊檔名

    //Creates new form RemixGUI
    public RemixGUI() {
    	initComponents();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void initComponents() {
        demo = new JFrame("影音MIX");	//視窗
        demo.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        demo.setLocationByPlatform(true);
        demo.setSize(800, 600);
        demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        demo.setLayout(null);			
                    
        label = new JLabel("歡迎使用影音MIX", JLabel.CENTER);
        label.setBounds(170,70,400,50);
        label.setFont(new java.awt.Font("Dialog", 1, 40));	//(字體，粗體，大小)
        demo.add(label);
                    
        final JFileChooser file1 = new JFileChooser();
                            
        file1Button = new JButton("上傳");	//按鈕
        file2Button = new JButton("上傳");
        start = new JButton("開始");
        file1Button.setFont(new java.awt.Font("Dialog", 1, 30));
        file2Button.setFont(new java.awt.Font("Dialog", 1, 30));
        start.setFont(new java.awt.Font("Dialog", 1, 40));

        panel = new JPanel();	//面板
        panel.setBorder(javax.swing.BorderFactory.createTitledBorder("音訊檔"));
        labelAudio = new JLabel("尚未上傳音訊檔", JLabel.LEFT);
        labelAudio.setFont(new java.awt.Font("Dialog", 1, 20));	//(字體，粗體，大小)
        panel.add(labelAudio);
        panel.add(file1Button);
        panel.setBounds(90,150,240,170);

        panel2 = new JPanel();	//面板
        panel2.setBorder(javax.swing.BorderFactory.createTitledBorder("影音檔"));
        labelVideo = new JLabel("尚未上傳影音檔", JLabel.LEFT);
        labelVideo.setFont(new java.awt.Font("Dialog", 1, 20));	//(字體，粗體，大小)
        panel2.add(labelVideo);
        panel2.add(file2Button);
        panel2.setBounds(430,150,240,170);

        start.setBounds(290,380,180,100);
                    
        demo.add(panel);
        demo.add(panel2);
        demo.add(start);
        demo.setVisible(true);
        /*file1Button.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){ 
                //上傳音訊檔
                file1ButtonPerformed(e);
            }
        });*/
        file2Button.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e2){ 
                //上傳影音檔
                file2ButtonPerformed(e2);
            }
        });
        start.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e3){ 
                //開始編譯影片
                startPerformed(e3);
            }
        });
        pack();
    }
    
    /*private void file1ButtonPerformed(java.awt.event.ActionEvent evt) {
        fileChooser = new JFileChooser();
        //初始化當前路徑
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File homeFile =fsv.getHomeDirectory();  //這便是讀取桌面路徑的方法了
        fileChooser.setCurrentDirectory(homeFile);
        //初始化檔案過濾器
        filter = new FileNameExtensionFilter("音訊檔案", "mp3", "wma");
        fileChooser.setFileFilter(filter);
        //初始化選擇模式
        fileChooser.setFileSelectionMode(fileSelectionMode);
        //開啟檔案選擇器
        int i = fileChooser.showDialog(this, "選擇");
        if(i == JFileChooser.APPROVE_OPTION){
            this.fileAudio = fileChooser.getSelectedFile();
            labelAudio.setText(fileAudio.getName());
        }
    }*/

    private void file2ButtonPerformed(java.awt.event.ActionEvent evt) {
        fileChooser = new JFileChooser();
        //初始化當前路徑
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File homeFile =fsv.getHomeDirectory();  //這便是讀取桌面路徑的方法了
        fileChooser.setCurrentDirectory(homeFile);
        //初始化檔案過濾器
        filter = new FileNameExtensionFilter("視訊檔案", "mp4", "mkv");
        fileChooser.setFileFilter(filter);
        //初始化選擇模式
        fileSelectionMode = JFileChooser.FILES_AND_DIRECTORIES;
        fileChooser.setFileSelectionMode(fileSelectionMode);
        //開啟檔案選擇器
        int i = fileChooser.showDialog(this, "選擇");
        if(i == JFileChooser.APPROVE_OPTION){
            this.fileVideo = fileChooser.getSelectedFile();
            labelVideo.setText(fileVideo.getName());
        }
    }

    private void startPerformed(java.awt.event.ActionEvent evt) {
        //以下四行可刪除
        //StringBuilder sb = new StringBuilder("您選擇的檔案路徑是：");
        //sb.append("\n");
        //sb.append(fileAudio.getPath());
        //sb.append("\n");
        //sb.append(fileVideo.getPath());
        //JOptionPane.showMessageDialog(this, sb);
        //TODO
    	video2Image demo = new video2Image(fileVideo);	//我不知道怎麼跳轉GUI，先直接在這隨便呼叫個截圖時間
    	demo.captureScreen(0, 3, 10, 3);	//開始時間（時，分，秒，張數）
    }
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        //Set the Nimbus look and feel 
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } 
        catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RemixGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RemixGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RemixGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RemixGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        //Create and display the form 
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RemixGUI();
            }
        });
    }

}