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
	
	//input startTime三格（時：分：秒）我有放參考圖
	//input endTime
	//input 圖片張數
	//開始截圖button執行video2Image(截圖暫存在gif\picture資料夾）
	//開始gif製作button執行imageGIF(未執行開始截圖button就按開始gif製作button，回傳你還沒截圖之類的錯誤)
	//maybe要顯示截圖結果預覽，但我不知道是要寫在這還是video2Image
    private JFrame menu; //初始選單
    private JPanel panel;   //圖片區
    private JLabel label;   
    private JButton start;            //控制開始編譯
    private Color DarkSeaGreen = new Color(143,188,143);  //深海洋綠
    private Color SlateGray = new Color(112,128,144);  //石板灰
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
        menu = new JFrame("vedio");	//視窗
        menu.setLocationByPlatform(true);
        menu.setSize(800, 600);
        menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menu.setLayout(null);			
                    
        label = new JLabel("請選擇截圖時間及張數(2~10)", JLabel.CENTER);
        label.setBounds(170,70,400,50);
        label.setFont(new java.awt.Font("Dialog", 1, 20));	//(字體，粗體，大小)
        menu.add(label);                     
       
        start = new JButton("開始");
        start.setFont(new java.awt.Font("Dialog", 1, 40));

        panel = new JPanel();	//面板
        panel.setBorder(javax.swing.BorderFactory.createTitledBorder("複數圖片檔"));

        start.setBounds(290,380,180,100);
                    
        menu.add(panel);
        menu.add(start);
        menu.setVisible(true);

        start.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){ 
                //開始編譯
                startPerformed(e);
            }
        });
    }


    private void startPerformed(java.awt.event.ActionEvent evt) {
        //確認訊息
       /* if(!correctTime & !correctNum){//時間&數量都錯誤>>錯誤訊息
           JOptionPane.showMessageDialog(menu,"請輸入正確時間及數量(2~10)!");
        }
        else if(correctTime & !correctNum){//數量選擇錯誤>>錯誤訊息
            JOptionPane.showMessageDialog(menu,"請輸入正確數量(2~10)!");
        }
        else if(!correctTime & correctNum){//時間選擇錯誤>>錯誤訊息
        	JOptionPane.showMessageDialog(menu,"請輸入正確時間!");
        }*/
        //else if(correctTime & correctNum){//選了正確的影音檔>>呼叫doVideo.captureScreen()
        	doVideo.captureScreen(0, 3, 0, 5);
        	menu.setVisible(false);
            new imageGUI();
        //}
    }

}
