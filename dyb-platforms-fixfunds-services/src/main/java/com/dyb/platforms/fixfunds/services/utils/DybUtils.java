package com.dyb.platforms.fixfunds.services.utils;

import com.dyb.platforms.fixfunds.services.utils.core.serializes.ISerialize;
import com.dyb.platforms.fixfunds.services.utils.core.serializes.JsonSerialize;

import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2016/9/8.
 */
public class DybUtils {

    public static String MODEL_TARGET_PATH="_dyb_resources";

    public static boolean isEmptyOrNull(String str) {
        return str == null || str.isEmpty();
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
}
