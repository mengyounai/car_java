package com.gasaiyuno.car.util;



import com.gasaiyuno.car.constants.ReturnCode;
import com.gasaiyuno.car.exception.ServiceException;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class PasswordUtil {

    //aes解密
    public static String passWordConvert(String convert) {
        String passWord;
        try {
            passWord = AesUtil.decryptAES(convert);
        } catch (Exception e) {
            throw new ServiceException(ReturnCode.check_error, "解密失败");
        }
        return passWord.trim();
    }

    public static void main(String[] args) {
        try {
      System.out.println(AesUtil.encryptAES("abc123456"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String passWordSha1(String userName, String password) {
        return DigestUtils.sha1Hex(userName + "{" + password + "}");
    }

    //随机6位密码
    public static String randPass() {
        StringBuilder val = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            // 输出字母还是数字
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            if ("char".equalsIgnoreCase(charOrNum)) {
                // 97小写 65大写
                val.append((char) (97 + random.nextInt(26)));
            } else {
                val.append(random.nextInt(10));
            }
        }
        return val.toString();
    }

    public static boolean isNotStringPwd(String password) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < password.length(); i++) {
            int A = password.charAt(i);
            if (A >= 48 && A <= 57) {// 数字
                map.put("数字", "数字");
            } else if (A >= 65 && A <= 90) {// 大写
                map.put("大写", "大写");
            } else if (A >= 97 && A <= 122) {// 小写
                map.put("小写", "小写");
            } else {
                map.put("特殊", "特殊");
            }
        }
        Set<String> sets = map.keySet();
        int pwdSize = sets.size();// 密码字符种类数
        int pwdLength = password.length();// 密码长度
        return !(pwdSize >= 2 && pwdLength >= 10);// 弱密码
    }

}
