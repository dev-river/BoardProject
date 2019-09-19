package kr.co.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import kr.co.domain.BoardVO;

@Component
@Aspect
public class TestAdvice {

	@Before("execution(* kr.co.service.BoardService*.*(..))")
	public void startLog(JoinPoint jp) {
		System.out.println(">>>>>>>>>>>>>>>>>>startLog>>>>>>>>>>>>>>>>");
		System.out.println(Arrays.toString(jp.getArgs()));
	}
	
	@After("execution(* kr.co.service.BoardService*.*(..))")
	public void endLog() {
		System.out.println("<<<<<<<<<<<<<<<<<<endLog<<<<<<<<<<<<<<<<<");
	}
	
	@Around("execution(* kr.co.service.BoardService*.*(..))")
	public Object checkTime(ProceedingJoinPoint pjp) throws Throwable{ //Throwable이 Exeption보다 상위
		
		long start = System.currentTimeMillis();
		Object obj = pjp.proceed(); // 얘가 pointcuts (실제 메서드 - 메서드별로 반환형이 다르기때문에 Object 사용)
		long end = System.currentTimeMillis();
		System.out.println(":::::실행시간:::::"+(end-start));
		
		BoardVO vo = null;
		if(obj instanceof BoardVO) {
			vo = (BoardVO) obj;
			vo.setBno(74);
			return vo;
		}
		
		
		return obj;
	}
}
