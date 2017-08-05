package coo.mvc.security.actions;

import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import coo.base.exception.BusinessException;
import coo.base.util.StringUtils;
import coo.mvc.security.captcha.AuthCounter;
import coo.mvc.security.captcha.Captcha;
import coo.mvc.security.model.LoginModel;
import coo.mvc.security.service.AbstractSecurityService;

/**
 * 登录基类。
 */
public abstract class AbstractLoginAction {
  @Resource
  protected AbstractSecurityService<?, ?, ?, ?> securityService;
  @Resource
  protected Captcha captcha;
  @Resource
  protected AuthCounter authCounter;

  /**
   * 查看登录页面。
   * 
   * @param model 数据模型
   */
  @RequestMapping("login")
  public void login(Model model) {
    model.addAttribute(new LoginModel());
    model.addAttribute(authCounter);
  }

  /**
   * 验证登录。
   * 
   * @param model 数据模型
   * @param request 请求对象
   * @param loginModel 登录数据模型
   * @param errors 错误信息
   * 
   * @return 登录成功返回系统首页，失败返回登录页面。
   */
  @RequestMapping("login-auth")
  public String auth(Model model, HttpServletRequest request, LoginModel loginModel,
      BindingResult errors) {
    if (authCounter.isOver() && !captcha.validate(loginModel.getCode())) {
      errors.reject("security.code.wrong");
      model.addAttribute(authCounter);
      captcha.generateImage();
      return "login";
    }
    try {
      securityService.signIn(loginModel.getUsername(), loginModel.getPassword(),
          getRemoteIp(request));
      authCounter.clean();
      return "redirect:/index";
    } catch (BusinessException e) {
      errors.reject("none", e.getMessage());
      authCounter.add();
      model.addAttribute(authCounter);
      captcha.generateImage();
      return "login";
    }
  }

  /**
   * 输出验证码图片。
   * 
   * @param request HTTP请求
   * @param out 页面输出流
   * @throws IOException
   * 
   * @throws IOException 图片输出失败时抛出异常。
   */
  @RequestMapping("captcha-code-image")
  public void captchaCode(HttpServletRequest request, OutputStream out) throws IOException {
    if (StringUtils.isNotBlank(request.getParameter("timestamp"))) {
      captcha.generateImage();
    }
    ImageIO.write(captcha.getImage(), "JPEG", out);
  }

  /**
   * 退出登录。
   * 
   * @return 返回登录页面。
   */
  @RequestMapping("logout")
  public String logout() {
    securityService.signOut();
    return "redirect:/login";
  }

  /**
   * 获取IP地址。
   * 
   * @param request 请求对象
   * @return 返回IP地址。
   */
  private String getRemoteIp(HttpServletRequest request) {
    String ip = request.getHeader("x-forwarded-for");
    if (StringUtils.isNotEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
      ip = ip.split(",")[0];
    } else {
      ip = request.getHeader("x-real-ip");
      if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getRemoteAddr();
      }
    }
    return ip;
  }
}
