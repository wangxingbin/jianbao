package utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 作者：赵自强
 * 时间：2016/9/6 11:05
 * 简述：
 */
public class PixelUtil {
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    private static Integer ID_OPERATE_SUCCESS = 0;
    private static Integer ID_OPERATE_FAILUER_SERVERERROR_1 = 11;
    private static Integer ID_OPERATE_FAILUER_SERVERERROR_5 = 15;
    private static Integer ID_OPERATE_FAILUER_SERVERERROR_7 = 17;
    private static Integer ID_OPERATE_FAILUER_SERVERERROR_99 = 99;

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     *
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int get(Context context) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) fontScale;
    }

    public static int getScreenWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    public static int getScreenHeight(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    /**
     * 给金额显示添加千分位","
     *
     * @param val 金额
     */
    public static String parseMoney(Object val) {
        String pattern = "##,###,##0.00";
        if (val == null || val.equals(""))
            return "";
        String valStr = val + "";
        DecimalFormat df = new DecimalFormat(pattern);
        valStr = df.format(new BigDecimal(valStr));
        return subZeroAndDot(valStr);
    }

    /**
     * 获取当前时间后几月
     *
     * @param currentDate 当前时间
     * @param period      间隔月数
     * @param dateFormat  时间格式 "yyyy-MM-dd HH:mm:ss"
     */
    public static String getMonthAfterStr(Object currentDate, Integer period,
                                          String dateFormat) {
        return getDateAfterStr(currentDate, period, "M", dateFormat);
    }

    /**
     * 获取当前时间后段时间
     *
     * @param currentDate 当前时间
     * @param period      后段时间天数
     * @param type        "Y"->年; "Mth"->月; "D"->天;"H"->时;"Min"->分;"S"->秒
     * @param dateFormat  时间格式 "yyyy-MM-dd HH:mm:ss"
     * @return String "String(yyyy-MM-dd HH:mm:ss)"->正常时间串;"11"->"操作失败：服务器故障(1)"
     * //输入时间类型不正确,非String/Date; ""15"->"操作失败：服务器故障(5)"" //输入时间格式不正确;
     * "99"->"操作失败：服务器故障(99)" //未知错误(初始化值)
     */
    public static String getDateAfterStr(Object currentDate, Integer period,
                                         String type, String dateFormat) {
        // System.err.println("Function getDateAfterStr is invoked...");
        // System.err.println("currentDate : "+currentDate);
        // System.err.println("period : "+period);
        String dateAfter; // 初始化值
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Calendar now = Calendar.getInstance();
        if (currentDate instanceof Date) { // currentDate 为 Date 类型
            now.setTime((Date) currentDate);
            // dateAfter = "0";
        } else if (currentDate instanceof String) { // currentDate 为 String 类型
            try {
                now.setTime(sdf.parse((String) currentDate));
                // dateAfter = "0";
            } catch (ParseException e) {
                dateAfter = ID_OPERATE_FAILUER_SERVERERROR_5.toString(); // 输入时间格式不正确
                System.err
                        .println("getDateAfterStr :-2 -> Date Format Error...");
                return dateAfter;
            }
        } else { // 格式错误
            dateAfter = ID_OPERATE_FAILUER_SERVERERROR_1.toString(); // 输入时间类型不正确,非String/Date
            System.err.println("getDateAfterStr :-1 -> Date Type Error...");
            return dateAfter;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now.getTime());
        if ("Y".equals(type)) {
            calendar.add(Calendar.YEAR, period);
        } else if ("M".equals(type)) {
            calendar.add(Calendar.MONTH, period);
        } else if ("D".equals(type)) {
            calendar.add(Calendar.DATE, period);
        } else if ("H".equals(type)) {
            calendar.add(Calendar.HOUR_OF_DAY, period);
        } else if ("m".equals(type)) {
            calendar.add(Calendar.MINUTE, period);
        } else if ("S".equals(type)) {
            calendar.add(Calendar.SECOND, period);
        }

        dateAfter = sdf.format(calendar.getTime());

        return dateAfter;
    }

    /**
     * String Number Utils
     */
    /**
     * 字符串数字的加法
     *
     * @param num1 数字1
     * @param num2 数字2
     * @return
     */
    public static String stringAdd(String num1, String num2) {
        BigDecimal vnum1 = new BigDecimal(num1);
        BigDecimal vnum2 = new BigDecimal(num2);
        return (vnum1.add(vnum2)).toString();
    }

    /**
     * 字符串数字的减法
     *
     * @param num1 数字1
     * @param num2 数字2
     * @return
     */
    public static String stringSubstract(String num1, String num2) {
        BigDecimal vnum1 = new BigDecimal(num1);
        BigDecimal vnum2 = new BigDecimal(num2);
        return (vnum1.subtract(vnum2)).toString();
    }

    /**
     * 字符串数字的除法
     *
     * @param num1   数字1
     * @param num2   数字2
     * @param params 不定参数 params[0]->保留位数(默认为2) params[1]->保留位数方式(默认为 向下取整)
     *               params[2]->是否去零(默认为 自动去小数点后的0)
     * @return
     */
    public static String stringDivide(String num1, String num2,
                                      Object... params) {
        BigDecimal vnum2 = new BigDecimal(num2);
        if (num2 == null || vnum2.equals(new BigDecimal("0")))
            return "0";
        BigDecimal vnum1 = new BigDecimal(num1);

        // 处理 params参数
        int bitLen = 2;
        int roundType = BigDecimal.ROUND_DOWN;
        boolean delZero = true;

        int pLen = params.length; // 参数长度
        if (params != null && pLen > 0) {

            if (pLen > 0) {
                try {
                    bitLen = (Integer) params[0];
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out
                            .println(" TypeCastException occurred when casting params[0] to Integer.");
                }
            }
            if (pLen > 1) {
                try {
                    roundType = (Integer) params[1];
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out
                            .println(" TypeCastException occurred when casting params[1] to Integer.");
                }
            }
            if (pLen > 2) {
                try {
                    delZero = (Boolean) params[2];
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out
                            .println(" TypeCastException occurred when casting params[2] to Boolean.");
                }
            }
        }
        String res = vnum1.divide(vnum2, bitLen, roundType).toString();
        // 去零处理
        if (delZero) {
            res = subZeroAndDot(res);
        }
        return res;
    }

    /**
     * 字符串数字的乘法
     *
     * @param num1 数字1
     * @param num2 数字2
     * @return
     */
    public static String stringMultiple(String num1, String num2) {
        BigDecimal vnum1 = new BigDecimal(num1);
        BigDecimal vnum2 = new BigDecimal(num2);
        return (vnum1.multiply(vnum2)).toString();
    }

    /**
     * 小数的 取小数点和零 如 12.30 -> 12.3 | 23.00 -> 23
     */
    public static String subZeroAndDot(String s) {
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");// 去掉多余的0
            s = s.replaceAll("[.]$", "");// 如最后一位是.则去掉
        }
        return s;
    }

}
