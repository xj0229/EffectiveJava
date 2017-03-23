/**
 * 
 */
package com.SE.Decode;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * @author 03010213
 *
 */
public class DllTest {

	
	public interface DllTest1 extends Library{
		DllTest1 INSTANCE=(DllTest1)Native.loadLibrary("DecoderLib",DllTest.class);
		public int  VSK_Play_Stop(int port);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int yy=0;
		int xx=DllTest1.INSTANCE.VSK_Play_Stop(80);
		System.out.println(xx);

	}

}
