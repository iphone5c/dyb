package com.dyb.platforms.fixfunds.services.utils;

import com.dyb.platforms.fixfunds.services.business.account.entity.Account;
import com.dyb.platforms.fixfunds.services.business.user.entity.User;
import com.dyb.platforms.fixfunds.services.utils.core.exception.DybRuntimeException;
import com.dyb.platforms.fixfunds.services.utils.core.serializes.ISerialize;
import com.dyb.platforms.fixfunds.services.utils.core.serializes.JsonSerialize;
import com.google.code.kaptcha.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * Created by lenovo on 2016/9/8.
 */
public class DybUtils {

    public static String MODEL_TARGET_PATH="_dyb_resources";

    public static String LETTER="ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String SERVICE="127.0.0.1";

    public static String PORT="8080";

    public static boolean isEmptyOrNull(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * 随机获取指定位数的字母
     * @param len 字母位数
     * @return
     */
    public static String getRandomLetter(int len){
        StringBuffer info=new StringBuffer();
        for (int i=0;i<len;i++){
            int start=DybUtils.getRanDom(1, 26);
            info.append(DybUtils.LETTER.substring(start-1,start));
        }
        return info.toString();
    }

    /**
     * 生成一个指定范围的随机数
     * @param min
     * @param max
     * @return
     */
    public static int getRanDom(int min,int max){
        Random random=new Random();
        return random.nextInt(max-min+1)+min;
    }

    public static Date dateAddMinute(Date date, int minute) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(12, minute);
        return ca.getTime();
    }

    public static Date dateAddDay(Date date, int day) {
        int minute = day * 24 * 60;
        return dateAddMinute(date, minute);
    }

