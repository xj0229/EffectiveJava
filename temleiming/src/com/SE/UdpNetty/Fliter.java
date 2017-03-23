package com.SE.UdpNetty;

import java.util.ArrayList;
import java.util.List;

public class Fliter {
	private List<String> msg=new ArrayList<String>();
	public String msgflitration2(String content) {
		if (msg.size()!=0) {
			String b =content.substring(content.indexOf("a")-1,content.indexOf("a"));
			if (msg.size()==1) {
				String a = msg.get(0).substring(msg.get(0).indexOf("a")-1, msg.get(0).indexOf("a"));
				if (a.equals(b)) {
					msg.add(content);
				}
				else{
					msg.clear();
					msg.add(content);
					return msg.get(1);
				}
			}
			else if (msg.size()==2) {
				String a = msg.get(1).substring(msg.get(1).indexOf("a")-1, msg.get(1).indexOf("a"));
				if (a.equals(b)) {
					msg.add(content);
				}
				else{
					msg.clear();
					msg.add(content);
					return msg.get(1);
				}		
			}
			else if (msg.size()==3) {
				msg.clear();
				msg.add(content);	
				return msg.get(1);	
			}
			else if (msg.size()==0) {
				msg.add(content);
		}
		}		
		return msg.get(1);
		
	}
	public String msgflitration(String content) {
		// TODO Auto-generated method stub

		if (msg.size()!=0) {
		String b =content.substring(content.indexOf("a")-1,content.indexOf("a"));
		if (msg.size()==1) {
			String a = msg.get(0).substring(msg.get(0).indexOf("a")-1, msg.get(0).indexOf("a"));
			if (a.equals(b)) {
				msg.add(content);
			}
			else{
				msg.clear();
				msg.add(content);
				return msg.get(1);
			}
		}
		else if (msg.size()==2) {
			String a = msg.get(1).substring(msg.get(1).indexOf("a")-1, msg.get(1).indexOf("a"));
			if (a.equals(b)) {
				msg.add(content);
			}
			else{
				msg.clear();
				msg.add(content);
				return msg.get(1);
			}		
		}
		else if (msg.size()==3) {
			String a = msg.get(2).substring(msg.get(2).indexOf("a")-1, msg.get(2).indexOf("a"));
			if (a.equals(b)) {
				msg.add(content);
			}
			else{
				msg.clear();
				msg.add(content);
				return msg.get(1);
			}		
		}
		else if (msg.size()==4) {
			String a = msg.get(3).substring(msg.get(3).indexOf("a")-1, msg.get(3).indexOf("a"));
			if (a.equals(b)) {
				msg.add(content);
			}
			else{
				msg.clear();
				msg.add(content);
				return msg.get(1);
			}		
		}
		else if (msg.size()==5) {
			String a = msg.get(4).substring(msg.get(4).indexOf("a")-1, msg.get(4).indexOf("a"));
			if (a.equals(b)) {
				msg.add(content);
			}
			else{
				msg.clear();
				msg.add(content);
				return msg.get(1);
			}		
		}
		else if (msg.size()==6) {
			String a = msg.get(5).substring(msg.get(5).indexOf("a")-1, msg.get(5).indexOf("a"));
			if (a.equals(b)) {
				msg.add(content);
			}
			else{
				msg.clear();
				msg.add(content);
				return msg.get(1);
			}		
		}
		else if (msg.size()==7) {
			String a = msg.get(6).substring(msg.get(6).indexOf("a")-1, msg.get(6).indexOf("a"));
			if (a.equals(b)) {
				msg.add(content);
			}
			else{
				msg.clear();
				msg.add(content);
				return msg.get(1);
			}		
		}
		else if (msg.size()==8) {
			String a = msg.get(7).substring(msg.get(7).indexOf("a")-1, msg.get(7).indexOf("a"));
			if (a.equals(b)) {
				msg.add(content);
			}
			else{
				msg.clear();
				msg.add(content);
				return msg.get(1);
			}		
		}
		else if (msg.size()==9) {
			String a = msg.get(7).substring(msg.get(7).indexOf("a")-1, msg.get(7).indexOf("a"));
			if (a.equals(b)) {
				msg.add(content);
			}
			else{
				msg.clear();
				msg.add(content);
				return msg.get(1);
			}		
		}
		else if (msg.size()==10) {
		//String a = msg.get(2).substring(msg.get(2).indexOf("a")-1, msg.get(2).indexOf("a"));
			msg.clear();
			msg.add(content);	
			return msg.get(1);
		}
	}
	else if (msg.size()==0) {
			msg.add(content);
	}
	return msg.get(1);
		
	
	}

}
