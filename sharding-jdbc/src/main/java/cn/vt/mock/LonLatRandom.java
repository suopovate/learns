package cn.vt.mock;

import cn.hutool.core.util.StrUtil;

import java.math.BigDecimal;

/**
 * 随机生成经纬度
 */
public class LonLatRandom {

    private static final BigDecimal BD_60 = new BigDecimal("60");

    /**
     * 在矩形内随机生成经纬度
     * @param MinLon 最小经度
     * @param MaxLon 最大经度
     * @param MinLat 最小纬度
     * @param MaxLat 最大纬度
     * @return 经度，纬度
     */
    public static String randomLonLat(double MinLon, double MaxLon, double MinLat, double MaxLat) {
        BigDecimal db = new BigDecimal(Math.random() * (MaxLon - MinLon) + MinLon);
        // 小数后6位
        String lon = db.setScale(6, BigDecimal.ROUND_HALF_UP).toString();
        db = new BigDecimal(Math.random() * (MaxLat - MinLat) + MinLat);
        String lat = db.setScale(6, BigDecimal.ROUND_HALF_UP).toString();
        return lon + "," + lat;
    }

    /**
     * 度转度分秒
     * @return
     */
    public static String degreeToDMS(String degree){
        BigDecimal bd = new BigDecimal(degree);

        // compute the minute
        BigDecimal mBd = bd.subtract(new BigDecimal(bd.longValue()));
        BigDecimal minute = mBd.multiply(BD_60);

        // compute the second
        BigDecimal sBd = minute.subtract(new BigDecimal(minute.longValue()));
        BigDecimal second = sBd.multiply(BD_60);

        return StrUtil.format("{}°{}'{}\"", bd.longValue(),minute.longValue(),second.toString());
    }

}
