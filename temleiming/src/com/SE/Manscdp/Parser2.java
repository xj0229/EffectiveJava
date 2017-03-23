package com.SE.Manscdp;

import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.LineListener;

import com.SE.SIPServer.ISipVideoControl;
import com.SE.SIPServer.VideoControl;
import com.SE.STClient.Common.Tb_IpcInfo;
import com.SE.STClient.Service.EquipmentDataService;

import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;

public class Parser2 {
	//static String msg;
	//static String msg = "0166:188\\a";  //待处理信息
	static String MSG;                    //处理结果
	static String MSG4="0";
	String position;               //第几个预设点
	int position2;
	String position3;              //第6字节  16进制
	String position4;              //第8个字节 16进制
	String camnum;                 //摄像头号
	static String monitornum;             //监视器号
	String[] MSG3=new String[3];		  //执行语句，摄像头号，监视器号
	private List<String> list=new ArrayList<String>();
	Tb_IpcInfo equipmentsInfoEntity;
	private Message bb = new Message();
	private ISipVideoControl cc = new VideoControl();
	String SpliteScreen;

	public void msghandle2(String msg){
		 if (msg.indexOf("Ma")!=-1) {//监视器号
			 monitornum=msg.substring(0,msg.indexOf("Ma"));
			 MSG3[2]=monitornum;
			 MSG4="1";
			 }
		 
		 else if (msg.indexOf("#a")!=-1) {
			 camnum=msg.substring(0,msg.indexOf("#a"));
			 cc.PTZControl(bb.keyboardinfo(monitornum, camnum));
			 
		 }
		 else if (msg.indexOf("Fa")!=-1) {//聚焦远

				if (msg.indexOf(":Fa")!=-1) {
				//	MSG3[0]="A50F014180000076";
					MSG3[1]=camnum;
					 cc.PTZControl( bb.parser("A50F014180000076","00151000000004000099" ));
				}
				else{
					SpliteScreen=msg.substring(0,msg.indexOf("Fa"));
					cc.PTZControl( bb.splitescrren(monitornum, SpliteScreen));
				}

		}
	}

