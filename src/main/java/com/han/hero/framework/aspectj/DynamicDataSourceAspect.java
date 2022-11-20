package com.han.hero.framework.aspectj;

import com.han.hero.framework.annotation.DS;
import com.han.hero.framework.datasource.DynamicDataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

@Aspect
@Component
public class DynamicDataSourceAspect {

    @Pointcut("@annotation(com.han.hero.framework.annotation.DS)")
    public void dataSourcePointCut() {
    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        if (signature instanceof MethodSignature) {
            // 注解在方法上
            Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
            // 获取被拦截方法参数名列表(使用Spring支持类库)
            LocalVariableTableParameterNameDiscoverer localVariableTable = new LocalVariableTableParameterNameDiscoverer();
            String[] paramNameArr = localVariableTable.getParameterNames(method);
            // 使用SpEL进行key的解析
            ExpressionParser parser = new SpelExpressionParser();
            // SpEL上下文
            StandardEvaluationContext context = new StandardEvaluationContext();
            for (int i = 0; i < Objects.requireNonNull(paramNameArr).length; i++) {
                context.setVariable(paramNameArr[i], joinPoint.getArgs()[i]);
            }
            String value = method.getAnnotation(DS.class).value();
            if (value.matches("^#.*.$")) {
                DynamicDataSourceContextHolder.setContextKey(parser.parseExpression(value).getValue(context, String.class));
            } else {
                DynamicDataSourceContextHolder.setContextKey(value);
            }
        } else {
            // 注解在类上
            Class<? extends Signature> targetClass = signature.getClass();
            DynamicDataSourceContextHolder.setContextKey(targetClass.getAnnotation(DS.class).value());
        }

        try {
            return joinPoint.proceed();
        } finally {
            DynamicDataSourceContextHolder.removeContextKey();
        }
    }

}
