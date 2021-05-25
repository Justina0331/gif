package demo.ex;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import demo.ex.vedioGUI.CustomFocusListener;
import demo.ex.vedioGUI.LimitedDocument;

public class imageGUI {
    private JFrame menu; //初始選單
    private File[] originalFiles;     //紀錄選取的圖片檔
    private JPanel markingJPanel;   //圖片預覽區
    private JPanel sizeJPanel;   //調整圖片尺寸區
    private JPanel lastTimeJPanel;   //調整圖片秒數區
    private JPanel repeatTimesJPanel;   //調整圖片重複次數區
    private JPanel inputJPanel;
    private JPanel totalJPanel;   
    private JTextField height;			//圖片長
    private JTextField weight;			//圖片寬
    private JTextField lastSeconds;			//圖片秒數
    private JTextField repeatTimes;			//重複次數
    private JButton start;            //製作GIF
    public ArrayList<ImageIcon> pictures = new ArrayList<ImageIcon>();  //尺寸300x300的圖片陣列
    private JScrollPane showImage;  //圖片預覽區(捲軸)
    private int[] intSize = new int[2];
    private Double intLastTime;
    private int intRepeatTimes;
    private boolean correctSize;  //是否選擇正確時間
    private boolean correctLastTime;   //是否選擇正確秒數(1~5)
    private boolean correctRepeatTimes;   //是否選擇正確重複次數(1~5)
    
    public imageGUI(File[] files) {
        initComponents(files);
    }
    
    private void setOriginalFiles(File[] files) {
    	this.originalFiles = files;
    }

