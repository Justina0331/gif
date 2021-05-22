package demo.ex;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;

import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.FocusEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class vedioGUI {

	private int vedioLong;//影片長度
    private JFrame menu; //初始選單
    private JPanel inputPanel = new JPanel();
    private JPanel picturePanel = new JPanel();
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
    private JButton[] chooseImage;

    //Creates new form makingGIF
    public vedioGUI(File fileVideo) {
    	Encoder encoder = new Encoder();
    	try {
    		MultimediaInfo m = encoder.getInfo(fileVideo);
    		vedioLong = (int)m.getDuration()/1000;
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	doVideo = new video2Image(fileVideo);
    	initComponents();
    }

    private void initComponents() {
    	
    	//視窗設定
        menu = new JFrame("vedio");
        menu.setLocationByPlatform(true);
        menu.setSize(1200, 800);
        menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menu.setLayout(new GridLayout(2, 1));	
        
        //input面板排版設定
        inputPanel.setLayout(new GridLayout(5, 1));
             
        //視窗加入標題
        JLabel titleLabel = new JLabel("請選擇截圖時間及張數(2~10)", JLabel.CENTER);
        titleLabel.setFont(new java.awt.Font("Dialog", 1, 30));	//(字體，粗體，大小)
        inputPanel.add(titleLabel);      
        
        //start
        JPanel startPanel = new JPanel();
        
        //時分秒Label
        JLabel hourLabel = new JLabel("時");
        hourLabel.setFont(new java.awt.Font("Dialog", 1, 20));	//(字體，粗體，大小)
        JLabel minuteLabel = new JLabel("分");
        minuteLabel.setFont(new java.awt.Font("Dialog", 1, 20));	//(字體，粗體，大小)
        JLabel secondLabel = new JLabel("秒");
        secondLabel.setFont(new java.awt.Font("Dialog", 1, 20));	//(字體，粗體，大小)
        
        //開始時間Label
        JLabel startTimeLabel = new JLabel("開始時間", JLabel.LEFT);
        startTimeLabel.setFont(new java.awt.Font("Dialog", 1, 20));	//(字體，粗體，大小)
        startPanel.add(startTimeLabel);
        
        //開始時間輸入
        startHour = new JTextField(3);
        startHour.setDocument(new LimitedDocument(2,24,startHour));		//輸入長度及型態設定
        startHour.addFocusListener(new CustomFocusListener(startHour, "0"));	//默認輸入
        
        startMinute = new JTextField(3);
        startMinute.setDocument(new LimitedDocument(2,59,startMinute));
        startMinute.addFocusListener(new CustomFocusListener(startMinute, "0"));
        
        startSecond = new JTextField(3);
        startSecond.setDocument(new LimitedDocument(2,59, startSecond));
        startSecond.addFocusListener(new CustomFocusListener(startSecond, "1"));
        
        startPanel.add(startHour);
        startPanel.add(hourLabel);
        startPanel.add(startMinute);
        startPanel.add(minuteLabel);
        startPanel.add(startSecond);
        startPanel.add(secondLabel);
        
        inputPanel.add(startPanel);
        
        //end
        JPanel endPanel = new JPanel();
        
        //時分秒Label
        JLabel hourLabel1 = new JLabel("時");
        hourLabel1.setFont(new java.awt.Font("Dialog", 1, 20));	//(字體，粗體，大小)
        JLabel minuteLabel1 = new JLabel("分");
        minuteLabel1.setFont(new java.awt.Font("Dialog", 1, 20));	//(字體，粗體，大小)
        JLabel secondLabel1 = new JLabel("秒");
        secondLabel1.setFont(new java.awt.Font("Dialog", 1, 20));	//(字體，粗體，大小)
        
        //結束時間Label
        JLabel endTimeLabel = new JLabel("結束時間", JLabel.LEFT);
        endTimeLabel.setFont(new java.awt.Font("Dialog", 1, 20));	//(字體，粗體，大小)
        endPanel.add(endTimeLabel);
        
        //結束時間輸入
        endHour = new JTextField(3);
        endHour.setDocument(new LimitedDocument(2,24,endHour));
        endHour.addFocusListener(new CustomFocusListener(endHour, "0"));
        
        endMinute = new JTextField(3); 
        endMinute.setDocument(new LimitedDocument(2,59,endMinute));
        endMinute.addFocusListener(new CustomFocusListener(endMinute, "0"));
        
        endSecond = new JTextField(3);
        endSecond.setDocument(new LimitedDocument(2,59,endSecond));
        endSecond.addFocusListener(new CustomFocusListener(endSecond, "1"));
        
        endPanel.add(endHour);
        endPanel.add(hourLabel1);
        endPanel.add(endMinute);
        endPanel.add(minuteLabel1);
        endPanel.add(endSecond);
        endPanel.add(secondLabel1);
        
        inputPanel.add(endPanel);
        
        //pictureNum
        JPanel numPanel = new JPanel();
        
        //截圖張數Label
        JLabel imageNumLabel = new JLabel("截圖數量(2~10)", JLabel.LEFT);
        imageNumLabel.setFont(new java.awt.Font("Dialog", 1, 20));	//(字體，粗體，大小)
        numPanel.add(imageNumLabel);
        
        //截圖張數輸入
        num = new JTextField(3);   
        num.setDocument(new LimitedDocument(2,10,num));
        num.addFocusListener(new CustomFocusListener(num, "2"));
        numPanel.add(num);
        inputPanel.add(numPanel);
        
        //button面板
        JPanel buttonPanel = new JPanel();
        
        //截圖Button
        capture = new JButton("截圖");
        capture.setFont(new java.awt.Font("Dialog", 1, 30));
        buttonPanel.add(capture);
        
        //開始gifButton
        start = new JButton("製作gif");
        start.setFont(new java.awt.Font("Dialog", 1, 30));
        start.setEnabled(false);
        buttonPanel.add(start);
        
        //下載Button
        downLoad = new JButton("下載圖片");
        downLoad.setFont(new java.awt.Font("Dialog", 1, 30));
        downLoad.setEnabled(false);
        buttonPanel.add(downLoad);
        
        inputPanel.add(buttonPanel);
        //panel加入視窗
        menu.add(inputPanel);
        menu.setVisible(true);
        
        //capture button按下後執行
        capture.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){ 
                //開始編譯
            	picturePanel.removeAll();
            	checkTime(e);
            	viewPicture();
            	for(JButton j : chooseImage) {	//截圖後可選取要用的圖片
        	        j.addMouseListener(new MouseAdapter() { 
        	            public void mousePressed(MouseEvent e) { 
        	            	if(j.getBackground().getRGB() == -20561) 
        	            		j.setBackground(Color.WHITE);
        	            	else 
        	            		j.setBackground(Color.PINK);
        	            } 
        	        });
                }
            }
        });
        
        //downLoad button按下後執行
        downLoad.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){ 
            	//把picture資料夾的capture1~captureImageNum下載到C:\\capturePictures
            	boolean doDoenload = false;
            	for(int i = 1;i <= imageNum;i++) {
            		if(chooseImage[i - 1].getBackground().getRGB() == -20561) {
            			File tempImage = new File("picture\\capture" + i + ".jpg");
	            		try {
							new downloadCapturePictures(tempImage);
							doDoenload = true;
						} catch (IOException e1) {
							e1.printStackTrace();
						}
            		}
            	}
            	if(doDoenload == false)
            		JOptionPane.showMessageDialog(menu,"未選取任何要下載的圖片!");
            }
        });
        
        //start button按下後執行
        start.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){ 
            	int chooseNum = 0;
            	File image = new File("picture");
        		String imagePath = image.getAbsolutePath(); //圖片暫存位置
        		
            	for(int i = 1; i <= imageNum; i++) {	//先算選取多少圖片
	            	if(chooseImage[i - 1].getBackground().getRGB() == -20561) {
	            		chooseNum++;
	        		}
            	}
            	if(chooseNum < 2) {	//若選取不到兩張則打印錯誤訊息並return
            		JOptionPane.showMessageDialog(menu,"請選取至少兩張圖片!");
            		return;
            	}
            	File[] filePictures = new File[chooseNum];     //給定File大小
            	
            	chooseNum = 0;	//給予完大小再歸0
            	for(int i = 1; i <= imageNum; i++) {	//將有選取的圖片加入File
            		if(chooseImage[i - 1].getBackground().getRGB() == -20561) {
	            		filePictures[chooseNum] = new File(imagePath + "\\capture" + i + ".jpg");
	            		chooseNum++;
            		}
            	} 
            	menu.setVisible(false);
	            new imageGUI(filePictures);
            }
        });
        
    }
    
    private void viewPicture() {
    	int k = 1;
    	int width = 200, height = 160;
    	
    	File image = new File("picture");
		String imagePath = image.getAbsolutePath(); //圖片暫存位置
		chooseImage = new JButton[imageNum];
		
    	ArrayList<ImageIcon> imgIcon = new ArrayList<ImageIcon>();
    	
    	for(int i = 0; i < imageNum; i++) {
    		imgIcon.add(new ImageIcon(imagePath + "\\capture" + k + ".jpg"));
    		k++;
    	}
    	
    	k = 0;
    	for (ImageIcon picture : imgIcon) {
            Image pic = picture.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);   //設置圖片大小
            ImageIcon picAdjust = new ImageIcon(pic);
            JButton j = new JButton();
            j.setBorderPainted(false);
            j.setIcon(picAdjust);
            chooseImage[k] = j;
            k++;
        }
    	
    	for(JButton j : chooseImage)
    		picturePanel.add(j);
    	
    	menu.add(picturePanel);
    	
    	for(JButton j : chooseImage)
    		j.setSize(width, height);
    	
    	menu.setVisible(true);
    }

	//判斷input值，限制輸出的畫面長度及型態
    @SuppressWarnings("serial")
    class LimitedDocument extends PlainDocument {

		private int _maxLength  = -1;
		private int _maxValue = 0; // 最大值限制
        private String _allowCharAsString = "0123456789";
        private JTextField F;
     
        public LimitedDocument(int maxLength, int maxValue, JTextField F){
            super();
            this._maxLength = maxLength;
            this._maxValue = maxValue;
            this.F = F;
        }
     
        public void insertString(int offset, String str, AttributeSet attrSet) throws BadLocationException{
            if(str == null)
                return;
            if(_allowCharAsString != null && str.length() == 1) {
                if(_allowCharAsString.indexOf(str) == -1)
                    return;
            }
            char[] charVal = str.toCharArray();
            String strOldValue = getText(0, getLength());
            byte[] tmp = strOldValue.getBytes();
            if(_maxLength != -1 && (tmp.length + charVal.length > _maxLength)){
            	F.setText(Integer.toString(_maxValue));
                return;
            }
            
            String s = this.getText(0, this.getLength());
            s = s.substring(0, offset) + str + s.substring(offset, s.length());
            if (Double.parseDouble(s) > _maxValue) {
            	F.setText(Integer.toString(_maxValue));
            	return;
            }
            super.insertString(offset, str, attrSet);
        }
    }
    
    //判斷使用者有沒有input，未輸入給予預設值
    class CustomFocusListener implements FocusListener {
    	
    	private String hintText;
    	private JTextField textField;
    	public CustomFocusListener(JTextField jTextField,String hintText) {
    		this.textField = jTextField;
    		this.hintText = hintText;
    		jTextField.setText(hintText);  //默認提示內容
    		jTextField.setForeground(Color.GRAY);
    	}
     
    	@Override
    	public void focusGained(FocusEvent e) {
    		//使用者若有輸入，清空提示内容
    		String temp = textField.getText();
    		if(temp.equals(hintText)) {
    			textField.setText("");
    			textField.setForeground(Color.BLACK);
    		}	
    	}
     
    	@Override
    	public void focusLost(FocusEvent e) {	
    		//失去輸入內容時，顯示提示內容
    		String temp = textField.getText();
    		if(temp.equals("")) {
    			textField.setForeground(Color.GRAY);
    			textField.setText(hintText);
    		}
    		
    	}

    }
    
    private void checkTime(ActionEvent e2) {
    	
    	//將text字串轉為數字
    	startTime[0] = Integer.parseInt(startHour.getText());
    	startTime[1] = Integer.parseInt(startMinute.getText());
    	startTime[2] = Integer.parseInt(startSecond.getText());
    	endTime[0] = Integer.parseInt(endHour.getText());
    	endTime[1] = Integer.parseInt(endMinute.getText());
    	endTime[2] = Integer.parseInt(endSecond.getText());
    	imageNum = Integer.parseInt(num.getText());
    	
    	//判斷時間有沒有正確
    	int endTimeSecond = endTime[0] * 60 * 60 + endTime[1] * 60 + endTime[2];
    	int startTimeSecond = startTime[0] * 60 * 60 + startTime[1] * 60 + startTime[2];
    	int stayTime = endTimeSecond - startTimeSecond + 1;
    	if(stayTime <= 0 || endTimeSecond > vedioLong || startTimeSecond > vedioLong)
    		correctTime = false;
    	else 
    		correctTime = true;
    	
    	//判斷張數有沒有正確
    	if(imageNum > 10 || imageNum < 2)
    		correctNum = false;
    	else
    		correctNum = true;
    	
	    startPerformed(e2);
    }

    private void startPerformed(ActionEvent e3) {
        //確認訊息
        if(!correctTime & !correctNum){//時間&數量都錯誤>>錯誤訊息
           JOptionPane.showMessageDialog(menu,"請輸入正確時間及數量(2~10)!");
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
