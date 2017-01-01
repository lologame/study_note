package cn.lo.serial;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerialCloneable implements Cloneable,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override 
	public Object clone()  {
		// TODO Auto-generated method stub
		ByteArrayOutputStream bOut = new ByteArrayOutputStream();
		
		try {
			ObjectOutputStream out = new ObjectOutputStream(bOut);
			out.writeObject(this);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		ByteArrayInputStream bin = new ByteArrayInputStream(bOut.toByteArray());
		try {
			ObjectInputStream in = new ObjectInputStream(bin);
			Object res = in.readObject();
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	
	}

}
