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
	//private String frequency;
	private String pictureNum;
	
	public video2Image(File fileVideo){
		File ffmpeg = new File("bin\\ffmpeg\\bin\\ffmpeg.exe");	//外掛
		this.ffmpegPath = ffmpeg.getAbsolutePath();
		this.veidoPath = fileVideo.getPath(); //影片路徑
		File image = new File("picture");
		this.imagePath = image.getAbsolutePath(); //圖片暫存位置
	}
	
	public String setFrequency(){	//根據開始和結束時間算截圖頻率
		return null;
		//TODO
	}
	
	public String setTime(int hours, int minutes, int seconds){	
		return Integer.toString(hours * 60 * 60 + minutes * 60 + seconds);
	}
	
	public void captureScreen(int startHours, int startMinutes, int startSeconds, int pictureNum) {
								// int endHours, int endMinutes, int endSeconds){	//setFrequency寫完才有結束時間,目前為預設頻率連續截圖
		
		this.startTime = setTime(startHours, startMinutes, startSeconds);
		this.pictureNum = Integer.toString(pictureNum);
		
		ArrayList<String> commands = new ArrayList<String>();

		commands.add(ffmpegPath);

		commands.add("-i");

		commands.add(veidoPath);
		
		commands.add("-f");
		
		commands.add("image2");

		commands.add("-ss");

		commands.add(startTime);//設置截取視頻開始時間

		commands.add("-frames");//切割長度

		commands.add(this.pictureNum);
		
		commands.add(imagePath + "\\%d.jpg");

		try {
			ProcessBuilder builder = new ProcessBuilder(commands);
			builder.start();
			//cmd執行結果(debug用)
			/*builder.redirectErrorStream(true);
			Process process = builder.start();

			readProcessOutput(process.getInputStream(), System.out);*/
			System.out.println("截取成功");
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
