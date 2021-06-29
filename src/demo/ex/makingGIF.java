package demo.ex;

import java.awt.event.*;
import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.*;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

@SuppressWarnings("serial")
public class makingGIF {
    private JFileChooser fileChooser;
    private FileNameExtensionFilter filter;
    private File[] filePictures;     //紀錄選取的圖片檔
    private File fileVideo;     //紀錄選取的影音檔
    private int fileSelectionMode = JFileChooser.FILES_AND_DIRECTORIES;  //選擇模式:檔案或目錄
    private JFrame menu; //初始選單
    private JPanel panel;   //圖片區
    private JPanel panel2;  //影音區
    private JLabel label;
    private JLabel labelPictures;      //顯示是否上傳圖片
    private JLabel labelVideo;  //顯示是否上傳影音
    private JButton uploadPicturesButton;      //控制上傳圖片
    private JButton uploadVideoButton;      //控制上傳影音
    private JButton removePicturesButton;      //控制移除圖片
    private JButton removeVideoButton;      //控制移除影音
    private JButton start;            //控制開始編譯
    private Color DarkSeaGreen = new Color(143,188,143);  //深海洋綠
    private Color SlateGray = new Color(112,128,144);  //石板灰
    private boolean correctPictures;  //是否選擇達最低數量&正確圖片檔案類型
    private boolean correctVideo;     //是否選擇正確影音檔案類型

