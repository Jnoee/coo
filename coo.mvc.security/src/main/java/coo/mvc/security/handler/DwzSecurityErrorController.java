package coo.mvc.security.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.web.servlet.ModelAndView;

import coo.mvc.dwz.DwzResultBuild;
import coo.mvc.handler.DwzErrorController;

public class DwzSecurityErrorController extends DwzErrorController {
  public DwzSecurityErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties) {
    super(errorAttributes, errorProperties);
  }

  @Override
  protected ModelAndView processErrorHtml(HttpServletRequest request,
      HttpServletResponse response) {
    ModelAndView mav = super.processErrorAjax(request, response);
    Throwable ex = getException(request);
    if (ex instanceof UnauthenticatedException) {
      mav = new ModelAndView("redirect:/login");
    }
    if (ex instanceof UnauthorizedException) {
      mav = new ModelAndView("/403");
    }
    return mav;
  }

  @Override
  protected ModelAndView processErrorAjax(HttpServletRequest request,
      HttpServletResponse response) {
    ModelAndView mav = super.processErrorAjax(request, response);
    Throwable ex = getException(request);
    if (ex instanceof UnauthenticatedException) {
      mav = new DwzResultBuild().timeout().build();
    }
    if (ex instanceof UnauthorizedException) {
      mav = new DwzResultBuild().denied().build();
    }
    return mav;
  }
}
