package com.carrentalooo.carrentalmytest.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class ControllersAdvise {
	Logger log = Logger.getLogger(getClass());
	@Before("execution(* com.car.rent.reservation.controller.*.*(..))")
	public void beforeCall(JoinPoint jp)
	{
		log.info("A call is about to process");
		Object[] args = jp.getArgs();
		log.info("Arguments are:");
		for(Object arg:args)
		{
			log.info(arg);
		}
	}
	@After("execution(* com.car.rent.reservation.controller.*.*(..))")
	public void afterCall(JoinPoint jp)
	{
		log.info("A call is processed");
		Object[] args = jp.getArgs();
		log.info("Arguments are:");
		for(Object arg:args)
		{
			log.info(arg);
		}
	}
}
