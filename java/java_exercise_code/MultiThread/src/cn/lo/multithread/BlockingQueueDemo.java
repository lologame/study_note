package cn.lo.multithread;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {
	public static void main(String[] args) {
		System.out.print("Enter a basic path:");
		Scanner in = new Scanner(System.in);
		File directory = new File(in.nextLine());
		System.out.println("Enter keyword:");
		String keyword = in.nextLine();
		in.close();
		
		BlockingQueue<File> queue = new ArrayBlockingQueue<File>(100);
		
		FileEnumrationTask fileEnumrationTask = new FileEnumrationTask(queue, directory);
		
		new Thread(fileEnumrationTask).start();
		
		for(int i = 1 ; i < 10 ; i ++){
			new Thread(new SerchingTask(keyword, queue)).start();
		}
	}
}
