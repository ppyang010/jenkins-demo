package org.movie.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import com.alibaba.fastjson.JSON;

@Aspect
public class LogAspect {
	Logger logger = Logger.getLogger(LogAspect.class);
	
	@Pointcut("execution(* org.movie.service..*.*(..))")
	public void pointCut() {
		
	}
	
	
//	@Before("pointCut()")
	public void test(JoinPoint jionPoint) {
		System.out.println("--------------begin before advice -----------------");
//		System.out.println(jionPoint.getTarget());
		System.out.println(jionPoint.getSignature());
//		System.out.println(jionPoint.getArgs());
//		System.out.println(jionPoint.getKind());
//		System.out.println(jionPoint.getSourceLocation());
//		System.out.println(jionPoint.getStaticPart());
		Signature signature = jionPoint.getSignature();
		System.out.println(signature.getDeclaringTypeName());
//		System.out.println(signature.getDeclaringType());
//		System.out.println(signature.getModifiers());
//		System.out.println(signature.getName());
		Object[] args = jionPoint.getArgs();
		System.out.println(JSON.toJSONString(args));
	}
	
	@Before("pointCut()")
	public void beginLog(JoinPoint jionPoint) {
		Signature signature = jionPoint.getSignature();
		StringBuilder builder=new StringBuilder();
		builder.append("Begin method ！！！ class name:");
		builder.append(signature.getDeclaringTypeName());
		builder.append("！！！！ method name：");
		builder.append(signature.getName());
		builder.append("!!! parameter is :");
		builder.append(JSON.toJSONString(jionPoint.getArgs()));
		logger.debug(builder);
	}
	@After("pointCut()")
	public void endLog(JoinPoint jionPoint) {
		Signature signature = jionPoint.getSignature();
		StringBuilder builder=new StringBuilder();
		builder.append("End method ！！！ class name:");
		builder.append(signature.getDeclaringTypeName());
		builder.append("！！！！ method name：");
		builder.append(signature.getName());
		logger.debug(builder);
	}
	
}
