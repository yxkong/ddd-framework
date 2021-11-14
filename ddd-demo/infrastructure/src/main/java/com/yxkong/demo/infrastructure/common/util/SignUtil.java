package com.yxkong.demo.infrastructure.common.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.*;
import java.util.Map.Entry;

/**
 * 签名工具类
 * @author ducongcong
 */
public class SignUtil {


    /**
     * 通用api验签密钥
     */
    public static final String OPEN_SALT = "M3A2OrXBF3ZcOFx0oCnZQ";

    /**
     * 加密解密用户id的秘钥
     */
    private static final String USER_SALT = "pVrG+nGlxU4amKi39E6bTA==";
    
    /**
     * MD5加密用户手机号用的盐值
     */
    public static final String MD5_SALT = "NsArzbOcYie4XMW5iGrkA";

    /**
     * 根据id 获取加密后的字符串
     *
     * @param customerId
     * @return
     */
    public static String getStrId(Long customerId) {
        return getEncode(String.valueOf(customerId));
    }

    /**
     * 根据id 获取加密后的字符串
     *
     * @param id
     * @param secretKey
     * @return
     * @author ducongcong
     * @createDate 2017年7月18日
     * @updateDate
     */
    public static String getStrId(Long id, String secretKey) {
        return getEncode(String.valueOf(id), secretKey);
    }

    /**
     * 根据用户id返回Long类型的id
     *
     * @param userId
     * @return
     */
    public static Long getLongId(String userId) {
        return getLongId(userId, USER_SALT);
    }

    public static Long getLongId(String strId, String secretKey) {
        String strLongId = getDecode(strId, secretKey);
        if (strLongId != null && StringUtils.isNumeric(strLongId)) {
            return Long.valueOf(strLongId);
        }
        return null;
    }

    /**
     * AES加密字符串
     *
     * @param str       要加密的字符串
     * @param secretKey 盐值
     * @return 返回加密值
     * @author ducongcong
     * @createDate 2017年7月18日
     * @updateDate
     */
    public static String getEncode(String str, String secretKey) {
        if (StringUtils.isNotNull(str) && StringUtils.isNotNull(secretKey)) {
            return EncryptUtil.getInstance().AESencode(str, secretKey);
        }
        return null;
    }

    /**
     * AES加密字符串
     *
     * @param str
     * @return
     * @author ducongcong
     * @createDate 2016年1月8日
     * @updateDate
     */
    public static String getEncode(String str) {
        return getEncode(str, USER_SALT);
    }

    /**
     * AES解密字符串
     *
     * @param userId
     * @return
     * @author ducongcong
     * @createDate 2016年1月8日
     * @updateDate
     */
    public static String getDecode(String userId) {
        return getDecode(userId, USER_SALT);
    }

    /**
     * AES解密字符串
     *
     * @param userId
     * @return
     * @author ducongcong
     * @createDate 2016年1月8日
     * @updateDate
     */
    public static String getDecode(String userId, String secretKey) {
        return EncryptUtil.getInstance().AESdecode(userId, secretKey);
    }

    /**
     * 参数加签
     *
     * @param salt 加签验签秘钥
     * @param kv   以键值对组成的参数
     *             <p>如：userId=xxx,mobile=156</p>
     *             <p>输入时为 verifySign(sign,"userId","xxx","mobile","156")</p>
     * @return 正常返回加签后的字符串
     * <p>kv不为双数,返回null</p>
     */
    public static String sign(String salt, String... kv) {
        LinkedHashMap<String, String> paramMap = getParamLinkedMap(kv);
        if (paramMap != null) {
            return sign(paramMap, salt);
        }
        return null;
    }

