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
    private File fileAudio;     //������������T��
    private File fileVideo;     //����������v����
    private int fileSelectionMode = JFileChooser.FILES_AND_DIRECTORIES;  //��ܼҦ�:�ɮשΥؿ�
    private JFrame demo; //���O
    private JLabel label;
    private JButton file1Button;      //����W�ǭ��T(���]�m�ɮ���������)
    private JButton file2Button;      //����W�Ǽv��(���]�m�ɮ���������)
    private JButton start;            //����}�l�sĶ�v��(���ˬd�O�_���ɮ׳��W��)
    private JPanel panel;
    private JLabel labelAudio;      //��ܬO�_�W��/�W�ǤF�����T�ɦW
    private JPanel panel2;
    private JLabel labelVideo;  //��ܬO�_�W��/�W�ǤF�����T�ɦW

    //Creates new form RemixGUI
    public RemixGUI() {
    	initComponents();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void initComponents() {
        demo = new JFrame("�v��MIX");	//����
        demo.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        demo.setLocationByPlatform(true);
        demo.setSize(800, 600);
        demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        demo.setLayout(null);			
                    
        label = new JLabel("�w��ϥμv��MIX", JLabel.CENTER);
        label.setBounds(170,70,400,50);
        label.setFont(new java.awt.Font("Dialog", 1, 40));	//(�r��A����A�j�p)
        demo.add(label);
                    
        final JFileChooser file1 = new JFileChooser();
                            
        file1Button = new JButton("�W��");	//���s
        file2Button = new JButton("�W��");
        start = new JButton("�}�l");
        file1Button.setFont(new java.awt.Font("Dialog", 1, 30));
        file2Button.setFont(new java.awt.Font("Dialog", 1, 30));
        start.setFont(new java.awt.Font("Dialog", 1, 40));

        panel = new JPanel();	//���O
        panel.setBorder(javax.swing.BorderFactory.createTitledBorder("���T��"));
        labelAudio = new JLabel("�|���W�ǭ��T��", JLabel.LEFT);
        labelAudio.setFont(new java.awt.Font("Dialog", 1, 20));	//(�r��A����A�j�p)
        panel.add(labelAudio);
        panel.add(file1Button);
        panel.setBounds(90,150,240,170);

        panel2 = new JPanel();	//���O
        panel2.setBorder(javax.swing.BorderFactory.createTitledBorder("�v����"));
        labelVideo = new JLabel("�|���W�Ǽv����", JLabel.LEFT);
        labelVideo.setFont(new java.awt.Font("Dialog", 1, 20));	//(�r��A����A�j�p)
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
                //�W�ǭ��T��
                file1ButtonPerformed(e);
            }
        });*/
        file2Button.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e2){ 
                //�W�Ǽv����
                file2ButtonPerformed(e2);
            }
        });
        start.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e3){ 
                //�}�l�sĶ�v��
                startPerformed(e3);
            }
        });
        pack();
    }
    
    /*private void file1ButtonPerformed(java.awt.event.ActionEvent evt) {
        fileChooser = new JFileChooser();
        //��l�Ʒ�e���|
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File homeFile =fsv.getHomeDirectory();  //�o�K�OŪ���ୱ���|����k�F
        fileChooser.setCurrentDirectory(homeFile);
        //��l���ɮ׹L�o��
        filter = new FileNameExtensionFilter("���T�ɮ�", "mp3", "wma");
        fileChooser.setFileFilter(filter);
        //��l�ƿ�ܼҦ�
        fileChooser.setFileSelectionMode(fileSelectionMode);
        //�}���ɮ׿�ܾ�
        int i = fileChooser.showDialog(this, "���");
        if(i == JFileChooser.APPROVE_OPTION){
            this.fileAudio = fileChooser.getSelectedFile();
            labelAudio.setText(fileAudio.getName());
        }
    }*/

    private void file2ButtonPerformed(java.awt.event.ActionEvent evt) {
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
        //�}���ɮ׿�ܾ�
        int i = fileChooser.showDialog(this, "���");
        if(i == JFileChooser.APPROVE_OPTION){
            this.fileVideo = fileChooser.getSelectedFile();
            labelVideo.setText(fileVideo.getName());
        }
    }

    private void startPerformed(java.awt.event.ActionEvent evt) {
        //�H�U�|��i�R��
        //StringBuilder sb = new StringBuilder("�z��ܪ��ɮ׸��|�O�G");
        //sb.append("\n");
        //sb.append(fileAudio.getPath());
        //sb.append("\n");
        //sb.append(fileVideo.getPath());
        //JOptionPane.showMessageDialog(this, sb);
        //TODO
    	video2Image demo = new video2Image(fileVideo);	//�ڤ����D������GUI�A�������b�o�H�K�I�s�ӺI�Ϯɶ�
    	demo.captureScreen(0, 3, 10, 3);	//�}�l�ɶ��]�ɡA���A��A�i�ơ^
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