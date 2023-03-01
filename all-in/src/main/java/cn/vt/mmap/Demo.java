package cn.vt.mmap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class Demo {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("~/softcache/mmap/test");
        FileChannel fileChannel = new RandomAccessFile(file, "rw").getChannel();
//        fileChannel.map
    }

}
