package com.atlas.client.extension.petquestionnaire.core;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * @author Andrew Jo
 *
 */
@Target(ElementType.FIELD)  
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreCopy {

}