	public void msghandle(String msg){

		msg=msg.substring(0,msg.indexOf("a")+1);
			
		
			 if (msg.indexOf("Da")!=-1) {//下

					MSG3[0]="A50F010480000039";
					MSG3[1]=camnum;
				
			}
			else if (msg.indexOf("Ua")!=-1) {//上
	
					MSG3[0]="A50F01088000003D";
					MSG3[1]=camnum;
				
			}
			else if (msg.indexOf("La")!=-1) {//左

					MSG3[0]="A50F010200800037";
					MSG3[1]=camnum;
				
			}
			else if (msg.indexOf("Ra")!=-1) {//右

					MSG3[0]="A50F010100800036";
					MSG3[1]=camnum;
				
			}
			else if (msg.indexOf("Wa")!=-1) {//放大

					MSG3[0]="A50F0110000008CD";
					MSG3[1]=camnum;

			}
			else if (msg.indexOf("Ta")!=-1) {//缩小

					MSG3[0]="A50F0120000008DD";
					MSG3[1]=camnum;
					
			}
			else if (msg.indexOf("Na")!=-1) {//聚焦近

				MSG3[0]="A50F014280000077";
				MSG3[1]=camnum;
		}

			else if (msg.indexOf("\\a")!=-1) {//调用预设点
				position = msg.substring(5,msg.indexOf("\\a"));
				position2 = Integer.parseInt(position);
				position3= Integer.toHexString(position2);
				if (position2<16) {
					position2=(311+position2)%256;
					position4= Integer.toHexString(position2);
					if (position2<16) {
						MSG3[0]="A50F0182000"+position3+"000"+position4;
						MSG3[1]=camnum;
					}
					else if (position2>=16) {
						MSG3[0]="A50F0182000"+position3+"00"+position4;
						MSG3[1]=camnum;
					}
				}
				else if (position2>=16)	{
					position2=(311+position2)%256;
					position4= Integer.toHexString(position2);
					if (position2<16) {
						MSG3[0]="A50F018200"+position3+"000"+position4;
						MSG3[1]=camnum;
					}
					else if (position2>=16) {
					MSG3[0]="A50F018200"+position3+"00"+position4;
					MSG3[1]=camnum;
					}

				}
		}
			else if (msg.indexOf("^a")!=-1) {//设置预设点
				position = msg.substring(5,msg.indexOf("^a"));
				position2 = Integer.parseInt(position);
				position3= Integer.toHexString(position2);
				if (position2<16) {
					MSG3[0]="A50F0181000"+position3+"0000";
		}
				else if (position2>=16) {
					MSG3[0]="A50F018100"+position3+"0000";
				}
		}
			else if (msg.indexOf("Sa")!=-1&&MSG4.equals("1")) {
				position = msg.substring(5,msg.indexOf("Sa"));
				position2 = Integer.parseInt(position);
				position3= Integer.toHexString(position2);
				if (position2<16) {
					position2=(317+position2)%256;
					position4= Integer.toHexString(position2);
					if (position2<16) {
						MSG="A50F0188000"+position3+"000"+position4;	
					}
					else if (position2>=16) {
						MSG="A50F0188000"+position3+"00"+position4;	
					}
				}
				else if (position2>=16)	{
					position2=(317+position2)%256;
					position4= Integer.toHexString(position2);
					if (position2<16) {
						MSG="A50F018800"+position3+"000"+position4;
					}
					else if (position2>=16) {
						MSG="A50F018800"+position3+"00"+position4;
					}
				}	
				 MSG4="2";
			}
			else if (msg.indexOf("_a")!=-1&&MSG4.equals("2")) {		
				MSG3[0]=MSG;
				MSG3[1]=camnum;
				} 
			else if (msg.indexOf("%a")!=-1) {
				cc.PTZControl(bb.cancelalarm());
			}
			else  {
				MSG3[0]=null;
				MSG3[1]=null;
			}
		 
			
			 /*equipmentsInfoEntity=new Tb_IpcInfo();
			  	EquipmentDataService equipmentDataService=new EquipmentDataService();
			  	equipmentsInfoEntity=equipmentDataService.GetAllIPCAndDomeCameraInfo();		
			  for (int i = 0; i < equipmentsInfoEntity.getDev_ID().size(); i++) {
				
				if (equipmentsInfoEntity.getDev_ID().get(i)==Integer.parseInt(cam)) {
						MSG3[1]=equipmentsInfoEntity.getSipID().toString();
						break;
				}	*/
			//	 cc.PTZControl( bb.parser(MSG3[0],MSG3[1] ));//"00151000000004000099"
			 cc.PTZControl( bb.parser(MSG3[0],"00151000000004000099" ));
			 System.out.println(MSG3[0]);
			 MSG3[0]=null;
		 }
	}	
	

	 
/*		if (list.size()==0) {
			list.add(MSG3[0]);
			 cc.PTZControl( bb.parser(MSG3[0],"00151000000004000099" ));
		}
		else if (list.size()==1) {
			list.add(MSG3[0]);
			 cc.PTZControl( bb.parser(MSG3[0],"00151000000004000099" ));
		}
		else if (list.size()==2) {
		//	String a = list.get(0).substring(6, 8);
			String b = list.get(1).substring(6, 8);
			if (list.get(0).equals(list.get(1))) {
				 cc.PTZControl( bb.parser(MSG3[0],"00151000000004000099" ));
				list.set(0, list.get(1));
				list.remove(1);
				//list.clear();
			}
			else{
				 cc.PTZControl( bb.parser(MSG3[0],"00151000000004000099" ));
				 
					 try {
						Thread.sleep(100);
						 if (b.equals("10")||b.equals("20")||b.equals("02")||b.equals("08")||b.equals("04")||b.equals("01")){
							 cc.PTZControl("A50F0100000000B5");
						 }
						 else if (MSG3[0].substring(6, 8).equals("42")||MSG3[0].substring(6, 8).equals("41")) {
								cc.PTZControl("A50F0140000000F5");
							}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
		}*/

