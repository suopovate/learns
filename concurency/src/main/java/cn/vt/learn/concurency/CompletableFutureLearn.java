package cn.vt.learn.concurency;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.text.csv.CsvReadConfig;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;

import java.io.File;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class CompletableFutureLearn {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 喝茶步骤
        // take tea leaf -> hot water -> take bowl -> put tea leaf to bowl  -> put hot water to bowl -> wait 30 minutes -> drink
        // take tea leaf -> take bowl -> put tea leaf to bowl
        // hot water 5 minutes -> put hot water to bowl
        // String arrow = " -> ";
        // // 放置茶叶
        // CompletableFuture<String> leafToBowl = CompletableFuture.supplyAsync(() -> "take tea leaf " + Thread.currentThread().getId()).thenApply(s -> s + arrow + "take bowl " + Thread.currentThread().getId()).thenApply(s -> s + arrow + "put tea leaf to bowl " + Thread.currentThread().getId());
        // // 烧热水 -> 加水
        // CompletableFuture<String> hotWater = CompletableFuture.supplyAsync(() -> {
        //     Console.log("正在烧水...");
        //     ThreadUtil.sleep(500);
        //     return "hot water 5 minutes " + Thread.currentThread().getId();
        // });
        // Console.log(
        //         CompletableFuture.allOf(leafToBowl, hotWater).thenApply(unused -> leafToBowl.join() + arrow + "put hot water to bowl " + Thread.currentThread().getId()).join()
        // );

        // readCsv();
        compareCityCode();
    }

    public static void forkJoin() {
        ForkJoinTask.adapt(() -> {
            Console.log("");
        });
    }

    public static void readCsv() {
        AtomicInteger i = new AtomicInteger();
        Console.log(
                CsvUtil.getReader().read(new File("/Users/vate/Desktop/执行结果1.txt")).getRows().stream()
                        .map(strings ->
                                new HashMap<String, Object>() {{
                                    put("id", strings.get(0));
                                    put("count", JSONUtil.parseArray(strings.get(5).replace("\\", "")).size());
                                }}
                        ).sorted(Comparator.comparingInt(o -> Integer.parseInt(String.valueOf(((HashMap) o).get("count")))).reversed())
                        .map(hashMap -> StrUtil.format("卡券id: {}, count: {} \n", i.incrementAndGet(), hashMap.get("id"), hashMap.get("count")))
                        .collect(Collectors.joining())
        );
        // Console.log(FileUtil.readUtf8Lines().stream().filter(s -> !s.contains("city_id")).map(s -> s.split(",")[5]).map(s -> JSONUtil.parseArray(s).size()).max(Integer::compareTo));
    }

    public static void compareCityCode() {
        String standardCityCode = "/Users/vate/softcache/workspace_java/mines/learns/concurency/src/main/java/cn/vt/learn/concurency/region.txt";
        String regions = "/Users/vate/Downloads/2022-01-14-14-25-54_EXPORT_CSV_4548471_111_0.csv";
        Map<String, String> standardCityMap = FileUtil.readUtf8Lines(standardCityCode).stream().filter(StrUtil::isNotEmpty).map(s -> s.replaceAll("\t", " ").split(" +")).collect(Collectors.toMap(strings -> strings[0], strings -> strings[1]));
        CsvReadConfig csvReadConfig = new CsvReadConfig();
        csvReadConfig.setContainsHeader(true);
        Map<String, String> regionsMap = CsvUtil.getReader().read(new File(regions)).getRows().stream().filter(strings -> StrUtil.isNotBlank(strings.get(12))).collect(Collectors.toMap(row -> row.get(12), row -> row.get(1)));

        AtomicInteger invalidCount = new AtomicInteger();
        regionsMap.entrySet().stream().forEach(kv -> {
            String standName = standardCityMap.get(kv.getKey());
            if (StrUtil.isBlank(standName) || !standName.contains(kv.getValue())) {
                invalidCount.getAndIncrement();
                Console.log("类型:{} - 城市code匹配失败 - 公司侧: {} - 标准:{}",
                        StrUtil.isBlank(standName) ? "标准code不存在" : "城市名称不匹配",
                        kv, standardCityMap.get(kv.getKey()));
            }
        });
        Console.log("总数:{},匹配失败数:{}", regionsMap.size(), invalidCount.get());
    }

}
