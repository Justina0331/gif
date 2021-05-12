package demo.ex;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class vedioGUI {
	
	//input startTime�T��]�ɡG���G��^�ڦ���Ѧҹ�
	//input endTime
	//input �Ϥ��i��
	//�}�l�I��button����video2Image(�I�ϼȦs�bgif\picture��Ƨ��^
	//�}�lgif�s�@button����imageGIF(������}�l�I��button�N���}�lgif�s�@button�A�^�ǧA�٨S�I�Ϥ��������~)
	//maybe�n��ܺI�ϵ��G�w���A���ڤ����D�O�n�g�b�o�٬Ovideo2Image
    private JFrame menu; //��l���
    private JPanel inputPanel;
    private JTextField startHour;
    private JTextField startMinute;
    private JTextField startSecond;
    private JTextField endHour;
    private JTextField endMinute;
    private JTextField endSecond;
    private JTextField num;
    private int[] startTime = new int[3];
    private int[] endTime = new int[3];
    private int imageNum;
    private JButton start;        //�}�l�s�@gif
    private JButton capture;      //�}�l�I��
    private JButton downLoad;     //�U��
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
    	
    	//�����]�w
        menu = new JFrame("vedio");
        menu.setLocationByPlatform(true);
        menu.setSize(800, 600);
        menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menu.setLayout(null);		
             
        //�����[�J���D
        JLabel titleLabel = new JLabel("�п�ܺI�Ϯɶ��αi��(2~10)", JLabel.CENTER);
        titleLabel.setBounds(180,70,400,50);
        titleLabel.setFont(new java.awt.Font("Dialog", 1, 30));	//(�r��A����A�j�p)
        menu.add(titleLabel);      
        
    	//input���O
        inputPanel = new JPanel();
        inputPanel.setBounds(220,200,320,170);
        
        //�ɤ���Label
        JLabel hourLabel = new JLabel("��");
        hourLabel.setFont(new java.awt.Font("Dialog", 1, 20));	//(�r��A����A�j�p)
        JLabel minuteLabel = new JLabel("��");
        minuteLabel.setFont(new java.awt.Font("Dialog", 1, 20));	//(�r��A����A�j�p)
        JLabel secondLabel = new JLabel("��");
        secondLabel.setFont(new java.awt.Font("Dialog", 1, 20));	//(�r��A����A�j�p)
        
        //�}�l�ɶ�Label
        JLabel startTimeLabel = new JLabel("�}�l�ɶ�", JLabel.LEFT);
        startTimeLabel.setBounds(0,0,200,50);
        startTimeLabel.setFont(new java.awt.Font("Dialog", 1, 20));	//(�r��A����A�j�p)
        inputPanel.add(startTimeLabel);
        
        //�}�l�ɶ���J
        startHour = new JTextField("0", 3);
        startMinute = new JTextField("0", 3); 
        startSecond = new JTextField("1", 3);
        inputPanel.add(startHour);
        inputPanel.add(hourLabel);
        inputPanel.add(startMinute);
        inputPanel.add(minuteLabel);
        inputPanel.add(startSecond);
        inputPanel.add(secondLabel);
        
        //�ɤ���Label
        JLabel hourLabel1 = new JLabel("��");
        hourLabel1.setFont(new java.awt.Font("Dialog", 1, 20));	//(�r��A����A�j�p)
        JLabel minuteLabel1 = new JLabel("��");
        minuteLabel1.setFont(new java.awt.Font("Dialog", 1, 20));	//(�r��A����A�j�p)
        JLabel secondLabel1 = new JLabel("��");
        secondLabel1.setFont(new java.awt.Font("Dialog", 1, 20));	//(�r��A����A�j�p)
        
        //�����ɶ�Label
        JLabel endTimeLabel = new JLabel("�����ɶ�", JLabel.LEFT);
        endTimeLabel.setBounds(0,0,200,50);
        endTimeLabel.setFont(new java.awt.Font("Dialog", 1, 20));	//(�r��A����A�j�p)
        inputPanel.add(endTimeLabel);
        
        //�����ɶ���J
        endHour = new JTextField("0", 3);
        endMinute = new JTextField("0", 3); 
        endSecond = new JTextField("1", 3);
        inputPanel.add(endHour);
        inputPanel.add(hourLabel1);
        inputPanel.add(endMinute);
        inputPanel.add(minuteLabel1);
        inputPanel.add(endSecond);
        inputPanel.add(secondLabel1);
        
        //�I�ϱi��Label
        JLabel imageNumLabel = new JLabel("�I�ϼƶq(2~10)", JLabel.LEFT);
        imageNumLabel.setBounds(0,0,200,50);
        imageNumLabel.setFont(new java.awt.Font("Dialog", 1, 20));	//(�r��A����A�j�p)
        inputPanel.add(imageNumLabel);
        
        //�I�ϱi�ƿ�J
        num = new JTextField("2", 3);   
        inputPanel.add(num);
        
        //�I��Button
        capture = new JButton("�I��");
        capture.setFont(new java.awt.Font("Dialog", 1, 30));
        capture.setBounds(40,380,200,100);
        
        //�}�lgifButton
        start = new JButton("�s�@gif");
        start.setFont(new java.awt.Font("Dialog", 1, 30));
        start.setBounds(290,380,200,100);
        start.setEnabled(false);
        
        //�U��Button
        downLoad = new JButton("�U���Ϥ�");
        downLoad.setFont(new java.awt.Font("Dialog", 1, 30));
        downLoad.setBounds(540,380,200,100);
        downLoad.setEnabled(false);

        //panel�Mbutton�[�J����
        menu.add(inputPanel);
        menu.add(capture);
        menu.add(start);
        menu.add(downLoad);
        menu.setVisible(true);
        
        //capture button���U�����
        capture.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){ 
                //�}�l�sĶ
            	checkType(e);
            }
        });
        
        //downLoad button���U�����
        downLoad.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){ 
                //TODO
            }
        });
        
        //start button���U�����
        start.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){ 
            	menu.setVisible(false);
                new imageGUI();
            }
        });
        
    }
    
    private void checkType(ActionEvent e1) {
    	//�N��J�ର�Ʀr
    	try {
	    	startTime[0] = Integer.parseInt(startHour.getText());
	    	startTime[1] = Integer.parseInt(startMinute.getText());
	    	startTime[2] = Integer.parseInt(startSecond.getText());
	    	endTime[0] = Integer.parseInt(endHour.getText());
	    	endTime[1] = Integer.parseInt(endMinute.getText());
	    	endTime[2] = Integer.parseInt(endSecond.getText());
	    	imageNum = Integer.parseInt(num.getText());
	    	checkRange(e1);
    	} catch(Exception e){	//�B�z�Y�ϥΪ̿�J�����O�Ʀr
    		JOptionPane.showMessageDialog(menu,"�п�J�Ʀr!");
    	}

    }
    
    private void checkRange(ActionEvent e2) {
	    //TODO �P�_�v���d���i�ƽd��
    	correctTime = true;
    	correctNum = true;
	    startPerformed(e2);
    }


    private void startPerformed(ActionEvent e3) {
        //�T�{�T��
        if(!correctTime & !correctNum){//�ɶ�&�ƶq�����~>>���~�T��
           JOptionPane.showMessageDialog(menu,"�п�J���T�ɶ��αi��(2~10)!");
        }
        else if(correctTime & !correctNum){//�ƶq��ܿ��~>>���~�T��
            JOptionPane.showMessageDialog(menu,"�п�J���T�ƶq(2~10)!");
        }
        else if(!correctTime & correctNum){//�ɶ���ܿ��~>>���~�T��
        	JOptionPane.showMessageDialog(menu,"�п�J���T�ɶ�!");
        }
        else if(correctTime & correctNum){//��F���T���v����>>�I�sdoVideo.captureScreen()
        	doVideo.captureScreen(startTime[0], startTime[1], startTime[2], endTime[0], endTime[1], endTime[2], imageNum);
        	downLoad.setEnabled(true);
        	start.setEnabled(true);
        }
    }

}
