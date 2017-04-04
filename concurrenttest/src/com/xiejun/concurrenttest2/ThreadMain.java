package com.xiejun.concurrenttest2;

import java.util.HashSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Effective Java item67 P234 20170404
public class ThreadMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ObservableSet<Integer> set = new ObservableSet<>(new HashSet<>());
		
		set.addObserver(new SetObserver<Integer>(){

			@Override
			public void added(ObservableSet<Integer> set, Integer element) {
				// TODO Auto-generated method stub
				System.out.println(element);
				if(element == 23){
//****************************ConcurrentModificationException*******************************
					//set.removeObserver(this);
					
//****************************deadlock************************************************
//					ExecutorService executor = Executors.newSingleThreadExecutor();
//					
//					final SetObserver<Integer> observer = this;
//					
//					try{
//						executor.submit(new Runnable(){
//
//							@Override
//							public void run() {
//								// TODO Auto-generated method stub
//								set.removeObserver(observer);
//							}
//							
//						}).get();
//					}catch(ExecutionException | InterruptedException e){
//						throw new AssertionError(e.getCause());
//					}finally{
//						executor.shutdown();
//					}
				}
			}
			
		});
		
		for(int i = 0; i < 100; i++){
			set.add(i);
		}

	}

}
