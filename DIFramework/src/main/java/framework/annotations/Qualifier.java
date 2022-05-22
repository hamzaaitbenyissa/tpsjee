package framework.annotations;

import java.lang.annotation.*;
import static java.lang.annotation.ElementType.*;


@Target({FIELD,METHOD,PARAMETER,TYPE,ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Qualifier {
	String value() default "";
}