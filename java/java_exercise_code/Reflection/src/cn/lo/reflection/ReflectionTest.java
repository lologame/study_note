package cn.lo.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

public class ReflectionTest {
	public static void main(String[] args) {
		String name ;
		if(args.length > 0)	name = args[0];
		else{
			Scanner in = new Scanner(System.in);
			System.out.println("Enter class name");
			name = in.next();
			in.close();
		}
		try{
			Class<?> c1 = Class.forName(name);
			String modifiers = Modifier.toString( c1.getModifiers());
			if(modifiers.length() > 0)	System.out.print(modifiers + " ");
			System.out.print("class" + name);
			Class<?> superC1 = c1.getSuperclass();
			if(superC1 != null)	System.out.print(" extends " + superC1.getName());
			System.out.print("\n" + "{" + "\n");
			printConstructors(c1);
			System.out.println();
			printMethods(c1);
			System.out.println();
			printFields(c1);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	private static void printConstructors(Class<?> c){
		Constructor<?>[] constructors = c.getDeclaredConstructors();
		for(Constructor<?> c1 : constructors){
			String name = c1.getName();
			String modifiers = Modifier.toString(c1.getModifiers());
			System.out.print("	");
			if(modifiers.length() > 0 )
				System.out.print(modifiers + " ");
			System.out.print(name + "(");
			Class<?>[] paramTypes = c1.getParameterTypes();
			for(int j = 0; j < paramTypes.length ; j++){
				if(j > 0)	System.out.print(",");
				System.out.print(paramTypes[j].getName());
			}
			System.out.println(");");
		}
	}
	
	private static void printMethods(Class<?> c){
		Method[] methods = c.getDeclaredMethods();
		for(Method m : methods){
			Class<?> retType = m.getReturnType();
			String name = m.getName();
			
			System.out.print("	");
			
			String modifiers = Modifier.toString(m.getModifiers());
			
			if(modifiers.length() > 0)	System.out.print(modifiers + " ");
			
			System.out.print(retType.getName() + " " + name + "(");
			
			Class<?>[] paramTypes = m.getParameterTypes();
			
			for(int j = 0; j < paramTypes.length ; j++){
				if(j > 0)	System.out.print(",");
				System.out.print(paramTypes[j].getName());
			}
			System.out.println(");");
			
		}
	}
	
	private static void printFields(Class<?> c){
		Field[] fields = c.getDeclaredFields();
		
		for(Field f : fields){
			String name = f.getName();
			String fType = f.getType().getName();
			String modifiers = Modifier.toString(f.getModifiers());
			System.out.print("	");
			if(modifiers.length() > 0 )	System.out.print(modifiers + " ");
			System.out.print(fType + " " + name + ";");
			System.out.println();
		}
	}
}