    public static Date dateAddMonty(Date date,int month){
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.MONTH,month);
        return ca.getTime();
    }

    public static void copyFile(File sourcefile,File targetFile) throws IOException {

        //新建文件输入流并对它进行缓冲
        FileInputStream input=new FileInputStream(sourcefile);
        BufferedInputStream inbuff=new BufferedInputStream(input);

        //新建文件输出流并对它进行缓冲
        FileOutputStream out=new FileOutputStream(targetFile);
        BufferedOutputStream outbuff=new BufferedOutputStream(out);

        //缓冲数组
        byte[] b=new byte[1024*5];
        int len=0;
        while((len=inbuff.read(b))!=-1){
            outbuff.write(b, 0, len);
        }

        //刷新此缓冲的输出流
        outbuff.flush();

        //关闭流
        inbuff.close();
        outbuff.close();
        out.close();
        input.close();


    }

    public static void copyDirectiory(String sourceDir,String targetDir) throws IOException{

        //新建目标目录

        (new File(targetDir)).mkdirs();

        //获取源文件夹当下的文件或目录
        File[] file=(new File(sourceDir)).listFiles();

        for (int i = 0; i < file.length; i++) {
            if(file[i].isFile()){
                //源文件
                File sourceFile=file[i];
                //目标文件
                File targetFile=new File(new File(targetDir).getAbsolutePath()+File.separator+file[i].getName());

                copyFile(sourceFile, targetFile);

            }

            if(file[i].isDirectory()){
                //准备复制的源文件夹
                String dir1=sourceDir+"/"+file[i].getName();
                //准备复制的目标文件夹
                String dir2=targetDir+"/"+file[i].getName();

                copyDirectiory(dir1, dir2);
            }
        }

    }

    public static boolean isBasisClass(Class<?> cls)
    {
        return (cls.isPrimitive()) || (cls.isAssignableFrom(String.class)) || (cls.isAssignableFrom(Date.class)) || (isWrapClass(cls)) || (cls.isEnum());
    }

    public static boolean isWrapClass(Class<?> cls)
    {
        try
        {
            return ((Class)cls.getField("TYPE").get(null)).isPrimitive(); } catch (Exception e) {
        }
        return false;
    }

    public static String getJsonSerialize(Object obj)
    {
        ISerialize serialize = new JsonSerialize();
        return serialize.serialize(obj);
    }

    public static Object getJsonDeserialize(String json, Class<?> cls)
    {
        ISerialize serialize = new JsonSerialize();
        return serialize.deserialize(json, cls);
    }

    public static <T> T getJsonDeserializeT(String json, Class<T> cls)
    {
        ISerialize serialize = new JsonSerialize();
        return serialize.deserializeT(json, cls);
    }

    public static <T> List<T> getJsonDeserializeListT(String json,Class<T> cls)
    {
        ISerialize serialize = new JsonSerialize();
        return serialize.deserializeListT(json, cls);
    }

    /**
     * 密码加密
     * @param origPwd
     * @return
     */
    public static String encryptPassword(String origPwd){
        return MD5.MD5Encode(origPwd);
    }

    /**
     * 密码验证
     * @param origPwd 加密前的密码
     * @param encryptedPwd 加密后的密码
     * @return
     */
    public static boolean verifyPassword(String origPwd,String encryptedPwd){
        return MD5.verify(origPwd, encryptedPwd);
    }

    /**
     * 获取当前登陆用户
     * @param request
     * @return
     */
    public static User getCurrentUser(HttpServletRequest request){
        User user= (User) request.getSession().getAttribute("CURRENT_USER");
        return user;
    }

    /**
     * 获取当前前端登陆用户
     * @param request
     * @return
     */
    public static Account getCurrentAccount(HttpServletRequest request){
        Account account= (Account) request.getSession().getAttribute("CURRENT_ACCOUNT");
        return account;
    }

    /**
     * 注销当前前端登录用户
     * @param request
     */
    public static void destoryCurrentAccount(HttpServletRequest request){
        request.getSession().removeAttribute("CURRENT_ACCOUNT");
    }

    /**
     * 将令牌设置到应用上下文
     * @param token 令牌
     */
    public static void setCuurentAccountClient(String token,Account account){
        Map<String,Account> session=null;
        Object obj=ContextLoader.getCurrentWebApplicationContext().getServletContext().getAttribute("CURRENT_ACCOUNT_CLIENT");
        if (obj==null){
            session=new HashMap<>();
            session.put(token, account);
        }else {
            session= (Map<String, Account>) obj;
            session.put(token, account);
        }
        ContextLoader.getCurrentWebApplicationContext().getServletContext().setAttribute("CURRENT_ACCOUNT_CLIENT",session);
    }

    /**
     * 根据token找到当前有效用户
     * @param token 令牌
     * @return
     */
    public static Account getCurrentAccountClient(String token){
        Map<String,Account> session= (Map<String, Account>) ContextLoader.getCurrentWebApplicationContext().getServletContext().getAttribute("CURRENT_ACCOUNT_CLIENT");
        return session.get(token);
    }

    /**
     * 获取当前验证码
     * @param request
     * @return
     */
    public static String getKaptchaCode(HttpServletRequest request){
        return (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
    }

    /**
     * 获取ip 地址
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if(index != -1){
                return ip.substring(0,index);
            }else{
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }
        return request.getRemoteAddr();
    }

    /**
     * 获取项目真实路径
     * @param path 在真实路径后加上自定义的路径
     * @return 项目路径+自定义路径
     */
    public static String getServletContextRealPath(String path){
        return ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath(path);
    }

    /**
     *
     * @param request
     * @param response
     * @param filePath
     * @return
     */
    public static Map<String,Object> uploadFile(HttpServletRequest request,HttpServletResponse response,String filePath){
        Map<String,Object> result = new HashMap<String,Object>();
        String path = null;
        if(DybUtils.isEmptyOrNull(filePath)){
            path = request.getServletContext().getRealPath("/upload");
        }else {
            path = request.getServletContext().getRealPath(filePath);
        }
        File fPath = new File(path);
        if (!fPath.exists()) {
            fPath.mkdirs();
        }
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request ;
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()){
                Map<String,Object> fileInfo = new HashMap<>();
                MultipartFile file = multiRequest.getFile((String)iter.next());
                //有上传文件的话，容量是大于0的。
                if (file != null && file.getSize() > 0){
                    String fieldName = ((CommonsMultipartFile)file).getFileItem().getFieldName();
                    String fileName = file.getOriginalFilename();
                    String ext = fileName.substring(fileName.lastIndexOf(".")) ;
                    String fileReName = UUID.randomUUID().toString() + ext ;
                    File localFile = new File(path,fileReName);
                    try {
                        file.transferTo(localFile);
                        fileInfo.put("filePath",path +"/"+fileReName);
                        fileInfo.put("path",filePath + "/" + fileReName);
                        fileInfo.put("fileName",fileName);
                        fileInfo.put("fileUploadTime", DybConvert.dateToString(new Date(),DybConvert.DATEFORMAT_DATETIME_EN_LONG));
                        result.put(fieldName,fileInfo);
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new DybRuntimeException("文件上传失败");
                    }
                }
            }
        }else {
            throw new DybRuntimeException("没有上传的文件");
        }
        return result;
    }
}
