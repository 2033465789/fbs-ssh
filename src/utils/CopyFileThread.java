package utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class CopyFileThread extends Thread {

	private File srcFile;
	private File destFile;

	public CopyFileThread(File srcFile, File desFile) {
		this.srcFile = srcFile;
		this.destFile = desFile;
	}

	@Override
	public void run() {
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
