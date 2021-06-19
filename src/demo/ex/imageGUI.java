package demo.ex;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import java.awt.Cursor; //鼠標
import demo.ex.vedioGUI.CustomFocusListener;
import demo.ex.vedioGUI.LimitedDocument;

public class imageGUI {
    private JFrame menu; //初始選單
    private File[] originalFiles;     //紀錄選取的圖片檔
    private JPanel markingJPanel;   //圖片預覽區
    private JPanel sizeJPanel;   //調整圖片尺寸區
    private JPanel lastTimeJPanel;   //調整圖片秒數區
    private JPanel isSaveJPanel;
    private JTextField height;			//圖片長
    private JTextField weight;			//圖片寬
    private JTextField lastSeconds;			//圖片秒數
    private JRadioButton save;    //儲存
    private JRadioButton notSave; //不存
    private ButtonGroup isSaveButtonGroup;
    private JButton start;            //製作GIF
    private JButton download;            //製作GIF
    public ArrayList<ImageIcon> pictures = new ArrayList<ImageIcon>();  //尺寸300x300的圖片陣列
    private JScrollPane showImage;  //圖片預覽區(捲軸)
    private int[] intSize = new int[2];
    private Double intLastTime;
    private boolean correctSize;  //是否選擇正確時間
    private boolean correctLastTime;   //是否選擇正確秒數(1~5)
    private boolean isSave;
    private image2GIF gif;

    private ArrayList<Integer> index;//圖片順序
    private ArrayList<JLabel> chosenLabel;//選取圖片

    
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

        //提示
        JLabel hintJLabel = new JLabel("↑請選擇圖片順序↑(預設為現顯示順序)", JLabel.CENTER);
        hintJLabel.setBounds(350,360,500,60);
        hintJLabel.setFont(new java.awt.Font("Dialog", 1, 20));
        menu.add(hintJLabel);

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
        JLabel lastTimeJLabel = new JLabel("設定圖片秒數(0.1~1):", JLabel.CENTER);
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


        //是否留下更改大小後之圖片
        isSaveJPanel = new JPanel();
        isSaveJPanel.setLayout(new FlowLayout());
        isSaveJPanel.setBounds(350,540,500,60);
        JLabel isSaveJLabel = new JLabel("是否留下更改大小後圖片檔：");
        isSaveJLabel.setBounds(0,0,200,50);
        isSaveJLabel.setFont(new java.awt.Font("Dialog", 1, 20));

        save = new JRadioButton("儲存");
        save.setFont(new java.awt.Font("Dialog", 1, 15));
        notSave = new JRadioButton("不儲存");
        notSave.setFont(new java.awt.Font("Dialog", 1, 15));
        notSave.setSelected(true);
        isSaveButtonGroup = new ButtonGroup();
        isSaveButtonGroup.add(save);
        isSaveButtonGroup.add(notSave);


        isSaveJPanel.add(isSaveJLabel);
        isSaveJPanel.add(save);
        isSaveJPanel.add(notSave);

        //開始製作GIF Button
        start = new JButton("製作");
        start.setFont(new java.awt.Font("Dialog", 1, 40));
        start.setBounds(350,600,200,100);
        
        //下載GIF Button
        download = new JButton("下載");
        download.setFont(new java.awt.Font("Dialog", 1, 40));
        download.setBounds(700,600,200,100);
        download.setEnabled(false);
        
        //panel和button加入視窗
        menu.add(sizeJPanel);
        menu.add(lastTimeJPanel);
        menu.add(isSaveJPanel);
        menu.add(start);
        menu.add(download);
        menu.setVisible(true);

