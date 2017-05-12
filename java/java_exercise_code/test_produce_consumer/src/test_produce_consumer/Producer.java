package test_produce_consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Producer implements Runnable{
	
	private BlockingQueue<Integer> q = new LinkedBlockingQueue<Integer>();
	
	public Producer(BlockingQueue<Integer> q){
		this.q = q;
	}

	@Override
	public void run() {
		while(true){
			System.out.println("׼������");
			
			q.add(1);
			
			System.out.println("�������");
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		// TODO Auto-generated method stub
				
	}

}
