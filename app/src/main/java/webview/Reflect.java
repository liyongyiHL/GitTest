package webview;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.util.Log;

/**
 * A reflection utility class.
 * 
 */

public class Reflect {
	private Object object;

	/**
	 * Constructs this object 
	 * 
	 * @param object the object to reflect on
	 */
	
	public Reflect(Object object) {
		if (object == null)
			throw new IllegalArgumentException("Object can not be null.");
		this.object = object;
	}

	/**
	 * Get a field from the object 
	 * 
	 * @param name the name of the field
	 * 
	 * @return a field reference
	 */
	
	public FieldRf field(String name) {
		return new FieldRf(object, name);
	}
	
	/**
	 * Get a method from the object 
	 * 
	 * @param name the name of the method
	 * 
	 * @return a method reference
	 */
	public MethodRf method(String name, Class<?>... args) {
		return new MethodRf(object, name, args);
	}

	/**
	 * A field reference.  
	 */
	public class FieldRf {
		private Class<?> clazz;
		private Object object;
		private String name;

		/**
		 * Constructs this object 
		 * 
		 * @param object the object to reflect on
		 * @param name the name of the field
		 */
		
		public FieldRf(Object object, String name) {
			this.object = object;
			this.name = name;
		}

		/**
		 * Constructs this object 
		 * 
		 * @param outclazz the output type
		 *
		 * @return <T> T
		 */
		
		public <T> T out(Class<T> outclazz) {
			Field field = getField();
			Object obj = getValue(field);
			return outclazz.cast(obj);
		}

		/**
		 * Set a value to a field 
		 * 
		 * @param value the value to set
		 */
		
		public void in(Object value) {
			Field field = getField();
			try {
				field.set(object, value);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		/**
		 * Set the class type 
		 * 
		 * @param clazz the type
		 *
		 * @return a field reference
		 */
		
		public FieldRf type(Class<?> clazz) {
			this.clazz = clazz;
			return this;
		}

		private Field getField() {
			if (clazz == null) {
				clazz = object.getClass();
			}

			Field field = null;
			try {
				field = clazz.getDeclaredField(name);
				field.setAccessible(true);
				return field;
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
			return field;
		}

		private Object getValue(Field field) {
			if (field == null) {
				return null;
			}
			Object obj = null;
			try {
				obj = field.get(object);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			return obj;
		}
	}
	
	/**
	 * A method reference.  
	 */
	public class MethodRf {
		private Class<?> clazz;
		private Object object;
		private String methodName;
		private Class<?>[] args;
		
		public MethodRf(Object object, String name, Class<?>... args) {
			this.object = object;
			this.methodName = name;
			this.args = args;
		}
		
		public MethodRf type(Class<?> calzz) {
			this.clazz = calzz;
			return this;
		}
		
		public <T> T invoke(Class<T> outclazz, Object... args) {
			Method method = getMethod();
			if(method == null) {
				return null;
			}
			try {
				if(outclazz != null){
					Object ret =method.invoke(object, args);
					return outclazz.cast(ret);
				}else{
					method.invoke(object, args);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		private Method getMethod() {
			if (clazz == null) {
				clazz = object.getClass();
			}

//			Log.i("Reflect", "------------getMethod-"+clazz.getName()+"---->"+methodName);
			Method method = null;
			for(Class<?> cls = clazz; cls != Object.class; cls = cls.getSuperclass()){
				try {
					method = cls.getDeclaredMethod(methodName, args);
					method.setAccessible(true);
//					Log.i("Reflect", "------------getMethod-"+cls.getName()+"---->"+methodName);
					return method;
				}catch (Exception e) {
//					Log.w("Reflect", "--getMethod-"+cls.getName()+"---->"+methodName +","+e.getMessage());
				}
			}
			return method;
		}
	}

}
