package demo.ex;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class vedioGUI {
	
	//input startTime�T��]�ɡG���G��^�ڦ���Ѧҹ�
	//input endTime
	//input �Ϥ��i��
	//�}�l�I��button����video2Image(�I�ϼȦs�bgif\picture��Ƨ��^
	//�}�lgif�s�@button����imageGIF(������}�l�I��button�N���}�lgif�s�@button�A�^�ǧA�٨S�I�Ϥ��������~)
	//maybe�n��ܺI�ϵ��G�w���A���ڤ����D�O�n�g�b�o�٬Ovideo2Image
    private JFrame menu; //��l���
    private JPanel panel;   //�Ϥ���
    private JLabel label;   
    private JButton start;            //����}�l�sĶ
    private Color DarkSeaGreen = new Color(143,188,143);  //�`���v��
    private Color SlateGray = new Color(112,128,144);  //�۪O��
    private boolean correctTime;  //�O�_��ܥ��T�ɶ�
    private boolean correctNum;   //�O�_��ܥ��T�d��ƶq(2~10)
    private video2Image doVideo;

    //Creates new form makingGIF
    public vedioGUI(File fileVideo) {
    	doVideo = new video2Image(fileVideo);
    	initComponents();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void initComponents() {
        menu = new JFrame("vedio");	//����
        menu.setLocationByPlatform(true);
        menu.setSize(800, 600);
        menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menu.setLayout(null);			
                    
        label = new JLabel("�п�ܺI�Ϯɶ��αi��(2~10)", JLabel.CENTER);
        label.setBounds(170,70,400,50);
        label.setFont(new java.awt.Font("Dialog", 1, 20));	//(�r��A����A�j�p)
        menu.add(label);                     
       
        start = new JButton("�}�l");
        start.setFont(new java.awt.Font("Dialog", 1, 40));

        panel = new JPanel();	//���O
        panel.setBorder(javax.swing.BorderFactory.createTitledBorder("�ƼƹϤ���"));

        start.setBounds(290,380,180,100);
                    
        menu.add(panel);
        menu.add(start);
        menu.setVisible(true);

        start.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){ 
                //�}�l�sĶ
                startPerformed(e);
            }
        });
    }


    private void startPerformed(java.awt.event.ActionEvent evt) {
        //�T�{�T��
       /* if(!correctTime & !correctNum){//�ɶ�&�ƶq�����~>>���~�T��
           JOptionPane.showMessageDialog(menu,"�п�J���T�ɶ��μƶq(2~10)!");
        }
        else if(correctTime & !correctNum){//�ƶq��ܿ��~>>���~�T��
            JOptionPane.showMessageDialog(menu,"�п�J���T�ƶq(2~10)!");
        }
        else if(!correctTime & correctNum){//�ɶ���ܿ��~>>���~�T��
        	JOptionPane.showMessageDialog(menu,"�п�J���T�ɶ�!");
        }*/
        //else if(correctTime & correctNum){//��F���T���v����>>�I�sdoVideo.captureScreen()
        	doVideo.captureScreen(0, 3, 0, 5);
        	menu.setVisible(false);
            new imageGUI();
        //}
    }

}
