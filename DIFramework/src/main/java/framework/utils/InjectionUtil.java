package framework.utils;

import framework.Injector;
import framework.annotations.Autowired;
import framework.annotations.Qualifier;
import org.burningwave.core.classes.FieldCriteria;


import java.lang.reflect.Field;
import java.util.Collection;

import static org.burningwave.core.assembler.StaticComponentContainer.Fields;

public class InjectionUtil {

	private InjectionUtil() {
		super();
	}

	/**
	 * Perform injection recursively, for each service inside the Client class
	 */
	public static void autowire(Injector injector, Class<?> classz, Object classInstance)
			throws InstantiationException, IllegalAccessException {
		Collection<Field> fields = Fields.findAllAndMakeThemAccessible(
			FieldCriteria.forEntireClassHierarchy().allThoseThatMatch(field ->
				field.isAnnotationPresent(Autowired.class)
			), 
			classz
		);
		for (Field field : fields) {
			String qualifier = field.isAnnotationPresent(Qualifier.class)
					? field.getAnnotation(Qualifier.class).value()
					: null;
			Object fieldInstance = injector.getBeanInstance(field.getType(), field.getName(), qualifier);
			Fields.setDirect(classInstance, field, fieldInstance);
			autowire(injector, fieldInstance.getClass(), fieldInstance);
		}
	}

}
