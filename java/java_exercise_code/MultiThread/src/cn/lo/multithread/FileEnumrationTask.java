package cn.lo.multithread;

import java.io.File;
import java.util.concurrent.BlockingQueue;

public class FileEnumrationTask implements Runnable{
	public static File Dummy = new File("");
	
	private BlockingQueue<File> queue;
	
	private File startingDirectory;

	public FileEnumrationTask(BlockingQueue<File> queue, File startingDirectory) {
		this.queue = queue;
		this.startingDirectory = startingDirectory;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			enumrate(startingDirectory);
			queue.put(Dummy);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void enumrate(File directory) throws InterruptedException{
		File[] files = directory.listFiles();
		
		for(File file : files){
			if(file.isDirectory()) enumrate(file);
			else	queue.put(file);
		}
	}
	
	
}