package com.SE.Manscdp;

public interface IMessage {

	public String parser(String msg,String devid);
	public String keyboardinfo(String mon,String cam);
	public String splitescrren(String mon,String SpliteScreen);
//	public String cancelalarm(String ID);
	public String cancelalarm();
}
