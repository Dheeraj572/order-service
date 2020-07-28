package com.mindtree.order.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;

@Aspect
@Log4j2
@Component
public class LoggingAspect {


	@Around(value = "execution(* com.mindtree.order.*.*.*(..)) "
			+ "&& !within(com.mindtree.order.constraintvalidators.*)")
	public Object logging(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

		ObjectMapper objectMapper = new ObjectMapper();
		String methodName = proceedingJoinPoint.getSignature().getName();
		String className = proceedingJoinPoint.getTarget().getClass().toString();
		Object[] arguments = proceedingJoinPoint.getArgs();
		log.info("Request from " + className + ":" + methodName + "()" + "Arguments :"
				+ objectMapper.writeValueAsString(arguments));
		Object response = proceedingJoinPoint.proceed();
		log.info("Response from " + className + ":" + methodName + "()" + "Response :"
				+ objectMapper.writeValueAsString(response));

		return response;
	}
}
