package utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	private static String imagesPath;
	private static String sharedPath;
	private static String webImgPath;
	private static String deletedDirectory;
	private static final String FILE_PATH = "static.properties";
	static {
		load(FILE_PATH);
	}

	private static String getOSName() {
		Properties os = System.getProperties();
		return os.getProperty("os.name"); // 操作系统名称
	}

	public static void makeFilePath(String path) {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	public static void load(String filePath) {
		InputStream in = null;
		try {
			Properties prop = new Properties();
			in = PropertiesUtil.class.getResourceAsStream(filePath);
			prop.load(in);
			String basePath = prop.getProperty("basePath") + File.separatorChar;
			if (getOSName().startsWith("Windows")) {
				basePath = prop.getProperty("basePath_windows");
			} else {
				basePath = prop.getProperty("basePath_linux");
			}
			imagesPath = prop.getProperty("images") + File.separatorChar;
			sharedPath = basePath + File.separatorChar
					+ prop.getProperty("sharedPath") + File.separatorChar;
			webImgPath = prop.getProperty("webImgPath") + File.separatorChar;
			makeFilePath(imagesPath);
			makeFilePath(sharedPath);
			makeFilePath(webImgPath);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String getImagesPath() {
		return imagesPath;
	}

	public static String getSharedPath() {
		return sharedPath;
	}

	public static String getWebImgPath() {
		return webImgPath;
	}

	public static String getDeletedDirectory() {
		return deletedDirectory;
	}

	public static void main(String[] args) {
		load(FILE_PATH);
		System.out.println(sharedPath);
	}
}
