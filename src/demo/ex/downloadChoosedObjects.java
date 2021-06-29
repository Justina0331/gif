package demo.ex;

import java.io.File;
import javax.swing.*;

public class downloadChoosedObjects {
	private static String DirMove = "C:\\yourDowloadings"; //目標資料夾
	private static String source; //來源照片路徑
	private static String srcPicturesName;
	
	public downloadChoosedObjects(File srcPictures) throws java.io.IOException {
		// 來源
		source = srcPictures.getPath();
		srcPicturesName = srcPictures.getName();
		File SetDir = new File(DirMove);
		if (!SetDir.exists()) { //目標資料夾不存在則建立新目標資料夾
			SetDir.mkdirs();
		}
		File f = new File(source);
		if (f.exists()) {
			check(f, SetDir);
		}
		else {
			System.out.println("找不到來源照片" + source);
		}
	}
	
	//確認來源照片及目標資料夾都存在
	public static void check(File f, File SetDir)
	throws java.io.IOException {
		if (!f.exists()) {
			System.out.println("找不到來源照片");
			return;
		}
		if (!SetDir.exists()) {
			System.out.println("找不到目標資料夾");
			return;
		}	
		copyTo(f, SetDir);
	}
	
	// 複製檔案
	public static void copyTo(File srcFile, File dstFile)
	throws java.io.IOException {
		File checkFile;
		if (!srcFile.isFile())
			throw new java.io.IOException("copy時來源錯誤!!!");
		// 複製目的地如果是資料夾,則把來源檔案名稱當成目的地的檔案名稱
		if (dstFile.isDirectory()) {
			checkFile = new File(dstFile.getAbsolutePath()
			+ File.separator + srcFile.getName());
			for(int i = 1;checkFile.exists();i++)//檔案已存在過則重新命名
			{
				//System.out.println("檔案已存在過!");
				String avaliableNewName = dstFile.getAbsolutePath() + File.separator ;
				for(int j=1;j<=i;j++) {
					avaliableNewName += "OldOne_";
				}
				avaliableNewName += srcFile.getName();
				boolean successfulRenamed = checkFile.renameTo(new File(avaliableNewName));  //已存在物件重新命名為"OldOne_原名"
				//debug用
				/*if(!successfulRenamed) {
					System.out.println("重新命名+存取失敗!\n"+avaliableNewName);
				}
				else {
					System.out.println("重新命名成功!");
				}*/
			}
			dstFile = checkFile;
		}
		
		// 複製
		java.io.BufferedInputStream in = new java.io.BufferedInputStream(
		new java.io.FileInputStream(srcFile));
		java.io.BufferedOutputStream out = new java.io.BufferedOutputStream(
		new java.io.FileOutputStream(dstFile));
		byte[] tmp = new byte[1024];
		while (in.read(tmp) != -1){
			out.write(tmp);
		}
		in.close();
		out.close();
		JOptionPane.showMessageDialog(null,"成功下載" + srcPicturesName + "至" + DirMove + "!");
	}
}