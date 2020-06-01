package com.nwp.util;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


/**
 * 字符串处理类
 */
public class StringUtils
{

    /**
     * 判断对象是否Empty(null或元素为0)<br> 实用于对如下对象做判断:String Collection及其子类 Map及其子类
     * 
     * @param pObj
     *            待检查对象
     * @return boolean 返回的布尔值
     */
    public static final boolean isEmpty(Object pObj)
    {
        if (pObj == null) return true;
        if (pObj == "") return true;
        if (pObj instanceof String)
        {
            if (((String)pObj).trim().length() == 0)
            {
                return true;
            }
        }
        else if (pObj instanceof Collection<?>)
        {
            if (((Collection<?>)pObj).size() == 0)
            {
                return true;
            }
        }
        else if (pObj instanceof Map<?, ?>)
        {
            if (((Map<?, ?>)pObj).size() == 0)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断对象是否为NotEmpty(!null或元素>0)<br> 实用于对如下对象做判断:String Collection及其子类 Map及其子类
     * 
     * @param pObj
     *            待检查对象
     * @return boolean 返回的布尔值
     */
    public static final boolean isNotEmpty(Object pObj)
    {
        if (pObj == null) return false;
        if (pObj == "") return false;
        if (pObj instanceof String)
        {
            if (((String)pObj).trim().length() == 0)
            {
                return false;
            }
        }
        else if (pObj instanceof Collection<?>)
        {
            if (((Collection<?>)pObj).size() == 0)
            {
                return false;
            }
        }
        else if (pObj instanceof Map<?, ?>)
        {
            if (((Map<?, ?>)pObj).size() == 0)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * 去掉空值
     * 
     * @param str
     *            原值
     * @return String 处理后值
     */
    public static String nvlString(String str)
    {
        String s_temp = str;
        if (s_temp == null || s_temp.toUpperCase().equals("NULL"))
        {
            s_temp = "";
        }
        return s_temp;
    }

    /**
     * 去掉空值
     * 
     * @param str
     *            原值
     * @return String 处理后值
     */
    public static String nvlString(Object str)
    {
        String s_temp = "" + str;
        if (s_temp == null || s_temp.toUpperCase().equals("NULL"))
        {
            s_temp = "";
        }
        return s_temp;
    }

    public static String changHTMLNull(String str)
    {
        if (str == null || "".equals(str.trim()))
            str = "&nbsp;";
        else
            str = str.trim();
        return str;
    }

    /**
     * 相当于oracle的nvl函数，如果输入不null或者不字符串的各种“null”后者空字符串，则返回输入，否则返回第2个参数
     * 
     * @param str
     *            原值
     * @param defu
     *            缺省
     * @return String 处理后值
     */
    public static String nvlString(Object str, String defu)
    {
        String s_temp = str + "";
        if (s_temp == null || s_temp.toUpperCase().equals("NULL") || s_temp.length() == 0)
        {
            s_temp = defu;
        }
        return s_temp;
    }

    /**
     * 相当于oracle的nvl函数，如果输入不null或者不字符串的各种“null”后者空字符串，则返回输入，否则返回第2个参数
     * 
     * @param str
     *            原值
     * @param defu
     *            缺省
     * @return String 处理后值
     */
    public static String nvlString(String str, String defu)
    {
        String s_temp = str;
        if (s_temp == null || s_temp.toUpperCase().equals("NULL") || s_temp.length() == 0)
        {
            s_temp = defu;
        }
        return s_temp;
    }

    /**
     * 将文件名中的汉字转为UTF8编码的串,以便下载时能正确显示另存的文件名. 纵横软件制作中心雨亦奇2003.08.01
     * 
     * @param s
     *            原文件名
     * @return 重新编码后的文件名
     */
    public static String toISOString(String s)
    {
        String temp = s;
        try
        {
            temp = new String(temp.getBytes(), "ISO-8859-1");
        }
        catch (Exception e)
        {}
        return temp;
    }

    /**
     * 数字型字符串转整形
     * 
     * @param str
     * @return String
     */
    public static int StringToInt(String str)
    {
        int i_temp = 0;
        try
        {
            i_temp = Integer.parseInt(nvlString(str));
        }
        catch (Exception e)
        {

        }
        return i_temp;
    }

    /**
     * 数字型字符串转长整形
     * 
     * @param str
     * @return String
     */
    public static long StringToLong(String str)
    {
        long i_temp = 0;
        try
        {
            i_temp = Long.parseLong(nvlString(str));
        }
        catch (Exception e)
        {

        }
        return i_temp;
    }

    /**
     * 数字型字符串转浮点型
     * 
     * @param str
     * @return String
     */
    public static float StringToFloat(String str)
    {
        float i_temp = 0;
        try
        {
            i_temp = Float.parseFloat(nvlString(str));
        }
        catch (Exception e)
        {

        }
        return i_temp;
    }

    /**
     * 数字型字符串转双精度浮点型
     * 
     * @param str
     * @return String
     */
    public static double StringToDouble(String str)
    {
        double i_temp = 0;
        try
        {
            i_temp = Double.parseDouble(nvlString(str));
        }
        catch (Exception e)
        {

        }
        return i_temp;
    }

    /**
     * 过滤sql注入字符
     * 
     * @param str
     * @return void
     */
    public static String sql_inj(String str)
    {
        if (str != null && !"".equals(str))
        {
            String injStr = "and:exec:insert:select:delete:update:count:*:%:chr:mid:master:truncate:char:declare:;:or:-:+:,:'";
            String[] injStra = injStr.split(":");
            for (int i = 0; i < injStra.length; i++ )
            {
                while (str.indexOf(injStra[i]) >= 0)
                {
                    str = str.replace(injStra[i], "");
                }
            }
        }
        return str;
    }

    /**
     * 功能：获取U03表的时间条件 描述：传入一个时间范围，获得U03表在该时间范围内有效单位的时间选择sql，表的别名是方便在关联查询时对U03表取别名的支持
     * 
     * @param beginDate
     *            有效时间
     * @param endDate
     *            失效时间
     * @param tableAs
     *            表别名
     * @return String sql条件
     */
    public static String getU03TimeCondition(String beginDate, String endDate, String tableAs)
    {
        if (beginDate == null || beginDate.equals("") || beginDate.length() < 4)
        {
            beginDate = "299912";
        }
        if (endDate == null || endDate.equals("") || endDate.length() < 4)
        {
            endDate = "200012";
        }
        if (beginDate.length() >= 4 && beginDate.length() < 6)
        {
            beginDate = beginDate.substring(0, 4) + "01";
        }
        if (endDate.length() >= 4 && endDate.length() < 6)
        {
            endDate = endDate.substring(0, 4) + "12";
        }
        beginDate = beginDate.substring(0, 6);
        endDate = endDate.substring(0, 6);
        if (tableAs != null && !tableAs.equals("") && tableAs.length() > 0)
        {
            tableAs += ".";
        }
        return " nvl(" + tableAs + "begin_date,'200012')<='" + endDate + "' and nvl(" + tableAs
               + "end_date,'999912')>='" + beginDate + "'";
    }

    /**
     * 将数字转换为相应的中文
     * 
     * @return
     */
    public static String changeNumToCnLing(String num)
    {
        StringBuffer cn = new StringBuffer();
        int numLength = num.length();
        char temp = '0';
        for (int i = 0; i < numLength; i++ )
        {
            temp = num.charAt(i);
            switch (temp)
            {
                case '0':
                    cn.append("零");
                    break;
                case '1':
                    cn.append("一");
                    break;
                case '2':
                    cn.append("二");
                    break;
                case '3':
                    cn.append("三");
                    break;
                case '4':
                    cn.append("四");
                    break;
                case '5':
                    cn.append("五");
                    break;
                case '6':
                    cn.append("六");
                    break;
                case '7':
                    cn.append("七");
                    break;
                case '8':
                    cn.append("八");
                    break;
                case '9':
                    cn.append("九");
                    break;
                default:
                    break;
            }
        }
        return cn.toString();
    }

    /**
     * 地道一个字符串的长度，显示的长度，一个汉字或日韩文长度为2，英文字符长度为1
     * 
     * @return
     */
    public static int length(String s)
    {
        if (s == null)
        {
            return 0;
        }
        char[] c = s.toCharArray();
        int len = 0;
        int length = c.length;
        for (int i = 0; i < length; i++ )
        {
            len++ ;
            if (!isLetter(c[i]))
            {
                len++ ;
            }
        }
        return len;
    }

    /**
     * 判断一个字符是Ascill字符还是其他字符（如：汉，日，韩文字符）
     * 
     * @return
     */
    public static boolean isLetter(char c)
    {
        int k = 0x80;
        return c / k == 0 ? true : false;
    }

    /**
     * 封装JDK自带的UUID，通过Random数字生成，中间无-分割
     */
    public static String uuid()
    {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 正则过滤特殊字符
     */
	public static String StringFilter(String str) throws PatternSyntaxException {
		// 只允许字母和数字
		// String regEx = "[^a-zA-Z0-9]";
		// 清除掉所有特殊字符
		String regEx = "[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

}

