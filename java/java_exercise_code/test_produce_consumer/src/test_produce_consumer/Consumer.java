package test_produce_consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Consumer implements Runnable {

	private BlockingQueue<Integer> q = new LinkedBlockingQueue<Integer>();
	
	public Consumer(BlockingQueue<Integer> q){
		this.q = q;
	}
	
	@Override
	public void run(){
		
		while(true){
			System.out.println("准备消费");
			
			q.poll();
			
			System.out.println("消费成功");
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
