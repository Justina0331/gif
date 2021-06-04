package demo.ex;

import java.awt.*;
import java.io.File;    //檔案讀寫
import java.io.FileWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;



public class image2GIF extends JFrame
{
	private boolean saveNewPictures;  //是否正確儲存改變大小的圖片
    private ArrayList<File> newFiles = new ArrayList<File>(); //更改大小後路徑

    public void makeGIF(File[] files, String height, String width, String time, boolean isSave) { //圖片轉GIF
        resizeGraph(files, height, width);

        File ffmpeg = new File("bin\\ffmpeg\\bin\\ffmpeg.exe");
        ArrayList<String> commands = new ArrayList<String>();
        commands.add(ffmpeg.getAbsolutePath());    //指令路徑

        //圖片路徑
        String picturesPath = newFiles.get(0).getPath();
        picturesPath = picturesPath.substring(0, picturesPath.lastIndexOf("\\"));

        StringBuilder pics = new StringBuilder();
        for(File f : newFiles){
            pics.append( "file '" + f.getPath() + "'\r\n");
            pics.append("duration " + time + "\r\n");
        }

        File pathTxt = new File(picturesPath + "\\path.txt");   //將檔案寫入路徑文件
        try {
            pathTxt.createNewFile();
            FileWriter writer = new FileWriter(picturesPath + "\\path.txt");
            writer.write(pics.toString());
            writer.close();
            //System.out.println("Successfully wrote to the file.");
        }
        catch (IOException e){
            System.out.println("檔案建立失敗");
        }

        commands.add("-f");
        commands.add("concat");
        commands.add("-safe");
        commands.add("0");
        commands.add("-i");
        commands.add(picturesPath + "\\path.txt");
        System.out.println("Successfully.");

        /*commands.add("-loop");
        commands.add("1");*/

        commands.add("-y");			//若檔案存在, 覆蓋檔案
        commands.add("test.gif");

        System.out.println(commands);
        try {
            ProcessBuilder builder = new ProcessBuilder(commands);
            builder.redirectErrorStream(true);
            Process process = builder.start();
            //readProcessOutput(process.getInputStream(), System.out);	//cmd執行結果(debug用)
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //GIF製作完成，刪除路徑txt、圖片
        pathTxt.delete();
        if(!isSave){
            for(File f : newFiles){
                f.delete();
            }
            File upF = new File(newFiles.get(0).getParent());
            upF.delete();
        }
        commands.clear();

        //預覽播放
        File ffplay = new File("bin\\ffmpeg\\bin\\ffplay.exe");
        commands.add(ffplay.getAbsolutePath());
        //commands.add("-framedrop");
        commands.add("-window_title");
        //commands.add("執行兩秒就會當機的預覽播放");
        commands.add("test.gif");
        try {
            ProcessBuilder builder = new ProcessBuilder(commands);
            builder.redirectErrorStream(true);
            Process process = builder.start();
            //readProcessOutput(process.getInputStream(), System.out);	//cmd執行結果(debug用)
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resizeGraph(File[] files, String height, String width) { //改變圖片大小

        //新建資料夾
        String upFile = files[0].getPath().substring(0, files[0].getPath().lastIndexOf("\\"));
        File desFile = new File( upFile + "\\new");
        desFile.mkdir();

        for(File f: files) {
            String path = f.getPath();
            try {
                //讀取圖片
                File srcFile = new File(f.getPath());
                BufferedImage srcImg = ImageIO.read(srcFile);

                //改變圖片大小及轉成jpg檔
                srcImg.getScaledInstance(Integer.parseInt(width), Integer.parseInt(height), Image.SCALE_SMOOTH);
                BufferedImage outputImage = new BufferedImage(Integer.parseInt(width), Integer.parseInt(height), BufferedImage.TYPE_INT_RGB);
                outputImage.getGraphics().drawImage(srcImg, 0, 0, Integer.parseInt(width), Integer.parseInt(height), null);

                //寫入資料夾
                String fileName = upFile + "\\new\\" + path.substring(path.lastIndexOf("\\") + 1, path.lastIndexOf("."));
                newFiles.add(new File(fileName + ".jpg"));
                ImageIO.write(outputImage, "jpg", newFiles.get(newFiles.size() - 1));
                this.saveNewPictures = true;
            } catch (IOException e) {
                System.out.println("檔案讀取失敗");
                this.saveNewPictures = false;
            }
        }//end for
    }//end function resize
    
    public boolean getSaveNewPictures() {
    	return this.saveNewPictures;
    }
} // end class image2GIF
