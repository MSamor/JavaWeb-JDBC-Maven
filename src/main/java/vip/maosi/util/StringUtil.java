package vip.maosi.util;
public class StringUtil {

    /**
     * 下划线转驼峰命名
     * @param dbColumnName 列名
     * @return 驼峰命名
     */
    public static String toJavaAttributeName(String dbColumnName) {
        char ch[] = dbColumnName.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (i == 0) {
                ch[i] = Character.toLowerCase(ch[i]);
            }
            if ((i + 1) < ch.length) {
                if (ch[i] == '_') {
                    ch[i + 1] = Character.toUpperCase(ch[i + 1]);
                } else {
                    ch[i + 1] = Character.toLowerCase(ch[i + 1]);
                }
            }
        }
        return new String(ch).replace("_", "");
    }

    /**
     * 返回实体类的set方法
     * @param attributeName 实体属性
     * @return set方法
     */
    public static String asserSetMethodName(String attributeName) {
        StringBuffer sb = new StringBuffer(16);
        char[] ch = attributeName.toCharArray();
        ch[0] = Character.toUpperCase(ch[0]);
        sb.append("set");
        sb.append(new String(ch));
        return sb.toString();
    }

}