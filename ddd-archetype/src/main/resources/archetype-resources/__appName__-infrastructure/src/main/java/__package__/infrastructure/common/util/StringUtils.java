#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static final String DEFAULT_CHARSET = "UTF-8";

    public final static Pattern PATTERN = Pattern.compile("[A-Z]");
    /**
     * 字符编码
     */
    public final static String ENCODING = "UTF-8";
    /**
     * 常用正则表达式：匹配非负整数（正整数 + 0）
     */
    public final static String REGEXP_INTEGER_1 = "^${symbol_escape}${symbol_escape}d+${symbol_dollar}";

    /**
     * 常用正则表达式：匹配正整数
     */
    public final static String REGEXP_INTEGER_2 = "^[0-9]*[1-9][0-9]*${symbol_dollar}";

    /**
     * 常用正则表达式：匹配非正整数（负整数 + 0）
     */
    public final static String REGEXP_INTEGER_3 = "^((-${symbol_escape}${symbol_escape}d+) ?(0+))${symbol_dollar}";

    /**
     * 常用正则表达式：匹配负整数
     */
    public final static String REGEXP_INTEGER_4 = "^-[0-9]*[1-9][0-9]*${symbol_dollar}";

    /**
     * 常用正则表达式：匹配整数
     */
    public final static String REGEXP_INTEGER_5 = "^-?${symbol_escape}${symbol_escape}d+${symbol_dollar}";

    /**
     * 常用正则表达式：匹配非负浮点数（正浮点数 + 0）
     */
    public final static String REGEXP_FLOAT_1 = "^${symbol_escape}${symbol_escape}d+(${symbol_escape}${symbol_escape}.${symbol_escape}${symbol_escape}d+)?${symbol_dollar}";

    /**
     * 常用正则表达式：匹配正浮点数
     */
    public final static String REGEXP_FLOAT_2 =
            "^(([0-9]+${symbol_escape}${symbol_escape}.[0-9]*[1-9][0-9]*) ?([0-9]*[1-9][0-9]*${symbol_escape}${symbol_escape}.[0-9]+) ?([0-9]*[1-9][0-9]*))${symbol_dollar}";

    /**
     * 常用正则表达式：匹配非正浮点数（负浮点数 + 0）
     */
    public final static String REGEXP_FLOAT_3 = "^((-${symbol_escape}${symbol_escape}d+(${symbol_escape}${symbol_escape}.${symbol_escape}${symbol_escape}d+)?) ?(0+(${symbol_escape}${symbol_escape}.0+)?))${symbol_dollar}";

    /**
     * 常用正则表达式：匹配负浮点数
     */
    public final static String REGEXP_FLOAT_4 =
            "^(-(([0-9]+${symbol_escape}${symbol_escape}.[0-9]*[1-9][0-9]*) ?([0-9]*[1-9][0-9]*${symbol_escape}${symbol_escape}.[0-9]+) ?([0-9]*[1-9][0-9]*)))${symbol_dollar}";

    /**
     * 常用正则表达式：匹配浮点数
     */
    public final static String REGEXP_FLOAT_5 = "^(-?${symbol_escape}${symbol_escape}d+)(${symbol_escape}${symbol_escape}.${symbol_escape}${symbol_escape}d+)?${symbol_dollar}";

    /**
     * 常用正则表达式：匹配由26个英文字母组成的字符串
     */
    public final static String REGEXP_LETTER_1 = "^[A-Za-z]+${symbol_dollar}";

    /**
     * 常用正则表达式：匹配由26个英文字母的大写组成的字符串
     */
    public final static String REGEXP_LETTER_2 = "^[A-Z]+${symbol_dollar}";

    /**
     * 常用正则表达式：匹配由26个英文字母的小写组成的字符串
     */
    public final static String REGEXP_LETTER_3 = "^[a-z]+${symbol_dollar}";

    /**
     * 常用正则表达式：匹配由数字和26个英文字母组成的字符串
     */
    public final static String REGEXP_LETTER_4 = "^[A-Za-z0-9]+${symbol_dollar}";

    /**
     * 常用正则表达式：匹配由数字、26个英文字母或者下划线组成的字符串
     */
    public final static String REGEXP_LETTER_5 = "^${symbol_escape}${symbol_escape}w+${symbol_dollar}";
    /**
     * 正则表达式：匹配email地址
     */
    public static final String REGEXP_EMAIL =
            "^([a-z0-9A-Z-_]+[-|${symbol_escape}${symbol_escape}.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?${symbol_escape}${symbol_escape}.)+[a-zA-Z]{2,}${symbol_dollar}";

    /**
     * 常用正则表达式：匹配url
     */
    public final static String REGEXP_URL_1 = "^[a-zA-z]+://(${symbol_escape}${symbol_escape}w+(-${symbol_escape}${symbol_escape}w+)*)(${symbol_escape}${symbol_escape}.(${symbol_escape}${symbol_escape}w+(-${symbol_escape}${symbol_escape}w+)*))*(${symbol_escape}${symbol_escape}?${symbol_escape}${symbol_escape}S*)?${symbol_dollar}";

    /**
     * 常用正则表达式：匹配url
     */
    public final static String REGEXP_URL_2 = "[a-zA-z]+://[^${symbol_escape}${symbol_escape}s]*";

    /**
     * 正则表达式：验证URL
     */
    public static final String REGEXP_URL_3 = "http(s)?://([${symbol_escape}${symbol_escape}w-]+${symbol_escape}${symbol_escape}.)+[${symbol_escape}${symbol_escape}w-]+(/[${symbol_escape}${symbol_escape}w- ./?%&=]*)?";
    /**
     * 常用正则表达式：匹配中文字符
     */
    public final static String REGEXP_CHINESE_1 = "[${symbol_escape}${symbol_escape}u4e00-${symbol_escape}${symbol_escape}u9fa5]";

    /**
     * 常用正则表达式：匹配双字节字符(包括汉字在内)
     */
    public final static String REGEXP_CHINESE_2 = "[^${symbol_escape}${symbol_escape}x00-${symbol_escape}${symbol_escape}xff]";

    /**
     * 常用正则表达式：匹配空行
     */
    public final static String REGEXP_LINE = "${symbol_escape}${symbol_escape}n[${symbol_escape}${symbol_escape}s ? ]*${symbol_escape}${symbol_escape}r";

    /**
     * 常用正则表达式：匹配HTML标记
     */
    public final static String REGEXP_HTML_1 = "/ <(.*)>.* <${symbol_escape}${symbol_escape}/${symbol_escape}${symbol_escape}1> ? <(.*) ${symbol_escape}${symbol_escape}/>/";

    /**
     * 常用正则表达式：匹配首尾空格
     */
    public final static String REGEXP_STARTENDEMPTY = "(^${symbol_escape}${symbol_escape}s*) ?(${symbol_escape}${symbol_escape}s*${symbol_dollar})";

    /**
     * 常用正则表达式：匹配帐号是否合法(字母开头，允许6-16字节，允许字母数字下划线)
     */
    public final static String REGEXP_ACCOUNTNUMBER = "^[a-zA-Z][a-zA-Z0-9_]{6,15}${symbol_dollar}";

    /**
     * 常用正则表达式：匹配国内电话号码，匹配形式如 0511-4405222 或 021-87888822
     */
    public final static String REGEXP_TELEPHONE = "${symbol_escape}${symbol_escape}d{3}-${symbol_escape}${symbol_escape}d{8} ?${symbol_escape}${symbol_escape}d{4}-${symbol_escape}${symbol_escape}d{7}";
    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEXP_MOBILE = "^((13[0-9])|(14[0-9])|(15[0-9])|(16[0-9])|(17[0-9])|(18[0-9]))${symbol_escape}${symbol_escape}d{8}${symbol_dollar}";
    /**
     * 常用正则表达式：腾讯QQ号, 腾讯QQ号从10000开始
     */
    public final static String REGEXP_QQ = "[1-9][0-9]{4,}";

    /**
     * 常用正则表达式：匹配中国邮政编码
     */
    public final static String REGEXP_POSTBODY = "[1-9]${symbol_escape}${symbol_escape}d{5}(?!${symbol_escape}${symbol_escape}d)";

    /**
     * 常用正则表达式：匹配身份证, 中国的身份证为15位或18位
     */
    public final static String REGEXP_IDCARD = "${symbol_escape}${symbol_escape}d{15} ?${symbol_escape}${symbol_escape}d{18}";

    /**
     * 正则表达式：验证IP地址
     */
    public static final String REGEXP_IP_ADDR = "(25[0-5]|2[0-4]${symbol_escape}${symbol_escape}d|[0-1]${symbol_escape}${symbol_escape}d{2}|[1-9]?${symbol_escape}${symbol_escape}d)";

    public final static Pattern REFERER_PATTERN = Pattern.compile("@([^@^${symbol_escape}${symbol_escape}s^:]{1,})([${symbol_escape}${symbol_escape}s${symbol_escape}${symbol_escape}:${symbol_escape}${symbol_escape},${symbol_escape}${symbol_escape};]{0,1})");// @.+?[${symbol_escape}${symbol_escape}s:]

    private static Random randGen = null;
    private static char[] numbersAndLetters = null;

    /**
     * 验证字符串是否匹配指定正则表达式
     *
     * @param content
     * @param regExp
     * @return
     */
    public static boolean regExpVali(String content, String regExp) {
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(content);
        return matcher.matches();
    }

    /**
     * double精度调整
     *
     * @param doubleValue 需要调整的值123.454
     * @param format      目标样式".${symbol_pound}${symbol_pound}"
     * @return
     */
    public static String decimalFormat(double doubleValue, String format) {
        DecimalFormat myFormatter = new DecimalFormat(format);
        String formatValue = myFormatter.format(doubleValue);
        return formatValue;
    }

    /**
     * 首字母转小写
     *
     * @param s
     * @return
     */
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * 首字母转大写
     *
     * @param s
     * @return
     */
    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * 生成随机码
     *
     * @return
     */
    public static final String randomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 生成随机码，去掉-，共32位
     *
     * @return
     */
    public static final String randomUUIDSplit() {
        return randomUUID().replaceAll("-", "");
    }

    /**
     * 将驼峰风格替换为下划线风格
     */
    public static String camelhumpToUnderline(String str) {
        Matcher matcher = PATTERN.matcher(str);
        StringBuilder builder = new StringBuilder(str);
        for (int i = 0; matcher.find(); i++) {
            builder.replace(matcher.start() + i, matcher.end() + i, "_" + matcher.group().toLowerCase());
        }
        if (builder.charAt(0) == '_') {
            builder.deleteCharAt(0);
        }
        return builder.toString();
    }

    /**
     * 将下划线风格替换为驼峰风格
     *
     * @param str
     * @return
     * @Author: ${author}
     * @createDate 2015年12月29日
     * @updateDate
     */
    public static String underlineToCamelhump(String str) {
        Matcher matcher = PATTERN.matcher(str);
        StringBuilder builder = new StringBuilder(str);
        for (int i = 0; matcher.find(); i++) {
            builder.replace(matcher.start() - i, matcher.end() - i, matcher.group().substring(1).toUpperCase());
        }
        if (Character.isUpperCase(builder.charAt(0))) {
            builder.replace(0, 1, String.valueOf(Character.toLowerCase(builder.charAt(0))));
        }
        return builder.toString();
    }

    /**
     * 指定位数模糊字符串
     *
     * @param str        原始字符串
     * @param startIndex 开始索引
     * @param len        模糊长度
     * @param vague      替换字符
     * @return 返回对应位置替换为对应字符的字符串
     * @Author: ${author}
     * @createDate 2016年2月23日
     * @updateDate
     */
    public static String getVague(String str, int startIndex, int len, String vague) {
        if (str.length() < startIndex + len) {
            return str;
        }
        StringBuffer sb = new StringBuffer();
        sb.append(str.substring(0, startIndex));
        for (int i = 0; i < len; i++) {
            sb.append(vague);
        }
        sb.append(str.substring(startIndex + len));
        return sb.toString();
    }

    /**
     * 手机号脱敏
     *
     * @param mobile
     * @return
     * @Author: ${author}
     * @createDate 2016年5月17日
     * @updateDate
     */
    public static String getMobileVague(String mobile) {
        return getVague(mobile, 3, 4, "*");
    }

    /**
     * 身份证号脱敏
     *
     * @param certId
     * @return
     * @Author: ${author}
     * @createDate 2016年5月17日
     * @updateDate
     */
    public static String getCertIdVague(String certId) {
        return getVague(certId, 4, 10, "*");
    }

    /**
     * 银行卡脱敏
     *
     * @return
     * @Author: ${author}
     * @createDate 2016年5月17日
     * @updateDate
     */
    public static String getcardNoVague(String cardNo) {
        if (isNotEmpty(cardNo)) {
            return getVague(cardNo, 0, cardNo.length() - 4, "*");
        }
        return "";
    }

    /**
     * 产生随机字符串
     *
     * @param length
     * @return
     */
    public static final String randomNumber(int length) {
        if (length < 1) {
            return null;
        }
        if (randGen == null) {
            randGen = new Random();
            numbersAndLetters = ("0123456789").toCharArray();
        }
        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[randGen.nextInt(9)];
        }
        return new String(randBuffer);
    }

    public static boolean isNotNull(String str) {
        return !isNull(str);
    }

    /**
     * 空字符串，null,"null"都为true
     *
     * @param str
     * @return
     * @Author: ${author}
     * @createDate 2018年1月26日
     * @updateDate
     */
    public static boolean isNull(String str) {
        if (StringUtils.isBlank(str) || "null".equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * 移除字符串中的特殊字符 / ${symbol_escape} + =
     *
     * @param str
     * @return
     * @Author: ${author}
     * @createDate 2016年5月27日
     * @updateDate
     */
    public static String getReplaceStr(String str) {
        if (isNotEmpty(str)) {
            return str.replaceAll("/", "").replaceAll("${symbol_escape}${symbol_escape}${symbol_escape}${symbol_escape}", "").replaceAll("${symbol_escape}${symbol_escape}+", "").replaceAll("=", "");
        }
        return str;
    }

    /**
     * 校验手机号
     *
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEXP_MOBILE, mobile);
    }

    /**
     * 校验邮箱
     *
     * @param email
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEXP_EMAIL, email);
    }

    /**
     * 校验汉字
     *
     * @param chinese
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isChinese(String chinese) {
        return Pattern.matches(REGEXP_CHINESE_1, chinese);
    }

    /**
     * 校验URL
     *
     * @param url
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUrl(String url) {
        return Pattern.matches(REGEXP_URL_3, url);
    }

    /**
     * 校验IP地址
     *
     * @param ipAddr
     * @return
     */
    public static boolean isIPAddr(String ipAddr) {
        return Pattern.matches(REGEXP_IP_ADDR, ipAddr);
    }

    /**
     * 获取字符串最后一位
     *
     * @param str
     * @return
     */
    public static String getLastIndexStr(String str) {
        return str.substring(str.length() - 1, str.length());

    }

    /**
     * url Decoder 编码
     *
     * @param str
     * @return
     */
    public static String urlDecoder(String str) {
        try {
            return URLDecoder.decode(str, DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * url Encoder 编码
     *
     * @param str
     * @return
     */
    public static String urlEncoder(String str) {
        try {
            return URLEncoder.encode(str, DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String subStringOfLength(String body, int len) {

        if (body.length() > len) {
            return body.substring(0, len);
        } else {
            return body;
        }
    }
}
