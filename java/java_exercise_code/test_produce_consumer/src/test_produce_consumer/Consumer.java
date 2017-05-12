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
			System.out.println("׼������");
			
			q.poll();
			
			System.out.println("���ѳɹ�");
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