    /**
     * 验签功能
     *
     * @param salt 加签验签秘钥
     * @param sign url 传递过来的 加密后的字符串
     * @param kv   以键值对组成的参数
     *             <p>如：userId=xxx,mobile=156</p>
     *             <p>输入时为 verifySign(sign,"userId","xxx","mobile","156")</p>
     * @return <p>kv不为双数,返回false</p>
     * <p>计算出的sign和传递过来的sign一致， 返回true</p>
     * <p>计算出的sign和传递过来的sign不一致，返回false</p>
     */
    public static boolean verifySign(String salt, String sign, String... kv) {
        LinkedHashMap<String, String> paramMap = SignUtil.getParamLinkedMap(kv);
        if (paramMap != null) {
            String signVal = SignUtil.sign(paramMap, salt);
            if (signVal != null && signVal.equals(sign)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /**
     * md5加签
     * 会将 !去除，
     *
     * @param paraMap   按约定顺序传入的参数和值
     * @param secretKey 秘钥
     * @return
     */
    public static String sign(LinkedHashMap<String, String> paraMap, String secretKey) {
        if (paraMap == null || paraMap.size() <= 0) {
            return null;
        }
        StringBuffer strBuf = new StringBuffer();
        for (Entry<String, String> ent : paraMap.entrySet()) {
            strBuf.append(ent.getKey()).append(":");
            if (ent.getValue() == null) {
                strBuf.append("");
            } else {
                strBuf.append(ent.getValue());
            }
        }
        String str = EncryptUtil.getInstance().MD5(strBuf.toString().replaceAll("!", ""), secretKey);
        return StringUtils.getReplaceStr(str);
    }

    /**
     * 验签
     *
     * @param paraMap   参数Map
     * @param secretKey 秘钥
     * @param sign      传递过来的加签值
     * @return
     */
    public static boolean verifySign(LinkedHashMap<String, String> paraMap, String secretKey, String sign) {
        String signVal = sign(paraMap, secretKey);
        if (signVal != null && signVal.equals(sign)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 将参数封装到 LinkedHashMap中
     *
     * @param str key value,key value 的数组
     * @return
     */
    public static LinkedHashMap<String, String> getParamLinkedMap(String... str) {
        if (str != null && (str.length % 2 == 0)) {
            LinkedHashMap<String, String> linkedMap = new LinkedHashMap<String, String>();
            for (int i = 0; i < str.length / 2; i++) {
                linkedMap.put(str[i * 2], str[i * 2 + 1]);
            }
            return linkedMap;
        }
        return null;
    }

    /**
     * @param sArray 签名参数组
     * @return
     * @desc: 去掉空值与签名参数sign后的新签名参数
     * @author:hbt
     */
    private static Map<String, String> paraFilter(Map<String, String> sArray) {

        Map<String, String> result = new HashMap<String, String>();
        if (sArray == null || sArray.size() <= 0) {
            return result;
        }
        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || "".equals(value) || "sign".equalsIgnoreCase(key)) {
                continue;
            }
            result.put(key, value);
        }
        return result;
    }

    /**
     * 把所有参数按key排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param params 参数
     * @return 拼接后字符串
     * @author:hbt
     */
    private static String createLinkString(Map<String, String> params) {
        Map<String, String> newParms = paraFilter(params);
        List<String> keys = new ArrayList<String>(newParms.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = newParms.get(key);
            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr;
    }

    /**
     * @param paraMap   要签名的参数
     * @param secretKey 密钥
     * @return
     * @desc: 获取排序后的签名字符串
     * @author:hbt
     */
    public static String getSign(Map<String, String> paraMap, String secretKey) {
        String prestr = createLinkString(paraMap);
        String str = EncryptUtil.getInstance().MD5(prestr.replaceAll("!", ""), secretKey);
        str = (str == null ? "" : str);
        return StringUtils.getReplaceStr(str);
    }

    /**
     * @param params    URL参数
     * @param secretKey 密钥
     * @return
     * @desc: 将参数自动排序后验证签名
     * @author:hbt
     */
    public static boolean veryfy(Map<String, String> params, String secretKey) {
        //获取签名结果
        String sign = "";
        if (params != null && params.get("sign") != null) {
            sign = params.get("sign");
        }
        String newSign = getSign(params, secretKey);
        //获得签名验证结果
        return sign.equals(newSign);
    }

    /**
     * 获取用户加密的md5密码
     *
     * @param pwd
     * @return
     * @author ducongcong
     * @createDate 2016年5月27日
     * @updateDate
     */
    public static String getMd5Pwd(String pwd) {
        String str = EncryptUtil.getInstance().MD5(pwd, SignUtil.OPEN_SALT);
        return str.replaceAll("/", "").replaceAll("\\\\", "").replaceAll("\\+", "").replaceAll("=", "");
    }
    
    /**
     * 将手机号MD5加密
     * @param mobile
     * @return
     * @author songchuanwu
     * @date 2018年12月6日下午3:18:58
     * @备注 有盐值
     */
    public static String getMd5Mobile(String mobile) {
    	return md5(mobile, MD5_SALT);
    }
    
    /**
     * 自带md5与mysql函数md5一致
     * @param mobile
     * @param salt
     * @return
     * @author songchuanwu
     * @date 2018年12月6日下午3:16:55
     * @备注
     */
    public static String md5(String md5Str, String salt) {
    	if(StringUtils.isNull(md5Str)) {
    		return null;
    	}
    	return DigestUtils.md5Hex(md5Str + salt);
    }
    
    /**
     * 自带md5与mysql函数md5一致
     * @param mobile
     * @return
     * @author songchuanwu
     * @date 2019年4月4日下午5:16:37
     * @备注
     */
    public static String md5(String md5Str) {
    	if(StringUtils.isNull(md5Str)) {
    		return null;
    	}
    	return DigestUtils.md5Hex(md5Str);
    }
    
    public static void main(String[] args) {
//        public static String getSign(Map<String, String> paraMap, String secretKey) {
//            String prestr = createLinkString(paraMap);
//            String str = EncryptUtil.getInstance().MD5(prestr.replaceAll("!", ""), secretKey);
//            str = (str == null ? "" : str);
//            return StringUtils.getReplaceStr(str);
//        }
//    	Map<String, String> paraMap = new HashMap<String, String>();
////    	18572875222
////    	paraMap.put("customerId", "1148010844");
//    	paraMap.put("proId", "123");
//    	paraMap.put("mobile", "13269983236");
//    	paraMap.put("_t", "123");

//    	String ssss = SignUtil.getSign(paraMap, UserConstants.USER_SALT);
        Long ssss = getLongId("B51DB43892B0631DAE1CEEA68D81D8A67A3E80AC24C72F40714E8783C56D5965");
    	System.out.println(ssss);
	}
}