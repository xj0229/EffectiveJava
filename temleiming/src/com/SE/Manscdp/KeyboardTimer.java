/**
 * 
 */
package com.SE.Manscdp;

import java.util.Timer;
import java.util.TimerTask;

import com.SE.SIPServer.ISipVideoControl;
import com.SE.SIPServer.VideoControl;

/**
 * @author Bryson Han
 *
 */
public class KeyboardTimer {
	
	private static RunTimer m_runTimer;
	
	private static Thread m_runThread;
	
	private ISipVideoControl m_PTZ = new VideoControl();
	
	
	//!!!!请曹工给m_content添加停止内容
	private String m_content;
	
	
	private class RunTimer implements Runnable {
		
		private int runTime;
		
		private Timer m_timer;
		
		TimerTask m_timerTask;
		
		private RunTimer (int time){
			this.runTime = time;
		}

		public void timerCancel (){
			m_timer.cancel();
			m_timer = null;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			this.m_timer = new Timer();
			m_content = "<?xml version=\"1.0\" ?>\n"
					+ "<Control>\n"
					+ "<CmdType>DeviceControl</CmdType>\n"
					+ "<SN>1</SN>\n"
					+ "<DeviceID>00151000000004000099</DeviceID>\n"
					+ "<PTZCmd>A50F0100000000B5</PTZCmd>\n"
					+ "</Control>";
			this.m_timerTask = new TimerTask(){
	            @Override
	            public void run() {
	            	m_PTZ.PTZControl(m_content);
	                System.out.println("到点啦！");
	                m_timer.cancel();
	            }
			};
			m_timer.schedule(m_timerTask, runTime);
		}
	}
	
	public KeyboardTimer(int time){
		this.m_runTimer = new RunTimer(time);
	}
	
	public void startTimer (){
		m_runThread = new Thread (this.m_runTimer);
		m_runThread.start();
	}
	
	@SuppressWarnings("deprecation")
	public void stopTimer(){
		if (m_runThread!=null){
			m_runTimer.timerCancel();
//			m_runThread.stop();
//			m_runThread = null;
			System.out.println("停止时钟");
		}
	}	
}
