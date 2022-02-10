package cn.vt.es;

import cn.hutool.core.date.DateField;
import cn.hutool.core.util.RandomUtil;
import org.apache.http.HttpHost;
import org.apache.tools.ant.taskdefs.Echo;
import org.elasticsearch.action.DocWriteRequest.OpType;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EsCli {

    public static RestHighLevelClient getClient() {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );
        return client;
    }

    public static void createOrder(RestHighLevelClient client) throws IOException {
        HashMap<String, Object> order = new HashMap<String, Object>() {{
            put("orderId", RandomUtil.randomString(20));
            put("name", RandomUtil.randomString(10));
            put("payTime", RandomUtil.randomDate(null, DateField.HOUR, -24, 24));
        }};
        client.index(
                new IndexRequest("orders").create(true).id("1").source(order).opType(OpType.CREATE),
                RequestOptions.DEFAULT
        );
    }

    public static void makeData(RestHighLevelClient client, int n) throws IOException {
        for (int i = 0; i < n; i++) {
            createOrder(client);
        }
    }

    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = getClient();
        makeData(client, 1000);
    }
}
