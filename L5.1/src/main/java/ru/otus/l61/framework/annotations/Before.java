package ru.otus.l61.framework.annotations;

import java.lang.annotation.*;

/**
 * Created by maximfirsov on 06/07/2017.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Inherited
public @interface Before {

}
