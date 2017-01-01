package cn.lo.multithread;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class MatchCounter implements Callable<Integer>{

	private File directory;
	private String keyword;
	
	
	public MatchCounter(File directory, String keyword) {
		super();
		this.directory = directory;
		this.keyword = keyword;
	}


	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		
		int count = 0;
		
		List<Future<Integer>> res = new ArrayList<>();
		
		File[] files = directory.listFiles();
		
		for(File file : files){
			if(file.isDirectory()){
				MatchCounter counter = new MatchCounter(file, keyword);
				FutureTask<Integer> task = new FutureTask<Integer>(counter);
				res.add(task);
				Thread t = new Thread(task);
				t.start();
			}
			else
			{
				if(serch(file))	count ++;
			}
		}
		
		for(Future<Integer> re : res){
			count += re.get();
		}
		
		return count;
	}
	
	private boolean serch(File file) throws FileNotFoundException{
		Scanner in = new Scanner(file);
		
		while(in.hasNextLine()){
			String line = in.nextLine();
			if(line.contains(keyword)) {
				in.close();
				return true;
			}
		}
		in.close();
		return false;
	}

}
