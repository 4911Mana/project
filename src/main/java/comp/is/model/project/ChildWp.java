package comp.is.model.project;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * A qualifier used to differentiate between multiple data repositories. 
 * 
 * If you only have 1 EntityManager, this annotation is optional
 */
@Qualifier
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ChildWp
{
   /* class body intentionally left blank */
}
