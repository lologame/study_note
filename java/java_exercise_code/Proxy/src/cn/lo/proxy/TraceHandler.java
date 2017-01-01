package cn.lo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


public class TraceHandler implements InvocationHandler{
	
	private Object target;
	
	
	
	public TraceHandler(Object target) {
		this.target = target;
	}



	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.print(target);
		System.out.print("." + method.getName() + "(" );
		if(args != null)
		{
			for(int i = 0; i < args.length ; i++)
			{
				if(i > 0)	System.out.print(",");
				System.out.print(args[i]);
			}
		}
		System.out.println(")");
		return method.invoke(target, args);
	}

}
