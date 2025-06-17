package br.com.project.TRFamilia.aspects;

import br.com.project.TRFamilia.annotations.JustAdmin;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.JoinPoint;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect
public class JustAdminAspect {

    @Pointcut("@annotation(justAdmin)")
    public void adminOnlyMethods(JustAdmin justAdmin) {}

    @Before("adminOnlyMethods(justAdmin)")
    public void checkAdminRole(JoinPoint joinPoint, JustAdmin justAdmin) {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attrs == null) {
            throw new SecurityException("Access denied: Request not found.");
        }

        HttpServletRequest request = attrs.getRequest();
        String userType = (String) request.getAttribute("userType");

        if (userType== null || !userType.equals("admin")) {
            throw new SecurityException("Access Denied: Only administrators can access this route.");
        }
    }
}