    private void initComponents(File[] files) {
    	
    	//視窗設定
        menu = new JFrame("調整圖片");	
        menu.setLocationByPlatform(true);
        menu.setSize(1200, 800);
        menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menu.setLayout(null);
        
        //default圖片預覽
    	setOriginalFiles(files);
        for (File file: this.originalFiles) {  //新增所有圖片到array
            pictures.add(new ImageIcon(file.getPath()));
            //System.out.print(file.getPath());
        }
        showUploadedPictures(pictures);
        
        //圖片尺寸Label
        sizeJPanel = new JPanel();
        sizeJPanel.setBounds(350,420,500,60);
        JLabel sizeJLabel = new JLabel("設定圖片大小(100~900):", JLabel.CENTER);
        JLabel sizeSymbol = new JLabel("x");
        sizeSymbol.setFont(new java.awt.Font("Dialog", 1, 20));	//(字體，粗體，大小)
        sizeJLabel.setBounds(0,0,200,50);
        sizeJLabel.setFont(new java.awt.Font("Dialog", 1, 20));	
        sizeJPanel.add(sizeJLabel);
        
        //圖片尺寸輸入
        height = new JTextField(5);
        height.setDocument(new LimitedDocument(100,900.0,height));		//輸入長度及型態設定
        height.addFocusListener(new CustomFocusListener(height, "300"));	//默認輸入
        
        weight = new JTextField(5);
        weight.setDocument(new LimitedDocument(100,900.0,weight));
        weight.addFocusListener(new CustomFocusListener(weight, "300"));

        sizeJPanel.add(height);
        sizeJPanel.add(sizeSymbol);
        sizeJPanel.add(weight);
        
        //圖片秒數Label
        lastTimeJPanel = new JPanel();
        lastTimeJPanel.setBounds(350,480,500,60);
        JLabel lastTimeJLabel = new JLabel("設定圖片秒數(1~5):", JLabel.CENTER);
        JLabel secondSymbol = new JLabel("秒/張");
        secondSymbol.setFont(new java.awt.Font("Dialog", 1, 20));	//(字體，粗體，大小)
        lastTimeJLabel.setBounds(0,0,200,50);
        lastTimeJLabel.setFont(new java.awt.Font("Dialog", 1, 20));	
        lastTimeJPanel.add(lastTimeJLabel);
        
        //圖片秒數輸入
        lastSeconds = new JTextField(3);
        lastSeconds.setDocument(new LimitedDocument(3,1.0,lastSeconds));		//輸入長度及型態設定
        lastSeconds.addFocusListener(new CustomFocusListener(lastSeconds, "0.1"));	//默認輸入

        lastTimeJPanel.add(lastSeconds);
        lastTimeJPanel.add(secondSymbol);

        //圖片重複次數Label
        repeatTimesJPanel = new JPanel();
        repeatTimesJPanel.setBounds(350,540,500,60);
        JLabel repeatTimesJLabel = new JLabel("設定圖片重複次數(1~5):", JLabel.CENTER);
        JLabel repeatTimesSymbol = new JLabel("次");
        repeatTimesSymbol.setFont(new java.awt.Font("Dialog", 1, 20));	//(字體，粗體，大小)
        repeatTimesJLabel.setBounds(0,0,200,50);
        repeatTimesJLabel.setFont(new java.awt.Font("Dialog", 1, 20));	
        repeatTimesJPanel.add(repeatTimesJLabel);
        
        //圖片秒數輸入
        repeatTimes = new JTextField(3);
        repeatTimes.setDocument(new LimitedDocument(2,5.0,repeatTimes));		//輸入長度及型態設定
        repeatTimes.addFocusListener(new CustomFocusListener(repeatTimes, "1"));	//默認輸入

        repeatTimesJPanel.add(repeatTimes);
        repeatTimesJPanel.add(repeatTimesSymbol);
        
        //開始製作GIF Button
        start = new JButton("完成製作");
        start.setFont(new java.awt.Font("Dialog", 1, 40));
        start.setBounds(500,600,200,100);
        
        //panel和button加入視窗
        menu.add(sizeJPanel);
        menu.add(lastTimeJPanel);
        menu.add(repeatTimesJPanel);
        menu.add(start);
        menu.setVisible(true);

        start.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //開始編譯
            	checkInput(e);
            }
        });
    }
       
    private void showUploadedPictures(ArrayList<ImageIcon> pictures){    //顯示上傳圖片
    	markingJPanel = new JPanel();
        for (ImageIcon picture : pictures) {
            Image pic = picture.getImage().getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);   //設置圖片default大小為(300,300)
            ImageIcon picAdjust = new ImageIcon(pic);
            JLabel label2 = new JLabel(picAdjust, SwingConstants.LEFT);
            label2.setToolTipText("This is " + picture);
            markingJPanel.add(label2);
            //輪流彈出圖片
            //JOptionPane.showInternalMessageDialog(null, null, "preview picture", JOptionPane.PLAIN_MESSAGE, picAdjust);
        }
        showImage=new JScrollPane(markingJPanel);  //設定捲軸
        //showImage.setLayout(null);
        showImage.setPreferredSize(new Dimension(900, 330));
        showImage.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        showImage.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        showImage.setVisible(true);
        JPanel panel = new JPanel();
        panel.add (showImage);
        panel.setBounds(150, 10, 900, 330);
        panel.setVisible(true);
        menu.add(panel);
    }
    
    //判斷input值，限制輸出的畫面長度及型態
    @SuppressWarnings("serial")
    class LimitedDocument extends PlainDocument {

		private int _maxLength  = -1;
		private double _maxValue = 0; // 最大值限制
        private String _allowCharAsString = "0123456789.";
        private JTextField F;
     
        public LimitedDocument(int maxLength, Double maxValue, JTextField F){
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
            	F.setText(Double.toString(_maxValue));
                return;
            }
            
            String s = this.getText(0, this.getLength());
            s = s.substring(0, offset) + str + s.substring(offset, s.length());
            if (Double.parseDouble(s) > _maxValue) {
            	F.setText(Double.toString(_maxValue));
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
    private void checkInput(ActionEvent e2) {
    	
    	//將text字串轉為數字
    	intSize[0] = Integer.parseInt(height.getText());
    	intSize[1] = Integer.parseInt(weight.getText());
    	intLastTime = Double.parseDouble(lastSeconds.getText());
    	intRepeatTimes = Integer.parseInt(repeatTimes.getText());

    	
    	//判斷尺寸有沒有正確
    	if(intSize[0] >= 100 & intSize[0] <= 900 & intSize[1] >= 100 & intSize[1] <= 900) {
    		correctSize = true;
    	}
    	else {
    		correctSize = false;
    	}
    	
    	//判斷秒數有沒有正確
    	if(intLastTime >= 0.1 & intLastTime <= 1) {
    		correctLastTime = true;
    	}
    	else {
    		correctLastTime = false;
    	}
    	
    	//判斷重複次數有沒有正確
    	if(intRepeatTimes >= 1 & intRepeatTimes <= 5) {
    		correctRepeatTimes = true;
    	}
    	else {
    		correctRepeatTimes = false;
    	}
    	
	    startPerformed(e2);
    }

    private void startPerformed(ActionEvent e3) {
        //確認訊息
        if(!correctSize){//尺寸錯誤>>錯誤訊息
           JOptionPane.showMessageDialog(menu,"請輸入正確圖片尺寸(100~900)!");
        }
        else if(!correctLastTime){//秒數錯誤>>錯誤訊息
            JOptionPane.showMessageDialog(menu,"請輸入正確數量(0.1~1)!");
        }
        else if(!correctRepeatTimes){//次數錯誤>>錯誤訊息
        	JOptionPane.showMessageDialog(menu,"請輸入正確重複次數(1~5)!");
        }
        else if(correctSize & correctLastTime & correctRepeatTimes){//選了正確的影音檔>>呼叫image2GIF中製作GIF的function
        	//TODO
        	
        }
    }
}
