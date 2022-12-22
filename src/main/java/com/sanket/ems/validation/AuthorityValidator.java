package com.sanket.ems.validation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

//@Aspect
public class AuthorityValidator {

    //@Pointcut(value = "methodAnnotatedWithHasAuthority()")
    public void validateAuthority(){
        //validation stuff to do
    }

    //@Before("@hasAuthority()")
    public void methodAnnotatedWithHasAuthority(){}
}
