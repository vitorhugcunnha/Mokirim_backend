package org.mokirim.infrastructure.security.authorization.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

import org.mokirim.infrastructure.security.authorization.role.Role;

@Target({ TYPE, METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Secured {
    Role[] value();
}