    //Creates new form makingGIF
    public makingGIF() {
        initComponents();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void initComponents() {
        menu = new JFrame("makingGIF");	//視窗
        menu.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        menu.setLocationByPlatform(true);
        menu.setSize(800, 600);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setLayout(null);

        label = new JLabel("歡迎使用makingGIF", JLabel.CENTER);
        label.setToolTipText("請選擇以下1種作為媒材");
        label.setBounds(170,70,400,50);
        label.setFont(new java.awt.Font("Dialog", 1, 40));	//(字體，粗體，大小)
        menu.add(label);

        final JFileChooser file1 = new JFileChooser();

        uploadPicturesButton = new JButton("上傳");	//按鈕
        uploadVideoButton = new JButton("上傳");
        removePicturesButton = new JButton("移除");	//按鈕
        removeVideoButton = new JButton("移除");
        start = new JButton("開始");
        uploadPicturesButton.setFont(new java.awt.Font("Dialog", 1, 30));
        uploadVideoButton.setFont(new java.awt.Font("Dialog", 1, 30));
        removePicturesButton.setFont(new java.awt.Font("Dialog", 1, 30));
        removeVideoButton.setFont(new java.awt.Font("Dialog", 1, 30));
        start.setFont(new java.awt.Font("Dialog", 1, 40));

        panel = new JPanel();	//面板
        panel.setBorder(javax.swing.BorderFactory.createTitledBorder("複數圖片檔"));
        labelPictures = new JLabel("尚未上傳圖片檔", JLabel.LEFT);
        labelPictures.setToolTipText("請選擇2~10張圖片檔");
        labelPictures.setForeground(SlateGray);
        labelPictures.setFont(new java.awt.Font("Dialog", 1, 30));	//(字體，粗體，大小)
        panel.add(labelPictures);
        panel.add(uploadPicturesButton);
        panel.add(removePicturesButton);
        panel.setBounds(90,150,240,180);

        panel2 = new JPanel();	//面板
        panel2.setBorder(javax.swing.BorderFactory.createTitledBorder("影音檔"));
        labelVideo = new JLabel("尚未上傳影音檔", JLabel.LEFT);
        labelVideo.setToolTipText("請選擇1個影音檔");
        labelVideo.setForeground(SlateGray);
        labelVideo.setFont(new java.awt.Font("Dialog", 1, 30));	//(字體，粗體，大小)
        panel2.add(labelVideo);
        panel2.add(uploadVideoButton);
        panel2.add(removeVideoButton);
        panel2.setBounds(430,150,240,180);

        start.setBounds(290,380,180,100);

        menu.add(panel);
        menu.add(panel2);
        menu.add(start);
        menu.setVisible(true);
        uploadPicturesButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //上傳多張圖片檔
                uploadPicturesButtonPerformed(e);
            }
        });
        uploadVideoButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e2){
                //上傳影音檔
                uploadVideoButtonPerformed(e2);
            }
        });
        removePicturesButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //移除圖片檔
                removePicturesButtonPerformed(e);
            }
        });
        removeVideoButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e2){
                //移除影音檔
                removeVideoButtonPerformed(e2);
            }
        });
        start.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e3){
                //開始編譯
                startPerformed(e3);
            }
        });
    }

    private void uploadPicturesButtonPerformed(java.awt.event.ActionEvent evt) {
        fileChooser = new JFileChooser();
        //初始化當前路徑
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File homeFile =fsv.getHomeDirectory();  //這便是讀取桌面路徑的方法了
        fileChooser.setCurrentDirectory(homeFile);
        //初始化檔案過濾器
        filter = new FileNameExtensionFilter("圖片檔案", "png", "jpg");
        fileChooser.setFileFilter(filter);
        //初始化選擇模式
        fileChooser.setFileSelectionMode(fileSelectionMode);
        fileChooser.setMultiSelectionEnabled(true);    //可複選
        //開啟檔案選擇器
        int i = fileChooser.showDialog(menu, "選擇");
        if(i == JFileChooser.APPROVE_OPTION){
            this.filePictures = fileChooser.getSelectedFiles();
            if(filePictures.length >= 2 & filePictures.length <= 10){ //檢查上傳之張數
                for (File file : this.filePictures) {
                    String fileType = file.getName().substring(file.getName().lastIndexOf("."));
                    fileType = fileType.toLowerCase();  //大小寫轉換
                    if(!fileType.equals(".png") & !fileType.equals(".jpg") ){ //檢查上傳之檔案類型
                        correctPictures = false;
                        break;
                    }
                    else{
                        correctPictures = true;
                    }
                }
            }
            else{
                correctPictures = false;
            }
            if(correctPictures){        //通知使用者已正確選取or需重新選取
                labelPictures.setText("已選取檔案");
                labelPictures.setForeground(DarkSeaGreen);
            }
            else{
                labelPictures.setText("未上傳正確檔案");
                JOptionPane.showMessageDialog(menu,"張數或類型錯誤\n請重新選擇!");
                labelPictures.setForeground(Color.RED);
            }
        }
    }

    private void uploadVideoButtonPerformed(java.awt.event.ActionEvent evt) {
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
        fileChooser.setMultiSelectionEnabled(false);  //只能單選
        //開啟檔案選擇器
        int i = fileChooser.showDialog(menu, "選擇");
        if(i == JFileChooser.APPROVE_OPTION){
            this.fileVideo = fileChooser.getSelectedFile();
            if(fileVideo != null){ //檢查是否上傳
                String fileType = fileVideo.getName().substring(fileVideo.getName().lastIndexOf("."));
                fileType = fileType.toLowerCase();  //大小寫轉換
                if(!fileType.equals(".mp4") & !fileType.equals(".mkv")){ //檢查上傳之檔案類型
                    correctVideo = false;
                }
                else{
                    correctVideo = true;
                }
            }
            else{
                correctVideo = false;
            }
            if(correctVideo){        //通知使用者已正確選取or需重新選取
                labelVideo.setText("已選取檔案");
                labelVideo.setForeground(DarkSeaGreen);
            }
            else{
                labelVideo.setText("未上傳正確檔案");
                JOptionPane.showMessageDialog(menu,"類型錯誤\n請重新選擇!");
                labelVideo.setForeground(Color.RED);
            }
        }
    }

    private void removePicturesButtonPerformed(java.awt.event.ActionEvent evt) {
        filePictures = null;
        correctPictures = false;
        labelPictures.setText("尚未上傳圖片檔");
        labelPictures.setForeground(SlateGray);
    }

    private void removeVideoButtonPerformed(java.awt.event.ActionEvent evt) {
        fileVideo = null;
        correctVideo = false;
        labelVideo.setText("尚未上傳影音檔");
        labelVideo.setForeground(SlateGray);
    }

    private void startPerformed(java.awt.event.ActionEvent evt) {
        //確認訊息
        if(!correctPictures & !correctVideo){//圖片&影音都沒選>>錯誤訊息
            JOptionPane.showMessageDialog(menu,"請至少正確上傳一項媒材!");
        }
        else if(correctPictures & correctVideo){//圖片&影音都選了>>錯誤訊息
            JOptionPane.showMessageDialog(menu,"不可兩項皆選\n請清除一項媒材!");
        }
        else if(correctPictures & !correctVideo){//選了正確的圖片檔>>呼叫imageGUI
            StringBuilder sb = new StringBuilder("您選擇的檔案分別是：");
            sb.append("\n");
            for (File file : this.filePictures) {
                sb.append("\n");
                sb.append(file.getName());
            }
            JOptionPane.showMessageDialog(menu,sb);
            
          //關閉首頁，開啟imageGUI
            imageGUI editPictures = new  imageGUI(filePictures); 
            menu.setVisible(false);

        }
        else if(!correctPictures & correctVideo){//選了正確的影音檔>>呼叫videoGUI
            StringBuilder sb = new StringBuilder("您選擇的檔案是：");
            sb.append("\n");
            sb.append(fileVideo.getName());
            JOptionPane.showMessageDialog(menu,sb);

            //關閉首頁，開啟vedioGUI
            menu.setVisible(false);
            new videoGUI(fileVideo);
        }
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
            java.util.logging.Logger.getLogger(makingGIF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(makingGIF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(makingGIF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(makingGIF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        //Create and display the form
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new makingGIF();
            }
        });
    }

}