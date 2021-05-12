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
	
	//input startTime三格（時：分：秒）我有放參考圖
	//input endTime
	//input 圖片張數
	//開始截圖button執行video2Image(截圖暫存在gif\picture資料夾）
	//開始gif製作button執行imageGIF(未執行開始截圖button就按開始gif製作button，回傳你還沒截圖之類的錯誤)
	//maybe要顯示截圖結果預覽，但我不知道是要寫在這還是video2Image
    private JFrame menu; //初始選單
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
    private JButton start;        //開始製作gif
    private JButton capture;      //開始截圖
    private JButton downLoad;     //下載
    private boolean correctTime;  //是否選擇正確時間
    private boolean correctNum;   //是否選擇正確範圍數量(2~10)
    private video2Image doVideo;

    //Creates new form makingGIF
    public vedioGUI(File fileVideo) {
    	doVideo = new video2Image(fileVideo);
    	initComponents();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void initComponents() {
    	
    	//視窗設定
        menu = new JFrame("vedio");
        menu.setLocationByPlatform(true);
        menu.setSize(800, 600);
        menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menu.setLayout(null);		
             
        //視窗加入標題
        JLabel titleLabel = new JLabel("請選擇截圖時間及張數(2~10)", JLabel.CENTER);
        titleLabel.setBounds(180,70,400,50);
        titleLabel.setFont(new java.awt.Font("Dialog", 1, 30));	//(字體，粗體，大小)
        menu.add(titleLabel);      
        
    	//input面板
        inputPanel = new JPanel();
        inputPanel.setBounds(220,200,320,170);
        
        //時分秒Label
        JLabel hourLabel = new JLabel("時");
        hourLabel.setFont(new java.awt.Font("Dialog", 1, 20));	//(字體，粗體，大小)
        JLabel minuteLabel = new JLabel("分");
        minuteLabel.setFont(new java.awt.Font("Dialog", 1, 20));	//(字體，粗體，大小)
        JLabel secondLabel = new JLabel("秒");
        secondLabel.setFont(new java.awt.Font("Dialog", 1, 20));	//(字體，粗體，大小)
        
        //開始時間Label
        JLabel startTimeLabel = new JLabel("開始時間", JLabel.LEFT);
        startTimeLabel.setBounds(0,0,200,50);
        startTimeLabel.setFont(new java.awt.Font("Dialog", 1, 20));	//(字體，粗體，大小)
        inputPanel.add(startTimeLabel);
        
        //開始時間輸入
        startHour = new JTextField("0", 3);
        startMinute = new JTextField("0", 3); 
        startSecond = new JTextField("1", 3);
        inputPanel.add(startHour);
        inputPanel.add(hourLabel);
        inputPanel.add(startMinute);
        inputPanel.add(minuteLabel);
        inputPanel.add(startSecond);
        inputPanel.add(secondLabel);
        
        //時分秒Label
        JLabel hourLabel1 = new JLabel("時");
        hourLabel1.setFont(new java.awt.Font("Dialog", 1, 20));	//(字體，粗體，大小)
        JLabel minuteLabel1 = new JLabel("分");
        minuteLabel1.setFont(new java.awt.Font("Dialog", 1, 20));	//(字體，粗體，大小)
        JLabel secondLabel1 = new JLabel("秒");
        secondLabel1.setFont(new java.awt.Font("Dialog", 1, 20));	//(字體，粗體，大小)
        
        //結束時間Label
        JLabel endTimeLabel = new JLabel("結束時間", JLabel.LEFT);
        endTimeLabel.setBounds(0,0,200,50);
        endTimeLabel.setFont(new java.awt.Font("Dialog", 1, 20));	//(字體，粗體，大小)
        inputPanel.add(endTimeLabel);
        
        //結束時間輸入
        endHour = new JTextField("0", 3);
        endMinute = new JTextField("0", 3); 
        endSecond = new JTextField("1", 3);
        inputPanel.add(endHour);
        inputPanel.add(hourLabel1);
        inputPanel.add(endMinute);
        inputPanel.add(minuteLabel1);
        inputPanel.add(endSecond);
        inputPanel.add(secondLabel1);
        
        //截圖張數Label
        JLabel imageNumLabel = new JLabel("截圖數量(2~10)", JLabel.LEFT);
        imageNumLabel.setBounds(0,0,200,50);
        imageNumLabel.setFont(new java.awt.Font("Dialog", 1, 20));	//(字體，粗體，大小)
        inputPanel.add(imageNumLabel);
        
        //截圖張數輸入
        num = new JTextField("2", 3);   
        inputPanel.add(num);
        
        //截圖Button
        capture = new JButton("截圖");
        capture.setFont(new java.awt.Font("Dialog", 1, 30));
        capture.setBounds(40,380,200,100);
        
        //開始gifButton
        start = new JButton("製作gif");
        start.setFont(new java.awt.Font("Dialog", 1, 30));
        start.setBounds(290,380,200,100);
        start.setEnabled(false);
        
        //下載Button
        downLoad = new JButton("下載圖片");
        downLoad.setFont(new java.awt.Font("Dialog", 1, 30));
        downLoad.setBounds(540,380,200,100);
        downLoad.setEnabled(false);

        //panel和button加入視窗
        menu.add(inputPanel);
        menu.add(capture);
        menu.add(start);
        menu.add(downLoad);
        menu.setVisible(true);
        
        //capture button按下後執行
        capture.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){ 
                //開始編譯
            	checkType(e);
            }
        });
        
        //downLoad button按下後執行
        downLoad.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){ 
                //TODO
            }
        });
        
        //start button按下後執行
        start.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){ 
            	menu.setVisible(false);
                new imageGUI();
            }
        });
        
    }
    
    private void checkType(ActionEvent e1) {
    	//將輸入轉為數字
    	try {
	    	startTime[0] = Integer.parseInt(startHour.getText());
	    	startTime[1] = Integer.parseInt(startMinute.getText());
	    	startTime[2] = Integer.parseInt(startSecond.getText());
	    	endTime[0] = Integer.parseInt(endHour.getText());
	    	endTime[1] = Integer.parseInt(endMinute.getText());
	    	endTime[2] = Integer.parseInt(endSecond.getText());
	    	imageNum = Integer.parseInt(num.getText());
	    	checkRange(e1);
    	} catch(Exception e){	//處理若使用者輸入的不是數字
    		JOptionPane.showMessageDialog(menu,"請輸入數字!");
    	}

    }
    
    private void checkRange(ActionEvent e2) {
	    //TODO 判斷影片範圍跟張數範圍
    	correctTime = true;
    	correctNum = true;
	    startPerformed(e2);
    }


    private void startPerformed(ActionEvent e3) {
        //確認訊息
        if(!correctTime & !correctNum){//時間&數量都錯誤>>錯誤訊息
           JOptionPane.showMessageDialog(menu,"請輸入正確時間及張數(2~10)!");
        }
        else if(correctTime & !correctNum){//數量選擇錯誤>>錯誤訊息
            JOptionPane.showMessageDialog(menu,"請輸入正確數量(2~10)!");
        }
        else if(!correctTime & correctNum){//時間選擇錯誤>>錯誤訊息
        	JOptionPane.showMessageDialog(menu,"請輸入正確時間!");
        }
        else if(correctTime & correctNum){//選了正確的影音檔>>呼叫doVideo.captureScreen()
        	doVideo.captureScreen(startTime[0], startTime[1], startTime[2], endTime[0], endTime[1], endTime[2], imageNum);
        	downLoad.setEnabled(true);
        	start.setEnabled(true);
        }
    }

}
