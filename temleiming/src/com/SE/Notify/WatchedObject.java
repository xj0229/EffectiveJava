/**
 * 
 */
package com.SE.Notify;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import java.util.Observable;
/**
 * @author 03010213
 *
 */
public class WatchedObject extends Observable{
	//Observing trigger
		private boolean m_changeState = false;
		//Observer list
		List<Observer> m_observers = new ArrayList<Observer>();
		//Object for message content
		List<String> m_messageContent =  new ArrayList<String>();
		
		/*
		 * Add an observer of sip to list
		 */
		public void addObserver(Observer observer) {  
		    if (observer == null) {  
		        throw new NullPointerException();  
		    }  
		    synchronized (this) {  
		        if (!m_observers.contains(observer))  
		            m_observers.add(observer);  
		    }  
		}
		
		/*
		 * Delete an observer of sip from list
		 */
		public synchronized void deleteObserver(Observer observer) {  
		    m_observers.remove(observer);  
		} 
		
		/*
		 * Count observers of sip in the list
		 */
		public int countObservers() {  
		    return m_observers.size();  
		}
		
		/*
		 * Reset the change state
		 */
		protected void clearChanged() {   
			m_changeState = false;  
			}
		
		/*
		 * Trigger the observing operation
		 */
		public void setChanged(String flag) {   
		    m_changeState = true;
		    
		    notifyObservers(flag);
		} 
		
		/*
		 * Return current change state
		 */
		public boolean getChangeState() {   
		    return m_changeState;  
		}
		
		public void setContent(List<String> content)
		{
			m_messageContent = content;
		}
		
		public List<String> getContent()
		{
			return m_messageContent;
		}
		
		public void clearContent()
		{
			m_messageContent = null;
		}
		
		public void notifyObservers(Object flag) {   
		    int size = 0;   
		    Observer[] arrays = null;   
		    synchronized (this) {   
		        if (getChangeState()) {   
		            clearChanged();   
		            size = m_observers.size();  
		            arrays = new Observer[size];   
		            m_observers.toArray(arrays);   
		        }   
		    }   
		    if (arrays != null) {   
		        for (Observer observer : arrays) {   
		            observer.update(this, flag);
		        }   
		    }  
		}  
}