        start.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //開始編譯
            	checkInput(e);
            }
        });
        
        download.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //開始下載
            	dowloadGIF();
            }
        });
    }
       
    private void showUploadedPictures(ArrayList<ImageIcon> pictures){    //顯示上傳圖片
    	markingJPanel = new JPanel();
        index = new ArrayList<>();
        ArrayList<JLabel> viewLabels = new ArrayList<>();
        chosenLabel = new ArrayList<>();
        for (ImageIcon picture : pictures) {
            Image pic = picture.getImage().getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);   //設置圖片default大小為(300,300)
            ImageIcon picAdjust = new ImageIcon(pic);
            JLabel label2 = new JLabel(picAdjust, SwingConstants.LEFT);
            label2.setToolTipText("This is " + picture);
            viewLabels.add(label2);
            markingJPanel.add(label2);
            label2.addMouseListener(new MouseListener(){
                public void mouseEntered(MouseEvent event){ //滑鼠移入改變鼠標
                    Cursor cu = new Cursor(Cursor.HAND_CURSOR);
                    menu.setCursor(cu);
                }
                public void mouseExited(MouseEvent e) {//滑鼠移出鼠標變回
                    Cursor cu = new Cursor(Cursor.DEFAULT_CURSOR);
                    menu.setCursor(cu);
                }
                public void mouseReleased(MouseEvent e){}
                public void mousePressed(MouseEvent e){}
                public void mouseClicked( MouseEvent event ){
                    if(chosenLabel.indexOf(event.getSource()) >= 0) { //取消選取
                        //取消文字.邊框
                        label2.setText(null);
                        label2.setBorder(BorderFactory.createEmptyBorder());
                        index.remove(index.indexOf(viewLabels.indexOf(label2)));
                        chosenLabel.remove(label2);
                        //重設text順序
                        for(JLabel label: chosenLabel) {
                            label.setText((chosenLabel.indexOf(label) + 1) + " ");
                        }
                    }
                    else {//選取
                        chosenLabel.add(label2);
                        index.add(viewLabels.indexOf(label2));
                        //加入順序標示.邊框
                        label2.setFont(new java.awt.Font("Dialog", 1, 20));
                        label2.setText(chosenLabel.size() + " ");
                        label2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, true));
                        label2.setIconTextGap(5);
                    }
                }
            }) ;
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

    private void checkInput(ActionEvent e2) {
    	
    	//將text字串轉為數字
    	intSize[0] = Integer.parseInt(height.getText());
    	intSize[1] = Integer.parseInt(weight.getText());
    	intLastTime = Double.parseDouble(lastSeconds.getText());

    	
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
        //是否保留圖片
        if(save.isSelected()){
            isSave = true;
        }
        else{
            isSave = false;
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
        else if(correctSize & correctLastTime){//選了正確的影音檔>>呼叫image2GIF中製作GIF的function
        	gif = new image2GIF();
        	gif.makeGIF(reloadFiles(), height.getText(), weight.getText(), lastSeconds.getText(), isSave);
        	download.setEnabled(true); //正確製作完gif則可下載
        	viewTestGIF(); 	 //GIF預覽
        }
    }
    private File[] reloadFiles(){
        if(index.size() == 0){return originalFiles;}
        File[] chosenFiles = new File[index.size()];
        for (int i : index) {
            chosenFiles[index.indexOf(i)] = new File(originalFiles[i].getPath());
        }
        return chosenFiles;
    }

    private void viewTestGIF() { //GIF預覽
    	JFrame viewGIF = new JFrame("Your GIF");
    	viewGIF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	viewGIF.setSize(intSize[1], intSize[0]);
    	
    	ImageIcon GIF = (new ImageIcon("test.gif"));
    	JLabel lGIF = new JLabel(GIF);
    	viewGIF.add(lGIF);
    	viewGIF.setVisible(true);
    	if(isSave) {//檢查是否正確儲存新大小圖片
    		if(gif.getSaveNewPictures()) {
        		JOptionPane.showMessageDialog(null,"已儲存新大小圖片至來源資料夾!");
        	}
        	else {
        		JOptionPane.showMessageDialog(null,"未能正確儲存新大小圖片至來源資料夾!");
        	}
    	}
    }
    
    private void dowloadGIF() { //GIF下載
    	try {
			new downloadChoosedObjects(new File("test.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.exit(0);
    }
}
