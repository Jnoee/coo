package coo.struts.security.interceptor;

import java.lang.reflect.Method;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import coo.base.exception.UncheckedException;
import coo.base.util.CollectionUtils;
import coo.core.security.annotations.Auth;

/**
 * 权限注解拦截器。
 */
public class AuthInterceptor extends AbstractInterceptor {
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Auth authorizeAnnotation = getAuthorizeAnnotation(invocation);
		if (authorizeAnnotation != null) {
			Subject subject = SecurityUtils.getSubject();
			if (!subject.isAuthenticated()) {
				throw new UnauthenticatedException();
			}
			if (!isAccessable(subject, authorizeAnnotation.value())) {
				throw new UnauthorizedException();
			}
		}
		return invocation.invoke();
	}

	/**
	 * 从Action类的方法中获取权限注解。
	 * 
	 * @param invocation
	 *            ActionInvocation
	 * @return 返回获取到的权限注解。
	 */
	private Auth getAuthorizeAnnotation(ActionInvocation invocation) {
		ActionProxy actionProxy = invocation.getProxy();
		Object action = actionProxy.getAction();
		String methodName = actionProxy.getMethod();
		try {
			Method method = action.getClass().getMethod(methodName);
			Auth authorizeAnnotation = method.getAnnotation(Auth.class);
			if (authorizeAnnotation != null) {
				return authorizeAnnotation;
			} else {
				return action.getClass().getAnnotation(Auth.class);
			}
		} catch (Exception e) {
			throw new UncheckedException("查找Authorize注解时发生异常。", e);
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
