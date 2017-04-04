package com.xiejun.concurrenttest2;

public interface SetObserver<E> {
	void added(ObservableSet<E> set, E element);

}
