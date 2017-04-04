package com.xiejun.concurrenttest1;

import java.util.concurrent.TimeUnit;

//Effective Java item 66 P230 20170404
public class ThreadMain {
	
	private static volatile boolean stopRequested = false;
	
	public static void main(String[] args) throws InterruptedException{
		
		Thread backgroundThread = new Thread(new Runnable(){

			public void run() {
				// TODO Auto-generated method stub
				int i = 0;
				while(!stopRequested){
					i++;
					System.out.println(i);
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		});
		
		backgroundThread.start();
		
		TimeUnit.SECONDS.sleep(10);
		
		stopRequested = true;
		
	}

}
