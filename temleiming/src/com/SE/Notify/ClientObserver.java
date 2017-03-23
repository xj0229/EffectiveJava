/**
 * 
 */
package com.SE.Notify;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * @author 03010213
 *
 */
public class ClientObserver implements Observer {
	private WatchedObject m_sip;
	
	private WatchedObject m_keyBoard;
	
	private IAlarmUserFunction m_alarmInterface;
	
	private ICatalogueUserFunction m_catalogueInterface;
	
	public ClientObserver(WatchedObject sip, WatchedObject keyBoard){
		m_sip = sip;
		m_keyBoard = keyBoard;
		m_sip.addObserver(this);
		m_keyBoard.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		String flag = (String)arg;
		
		List<String> content;
		if (flag.equals("alarm")){
			content = m_sip.getContent();
			m_alarmInterface.alarmUserProcessor(content);
		}
		else if (flag.equals("catalogue")){
			content = m_sip.getContent();
			m_catalogueInterface.processCatalogue(content);
			
		}
	}
}
