package com.xiejun.concurrenttest2;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class ForwardingSet<E> implements Set<E>{
	
	private final Set<E> s;
	
	public ForwardingSet(Set<E> s){
		this.s = s;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return s.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return s.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return s.contains(o);
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return s.iterator();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return s.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return s.toArray(a);
	}

	@Override
	public boolean add(E e) {
		// TODO Auto-generated method stub
		return s.add(e);
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return s.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return s.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return s.addAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return s.retainAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return s.removeAll(c);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		s.clear();
	}
	
}
