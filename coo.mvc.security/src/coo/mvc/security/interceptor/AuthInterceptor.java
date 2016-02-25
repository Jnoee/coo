package coo.mvc.security.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import coo.base.exception.UncheckedException;
import coo.base.util.CollectionUtils;
import coo.core.security.annotations.Auth;

/**
 * 权限注解拦截器。
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Auth authorizeAnnotation = getAuthorizeAnnotation(handlerMethod);
			if (authorizeAnnotation != null) {
				Subject subject = SecurityUtils.getSubject();
				if (!subject.isAuthenticated()) {
					throw new UnauthenticatedException("未登录或回话已过期。");
				}
				if (!isAccessable(subject, authorizeAnnotation.value())) {
					throw new UnauthorizedException("没有相应的操作权限。");
				}
			}
		}
		return true;
	}

	/**
	 * 从Controller类的方法中获取权限注解。
	 * 
	 * @param handlerMethod
	 *            HandlerMethod
	 * @return 返回获取到的权限注解。
	 */
	private Auth getAuthorizeAnnotation(HandlerMethod handlerMethod) {
		try {
			Method method = handlerMethod.getMethod();
			Auth authorizeAnnotation = method.getAnnotation(Auth.class);
			if (authorizeAnnotation != null) {
				return authorizeAnnotation;
			} else {
				return handlerMethod.getBeanType().getAnnotation(Auth.class);
			}
		} catch (Exception e) {
			throw new UncheckedException("查找Auth注解时发生异常。", e);
		}
	}

	/**
	 * 判断是否有权限。
	 * 
	 * @param subject
	 *            登录对象
	 * @param roles
	 *            权限代码
	 * @return 如果权限集合中包含权限代码返回true，否则返回false。
	 */
	private Boolean isAccessable(Subject subject, String[] roles) {
		// 如果没有指定权限直接返回true。
		if (CollectionUtils.isEmpty(roles)) {
			return true;
		}
		// 如果指定了多个权限，只要包含其中一个就返回true。
		for (String role : roles) {
			if (subject.hasRole(role)) {
				return true;
			}
		}
		return false;
	}
}
