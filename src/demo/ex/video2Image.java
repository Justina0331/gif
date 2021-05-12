package demo.ex;

/*import java.io.BufferedReader;	//debug用到的套件
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.Charset;*/

import java.io.File;
import java.util.ArrayList;


public class video2Image {
	private String ffmpegPath;
	private String veidoPath;
	private String imagePath;
	private String startTime;
	private String frequency;
	private String pictureNum;
	
	public video2Image(File fileVideo){
		File ffmpeg = new File("bin\\ffmpeg\\bin\\ffmpeg.exe");	//外掛
		this.ffmpegPath = ffmpeg.getAbsolutePath();
		this.veidoPath = fileVideo.getPath(); //影片路徑
		File image = new File("picture");
		this.imagePath = image.getAbsolutePath(); //圖片暫存位置
	}
	
	public String setFrequency(int startHours, int startMinutes, int startSeconds,
							   int endHours, int endMinutes, int endSeconds, int num){	//根據開始和結束時間算截圖頻率
		double stayTime = (endHours * 60 * 60 + endMinutes * 60 + endSeconds) - (startHours * 60 * 60 + startMinutes * 60 + startSeconds) + 1;
		return Double.toString(num/stayTime);
	}
	
	public String setTime(int hours, int minutes, int seconds){	
		return Integer.toString(hours * 60 * 60 + minutes * 60 + seconds);
	}
	
	public void captureScreen(int startHours, int startMinutes, int startSeconds,
							  int endHours, int endMinutes, int endSeconds, int pictureNum){	//擷取頭跟尾還需優化校正
		
		this.startTime = setTime(startHours, startMinutes, startSeconds);
		this.frequency = setFrequency(startHours, startMinutes, startSeconds,
									endHours, endMinutes, endSeconds, pictureNum);

		this.pictureNum = Integer.toString(pictureNum);
		
		ArrayList<String> commands = new ArrayList<String>();

		commands.add(ffmpegPath); 	//指令路徑

		commands.add("-i");			//影片路徑

		commands.add(veidoPath);
		
		commands.add("-f");			//型態
		
		commands.add("image2");

		commands.add("-ss");		//設置截取視頻開始時間

		commands.add(startTime);
		
		commands.add("-vf");		//幀數
		
		commands.add("fps=" + frequency);

		commands.add("-frames");	//擷取張數

		commands.add(this.pictureNum);
		
		commands.add("-y");			//覆蓋圖片
		
		commands.add(imagePath + "\\capture%d.jpg");

		try {
			ProcessBuilder builder = new ProcessBuilder(commands);
			builder.redirectErrorStream(true);
			Process process = builder.start();
			//readProcessOutput(process.getInputStream(), System.out);	//cmd執行結果(debug用)
			process.waitFor();	
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//cmd執行結果(debug用)
	/*private static void readProcessOutput(InputStream inputStream, PrintStream out) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("GBK")));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			System.out.println("-end");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}*/

}
