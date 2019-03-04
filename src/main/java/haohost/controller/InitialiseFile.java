package haohost.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class InitialiseFile {
	//开关
	private static boolean isInitRealPath = false;
	// 项目所在的真实路径
	private static String projectRealPath = null;
	// 存放到公共路径下
	private static String commonRealPath = null;
	// 存放到私有路径下
	private static String privateRealPath = null; 
	
	final public static void Intiialise(String path) {
		if(!isInitRealPath) {
			isInitRealPath=true;
			projectRealPath=path;
			commonRealPath=path+"static/img/updata";
			privateRealPath=path+"WEB-INF/updata";
		}
	}
	
	
	/**
	 *  获取文件输入流
	 *  如果f为null,或出现异常，直接返回null
	 * @param f  File f
	 * @return FileInputStream
	 */
	public FileInputStream getFileInputStream(File f) {
		if(null==f) {
			return null;
		}
		FileInputStream is=null;
		try {
			is = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			return null;
		}
		return is;
	}
	
	/**
	 * 采用缓冲方式进行输出文件流
	 * @param os OutputStream
	 * @param buffer 缓冲。为null时，默认会new一个大小为1024的缓冲池
	 * @param in InputStream输入流
	 * @throws IOException 异常抛出
	 */
	public void outputStream(OutputStream os,byte[] buffer,InputStream in) throws IOException {
		if (null == buffer) {
			buffer = new byte[1024];
		}
		int len;
		while((len=in.read(buffer))!=-1) {
			os.write(buffer,0,len);
		}
		os.flush();
	}
	/**
	 * 采用一次性方式进行输出文件流
	 * @param os OutputStream
	 * @param in InputStream输入流
	 * @throws IOException 异常抛出
	 */
	public void outputStream(OutputStream os,InputStream in) throws IOException {
		byte[] b = new byte[in.available()];
		in.read(b);
		os.write(b);
		os.flush();
	}
	
	/**
	 * 一次性写入文件
	 * 
	 * @param file
	 *            文件File对象
	 * @param b
	 *            字节流
	 * @return boolean
	 */
	public boolean saveFile(File file, byte[] b) {
		FileOutputStream fs = null;
		try {
			fs = new FileOutputStream(file);
			fs.write(b);// 一次性写入文件
			fs.flush();
			return true;
		} catch (IOException e) {
			return false;// 保存文件失败
		} finally {
			if (null != fs) {
				try {
					fs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 使用缓冲的方式
	 * <p>
	 * 方法内的程序不会对输入流（in）进行关闭。
	 * 
	 * @param in
	 *            输入流
	 * @param buffer
	 *            缓冲大小（如果为null,侧方法内会进行自动附值处理，默认1024字节）
	 * @param file
	 *            存放文件File对象
	 * @return boolean
	 */
	public boolean saveFile(InputStream in, byte[] buffer, File file) {
		if (null == buffer) {
			buffer = new byte[1024];
		}
		FileOutputStream fs = null;
		try {
			fs = new FileOutputStream(file);
			int len;
			while ((len = in.read(buffer)) != -1) {
				fs.write(buffer, 0, len);// 一次性写入文件
			}
			fs.flush();
			System.out.println(file.getAbsolutePath());
			return true;
		} catch (IOException e) {
			return false;// 保存文件失败
		} finally {
			if (null != fs) {
				try {
					fs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
