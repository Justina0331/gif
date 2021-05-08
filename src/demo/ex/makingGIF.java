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
    private File[] filePictures;     //����������Ϥ���
    private File fileVideo;     //����������v����
    private int fileSelectionMode = JFileChooser.FILES_AND_DIRECTORIES;  //��ܼҦ�:�ɮשΥؿ�
    private JFrame menu; //��l���
    private JPanel panel;   //�Ϥ���
    private JPanel panel2;  //�v����
    private JLabel label;   
    private JLabel labelPictures;      //��ܬO�_�W�ǹϤ�
    private JLabel labelVideo;  //��ܬO�_�W�Ǽv��
    private JButton uploadPicturesButton;      //����W�ǹϤ�
    private JButton uploadVideoButton;      //����W�Ǽv��
    private JButton removePicturesButton;      //������Ϥ�
    private JButton removeVideoButton;      //������v��
    private JButton start;            //����}�l�sĶ
    private Color DarkSeaGreen = new Color(143,188,143);  //�`���v��
    private Color SlateGray = new Color(112,128,144);  //�۪O��
    private boolean correctPictures;  //�O�_��ܹF�̧C�ƶq&���T�Ϥ��ɮ�����
    private boolean correctVideo;     //�O�_��ܥ��T�v���ɮ�����

    //Creates new form makingGIF
    public makingGIF() {
    	initComponents();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void initComponents() {
        menu = new JFrame("makingGIF");	//����
        menu.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        menu.setLocationByPlatform(true);
        menu.setSize(800, 600);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setLayout(null);			
                    
        label = new JLabel("�w��ϥ�makingGIF", JLabel.CENTER);
        label.setToolTipText("�п�ܥH�U1�ا@���C��");
        label.setBounds(170,70,400,50);
        label.setFont(new java.awt.Font("Dialog", 1, 40));	//(�r��A����A�j�p)
        menu.add(label);
                    
        final JFileChooser file1 = new JFileChooser();
                            
        uploadPicturesButton = new JButton("�W��");	//���s
        uploadVideoButton = new JButton("�W��");
        removePicturesButton = new JButton("����");	//���s
        removeVideoButton = new JButton("����");
        start = new JButton("�}�l");
        uploadPicturesButton.setFont(new java.awt.Font("Dialog", 1, 30));
        uploadVideoButton.setFont(new java.awt.Font("Dialog", 1, 30));
        removePicturesButton.setFont(new java.awt.Font("Dialog", 1, 30));
        removeVideoButton.setFont(new java.awt.Font("Dialog", 1, 30));
        start.setFont(new java.awt.Font("Dialog", 1, 40));

        panel = new JPanel();	//���O
        panel.setBorder(javax.swing.BorderFactory.createTitledBorder("�ƼƹϤ���"));
        labelPictures = new JLabel("�|���W�ǹϤ���", JLabel.LEFT);
        labelPictures.setToolTipText("�п��2~10�i�Ϥ���");
        labelPictures.setForeground(SlateGray);
        labelPictures.setFont(new java.awt.Font("Dialog", 1, 30));	//(�r��A����A�j�p)
        panel.add(labelPictures);
        panel.add(uploadPicturesButton);
        panel.add(removePicturesButton);
        panel.setBounds(90,150,240,180);

        panel2 = new JPanel();	//���O
        panel2.setBorder(javax.swing.BorderFactory.createTitledBorder("�v����"));
        labelVideo = new JLabel("�|���W�Ǽv����", JLabel.LEFT);
        labelVideo.setToolTipText("�п��1�Ӽv����");
        labelVideo.setForeground(SlateGray);
        labelVideo.setFont(new java.awt.Font("Dialog", 1, 30));	//(�r��A����A�j�p)
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
                //�W�Ǧh�i�Ϥ���
                uploadPicturesButtonPerformed(e);
            }
        });
        uploadVideoButton.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e2){ 
                //�W�Ǽv����
                uploadVideoButtonPerformed(e2);
            }
        });
        removePicturesButton.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){ 
                //�����Ϥ���
                removePicturesButtonPerformed(e);
            }
        });
        removeVideoButton.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e2){ 
                //�����v����
                removeVideoButtonPerformed(e2);
            }
        });
        start.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e3){ 
                //�}�l�sĶ
                startPerformed(e3);
            }
        });
    }

    private void uploadPicturesButtonPerformed(java.awt.event.ActionEvent evt) {
        fileChooser = new JFileChooser();
        //��l�Ʒ�e���|
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File homeFile =fsv.getHomeDirectory();  //�o�K�OŪ���ୱ���|����k�F
        fileChooser.setCurrentDirectory(homeFile);
        //��l���ɮ׹L�o��
        filter = new FileNameExtensionFilter("�Ϥ��ɮ�", "png", "jpg");
        fileChooser.setFileFilter(filter);
        //��l�ƿ�ܼҦ�
        fileChooser.setFileSelectionMode(fileSelectionMode);
        fileChooser.setMultiSelectionEnabled(true);    //�i�ƿ�
        //�}���ɮ׿�ܾ�
        int i = fileChooser.showDialog(menu, "���");
        if(i == JFileChooser.APPROVE_OPTION){
            this.filePictures = fileChooser.getSelectedFiles();
            if(filePictures.length >= 2 & filePictures.length <= 10){ //�ˬd�W�Ǥ��i��
                for (File file : this.filePictures) {
                    String fileType = file.getName().substring(file.getName().lastIndexOf("."));
                    if(!fileType.equals(".png") & !fileType.equals(".jpg")){ //�ˬd�W�Ǥ��ɮ����� 
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
            if(correctPictures){        //�q���ϥΪ̤w���T���or�ݭ��s���
                labelPictures.setText("�w����ɮ�");
                labelPictures.setForeground(DarkSeaGreen);    
            }
            else{
                labelPictures.setText("���W�ǥ��T�ɮ�");
                JOptionPane.showMessageDialog(menu,"�i�Ʃ��������~\n�Э��s���!");
                labelPictures.setForeground(Color.RED);    
            }
        }
    }

    private void uploadVideoButtonPerformed(java.awt.event.ActionEvent evt) {
        fileChooser = new JFileChooser();
        //��l�Ʒ�e���|
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File homeFile =fsv.getHomeDirectory();  //�o�K�OŪ���ୱ���|����k�F
        fileChooser.setCurrentDirectory(homeFile);
        //��l���ɮ׹L�o��
        filter = new FileNameExtensionFilter("���T�ɮ�", "mp4", "mkv");
        fileChooser.setFileFilter(filter);
        //��l�ƿ�ܼҦ�
        fileSelectionMode = JFileChooser.FILES_AND_DIRECTORIES;
        fileChooser.setFileSelectionMode(fileSelectionMode);
        fileChooser.setMultiSelectionEnabled(false);  //�u����
        //�}���ɮ׿�ܾ�
        int i = fileChooser.showDialog(menu, "���");
        if(i == JFileChooser.APPROVE_OPTION){
            this.fileVideo = fileChooser.getSelectedFile();
            if(fileVideo != null){ //�ˬd�O�_�W��
                String fileType = fileVideo.getName().substring(fileVideo.getName().lastIndexOf("."));
                if(!fileType.equals(".mp4") & !fileType.equals(".mkv")){ //�ˬd�W�Ǥ��ɮ����� 
                    correctVideo = false;
                }
                else{
                         correctVideo = true;
                }
            }
            else{
                correctVideo = false;
            }
            if(correctVideo){        //�q���ϥΪ̤w���T���or�ݭ��s���
                labelVideo.setText("�w����ɮ�");
                labelVideo.setForeground(DarkSeaGreen);    
            }
            else{
                labelVideo.setText("���W�ǥ��T�ɮ�");
                JOptionPane.showMessageDialog(menu,"�������~\n�Э��s���!");
                labelVideo.setForeground(Color.RED);    
            }
        }
    }

    private void removePicturesButtonPerformed(java.awt.event.ActionEvent evt) {
        filePictures = null;
        correctPictures = false;
        labelPictures.setText("�|���W�ǹϤ���");
        labelPictures.setForeground(SlateGray);    
    }

    private void removeVideoButtonPerformed(java.awt.event.ActionEvent evt) {
        fileVideo = null;
        correctVideo = false;
        labelVideo.setText("�|���W�Ǽv����");
        labelVideo.setForeground(SlateGray); 
    }

    private void startPerformed(java.awt.event.ActionEvent evt) {
        //�T�{�T��
        if(!correctPictures & !correctVideo){//�Ϥ�&�v�����S��>>���~�T��
           JOptionPane.showMessageDialog(menu,"�Цܤ֥��T�W�Ǥ@���C��!");
        }
        else if(correctPictures & correctVideo){//�Ϥ�&�v������F>>���~�T��
            JOptionPane.showMessageDialog(menu,"���i�ⶵ�ҿ�\n�вM���@���C��!");
        }
        else if(correctPictures & !correctVideo){//��F���T���Ϥ���>>�I�simageGUI
            StringBuilder sb = new StringBuilder("�z��ܪ��ɮפ��O�O�G");
            sb.append("\n");
            for (File file : this.filePictures) {
                sb.append("\n");
                sb.append(file.getName());
             }
            JOptionPane.showMessageDialog(menu,sb);
            //TODO
        }
        else if(!correctPictures & correctVideo){//��F���T���v����>>�I�svideoGUI
            StringBuilder sb = new StringBuilder("�z��ܪ��ɮ׬O�G");
            sb.append("\n");
            sb.append(fileVideo.getName());
            JOptionPane.showMessageDialog(menu,sb);
            
            //���������A�}��vedioGUI�A���u��
            menu.setVisible(false);
            new vedioGUI(fileVideo);
            
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
