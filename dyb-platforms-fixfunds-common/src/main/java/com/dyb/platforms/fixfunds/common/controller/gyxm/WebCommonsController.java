package com.dyb.platforms.fixfunds.common.controller.gyxm;

import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.account.entity.em.AccountType;
import com.dyb.platforms.fixfunds.services.business.account.service.IAccountService;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.configureations.SettingConfigureationFactory;
import com.dyb.platforms.fixfunds.services.utils.core.controller.BaseController;
import com.dyb.platforms.fixfunds.services.utils.core.exception.DybRuntimeException;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Administrator on 2015/7/1.
 */
@RestController
@RequestMapping(value = "/web/commons")
public class WebCommonsController extends BaseController {

    public Logger log = Logger.getLogger(WebCommonsController.class);//日志

    @Autowired
    private IAccountService accountService;
    @Autowired
    private Producer captchaProducer = null;

    /**
     * web登陆验证
     * @param loginName 登陆ID
     * @param password 登陆密码
     * @param accountType 账户类型
     * @return 账户信息
     */
    @RequestMapping(value = "/loginAccount")
    public void loginAccount(HttpServletRequest request,HttpServletResponse response,String loginName,String password,String accountType) {
        log.info("登陆验证");
        Account account=accountService.loginAccountForClient(loginName, password, AccountType.getAccountTypeByName(accountType));
        if (account==null){
            validationResultJSONP(request,response,1001,"登陆失败");
        }else {
            request.getSession().setAttribute("CURRENT_ACCOUNT",account);
            resultJSONP(request,response,"登陆成功");
        }
    }

    /**
     * 获取菜单
     * @param accountType 账户类型
     * @return
     */
    @RequestMapping(value = "/getWebMenu")
    public void getWebMenu(HttpServletRequest request,HttpServletResponse response,String accountType){
        log.info("获取账户菜单");
        if (DybUtils.isEmptyOrNull(accountType))
            throw new DybRuntimeException("获取菜单账户类型不能为空");
        AccountType menuType=AccountType.getAccountTypeByName(accountType);
        if (menuType==AccountType.信使){
            resultJSONP(request,response,SettingConfigureationFactory.getMenuListByKey("MEMBER_MENU"));
        }else if (menuType==AccountType.商家){
            resultJSONP(request,response,SettingConfigureationFactory.getMenuListByKey("MERCHANT_MENU"));
        }else if (menuType==AccountType.服务商){
            resultJSONP(request,response,SettingConfigureationFactory.getMenuListByKey("SERVICEPROVIDERS_MENU"));
        }else
            validationResultJSONP(request,response,1001,"尚未定义此账户类型的菜单");
    }

    @RequestMapping(value = "/getKaptchaImage")
    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String code = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        System.out.println("******************验证码是: " + code + "******************");

        response.setDateHeader("Expires", 0);

        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");

        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");

        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");

        // return a jpeg
        response.setContentType("image/jpeg");

        // create the text for the image
        String capText = captchaProducer.createText();

        // store the text in the session
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

        // create the image with the text
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();

        // write the data out
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }

    @RequestMapping(value = "/upload")
    public void upload(HttpServletRequest request,HttpServletResponse response,String path){
        Map<String,Object> result= DybUtils.uploadFile(request,response,path);
        if (result==null||result.size()<0)
            validationResultJSONP(request,response,1001,"上传失败");
        else
            resultJSONP(request,response,result);
    }

}