package cn.vt;

import cn.hutool.core.date.StopWatch;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import org.openjdk.jol.info.ClassLayout;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author vate
 */
public class ZeroCopyTest {

    public String s_i = "实例字符串";
    public static String s_s = "静态字符串";
    public final Integer i_f_s = new Integer("1");
    public Integer i_s = new Integer("1");

    public static void main(String[] args) throws IOException {
        Console.log(new ZeroCopyTest().s_i);
        Console.log(s_s);
//        trans42();
        Console.log(ClassLayout.parseInstance(
                new ArrayList<String>(){
                    {
                        add("1");
                        add("2");
                        add("3");
                        add("3");
                        add("3");
                        add("3");
                        add("3");
                        add("3");
                        add("3");
                    }
                }.toArray()
        ).toPrintable());
    }

    public static void trans42() throws IOException {
        StopWatch stopWatch = new StopWatch();
        File sFile = new File("/Users/v-data/softcache/test.txt");
        File dFile = new File(sFile.getAbsoluteFile() + ".back");
        // 使用通道传输文件
        stopWatch.start("使用通道传输文件");
        FileChannel sChannel = new FileInputStream(sFile).getChannel();
        sChannel.transferTo(0, sFile.length(), new FileOutputStream(dFile).getChannel());
        stopWatch.stop();
        // 不使用通道复制文件
        stopWatch.start("不使用通道传输文件");
        FileUtil.copy(sFile, dFile, true);
        stopWatch.stop();

        // 使用通道传输文件
        stopWatch.start("使用通道传输文件");
        FileChannel sChannel2 = new FileInputStream(sFile).getChannel();
        sChannel2.transferTo(0, sFile.length(), new FileOutputStream(dFile).getChannel());
        stopWatch.stop();
        // 不使用通道复制文件
        stopWatch.start("不使用通道传输文件");
        FileUtil.copy(sFile, dFile, true);
        stopWatch.stop();
        // 不使用通道复制文件
        stopWatch.start("不使用通道传输文件");
        FileUtil.copy(sFile, dFile, true);
        stopWatch.stop();
        Console.log(stopWatch.prettyPrint());
    }

}
