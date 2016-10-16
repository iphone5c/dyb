package com.dyb.platforms.fixfunds.common.controller.gyxm;

import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.account.entity.em.AccountType;
import com.dyb.platforms.fixfunds.services.business.account.service.IAccountService;
import com.dyb.platforms.fixfunds.services.business.merchant.entity.em.*;
import com.dyb.platforms.fixfunds.services.business.messengerbean.entity.em.MessengerBeanType;
import com.dyb.platforms.fixfunds.services.business.systemparams.entity.SystemParams;
import com.dyb.platforms.fixfunds.services.business.systemparams.service.ISystemParamsService;
import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.NameValue;
import com.dyb.platforms.fixfunds.services.utils.core.configureations.SettingConfigureationFactory;
import com.dyb.platforms.fixfunds.services.utils.core.controller.BaseController;
import com.dyb.platforms.fixfunds.services.utils.core.exception.DybRuntimeException;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    @Autowired
    private ISystemParamsService systemParamsService;

    /**
     * web登陆验证
     * @param loginName 登陆ID
     * @param password 登陆密码
     * @param accountType 账户类型
     * @return 账户信息
     */
    @RequestMapping(value = "/loginAccount")
    public Object loginAccount(HttpServletRequest request,HttpServletResponse response,String loginName,String password,String accountType) {
        log.info("登陆验证");
        Account account=accountService.loginAccountForClient(loginName, password, AccountType.getAccountTypeByName(accountType));
        if (account==null){
            return validationResult(1001,"登陆失败");
        }else {
            request.getSession().setAttribute("CURRENT_ACCOUNT",account);
            return result("登陆成功");
        }
    }

    /**
     * 获取菜单
     * @param accountType 账户类型
     * @return
     */
    @RequestMapping(value = "/getWebMenu")
    public Object getWebMenu(HttpServletRequest request,HttpServletResponse response,String accountType){
        log.info("获取账户菜单");
        if (DybUtils.isEmptyOrNull(accountType))
            throw new DybRuntimeException("获取菜单账户类型不能为空");
        AccountType menuType=AccountType.getAccountTypeByName(accountType);
        if (menuType==AccountType.信使){
            return result(SettingConfigureationFactory.getMenuListByKey("MEMBER_MENU"));
        }else if (menuType==AccountType.商家){
            return result(SettingConfigureationFactory.getMenuListByKey("MERCHANT_MENU"));
        }else if (menuType==AccountType.服务商){
            return result(SettingConfigureationFactory.getMenuListByKey("SERVICEPROVIDERS_MENU"));
        }else
            return validationResult(1001,"尚未定义此账户类型的菜单");
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
    public Object upload(HttpServletRequest request,HttpServletResponse response,@RequestParam(required=false,defaultValue="/upload")String path){
        Map<String,Object> result= DybUtils.uploadFile(request,response,path);
        if (result==null||result.size()<=0)
            return validationResult(1001,"上传失败");
        else
            return result(result);
    }

    @RequestMapping(value = "/getIndustry")
    public Object getIndustry(){
        return result( Industry.getAllConvertName());
    }

    @RequestMapping(value = "/getIndustryType")
    public Object getIndustryType(){
        return result( IndustryType.getAllConvertName());
    }

    @RequestMapping(value = "/getScale")
    public Object getScale(){
        return result( Scale.getAllConvertName());
    }

    @RequestMapping(value = "/getSex")
    public Object getSex(){
        return result( Sex.getAllConvertName());
    }

    @RequestMapping(value = "/getBusinessCircle")
    public Object getBusinessCircle(){
        return result( BusinessCircle.getAllConvertName());
    }

    @RequestMapping(value = "/getFoundation")
    public Object getFoundation(){
        SystemParams systemParams=systemParamsService.getSystemParamsByKey("FOUNDATION");
        Map<String,String> foundation=new HashMap<>();
        String[] temp= systemParams.getSystemParamsValue().split(",");
        foundation.put("bankName",temp[0]);
        foundation.put("bankNum",temp[1]);
        return result(foundation);
    }

    @RequestMapping(value = "/getBankList")
    public Object getBankList(){
        SystemParams systemParams=systemParamsService.getSystemParamsByKey("BANK");
        Map<String,String> foundation=new HashMap<>();
        String[] temp= systemParams.getSystemParamsValue().split(",");
        List<NameValue> nameValueList=new ArrayList<>();
        for (String bank:temp){
            nameValueList.add(NameValue.create(bank,bank));
        }
        return result(nameValueList);
    }

    @RequestMapping(value = "/getDonationType")
    public Object getDonationType(){
        List<NameValue> nameValueList=new ArrayList<>();
        nameValueList.add(NameValue.create(MessengerBeanType.普通信使豆.toString(),MessengerBeanType.普通信使豆.toString()));
        nameValueList.add(NameValue.create(MessengerBeanType.待提供发票.toString(),MessengerBeanType.待提供发票.toString()));
        return result(nameValueList);
    }

    /**
     * 根据账户code获取账户的名字和电话号码
     * @param accountCode
     * @return
     */
    @RequestMapping(value = "/getAccountByCode")
    public Object getAccountByCode(String accountCode){
        if (DybUtils.isEmptyOrNull(accountCode))
            return validationResult(1001,"账户code不能为空");
        Account account=accountService.getAccountByCode(accountCode,true);
        if (account==null)
            return validationResult(1001,"找不到此账户信息");
        Map<String,String> tjr=new HashMap<>();
        tjr.put("code",account.getAccountCode());
        tjr.put("phone",account.getAccountPhone());
        if (account.getAccountType()==AccountType.信使){
            tjr.put("name",account.getMember().getRealName());
        }else if (account.getAccountType()==AccountType.商家){
            tjr.put("name",account.getMerchant().getPrincipalName());
        }else if (account.getAccountType()==AccountType.服务商){
            tjr.put("name",account.getServiceProviders().getServiceProviderName());
        }
        return result(tjr);
    }

}
