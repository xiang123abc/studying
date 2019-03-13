package cn.ecut;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Demo {
	private static String path  = "F:\\项目\\erp_parent";
	public static void main(String[] args) throws Exception {
		//FileInputStream filein = new FileInputStream(new File(path));
		test(new File(path));
	}
	public static void test2(){
		
	}
	public static void test(File file) throws Exception{
		//File file = new File(path);
		if(file.exists() && file.isDirectory() ){
			File[] files = file.listFiles();
			for(File f:files){
				test(f);
				//Encoding.getFileEncode(f.getAbsolutePath())
			}
		}else if(file.exists() && file.isFile() && (file.getName().endsWith(".java") || file.getName().endsWith(".html"))){
			String charset = Encoding.getFileEncode(file.getAbsolutePath());
			System.out.println(charset+"-->"+file.getAbsolutePath());
		}
		
	}
}
