package cn.lo.multithread;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class SerchingTask implements Runnable{

	private String keyword;
	private BlockingQueue<File> queue;
	
	
	public SerchingTask(String keyword, BlockingQueue<File> queue) {
		super();
		this.keyword = keyword;
		this.queue = queue;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		boolean done = false;
		
		while(!done){
			File file;
			try {
				file = queue.take();
				if(file == FileEnumrationTask.Dummy){
					done = true;
					queue.put(file);
				}
				else{
					serch(file);
				}
			} catch (InterruptedException | FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void serch(File file) throws FileNotFoundException{
		Scanner in = new Scanner(file);
		while(in.hasNextLine()){
			String line = in.nextLine();
			if(line.contains(keyword))	System.out.printf("%s:%s\n",file.getPath(),line);
		}
		in.close();
	}
	
}
