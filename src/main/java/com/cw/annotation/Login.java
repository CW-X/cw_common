/**
 * 
 */
package com.cw.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author  xueyongfei
 * @date 2020年8月7日
 */
@Target({java.lang.annotation.ElementType.TYPE, java.lang.annotation.ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Login {
	boolean value() default true;
}
