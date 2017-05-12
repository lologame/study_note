package test_produce_consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
	
	public static void main(String[] args) {
		BlockingQueue<Integer> q = new LinkedBlockingQueue<Integer>();
		
		Consumer consumer = new Consumer(q);
		
		Producer producer = new Producer(q);
		
		ExecutorService service = Executors.newCachedThreadPool();
		
		service.submit(producer);
		
		service.submit(consumer);
	}
}
