package cn.vt.freemark;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import lombok.Builder;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;

public class Generator {

    private static String json = "{\"data\":{\"page\":0,\"pageCount\":0,\"resultNum\":0,\"results\":[{\"appointment\":\"\",\"attentCount\":0,\"businessHours\":\"\",\"cancelDay\":0,\"cancelRules\":[{\"agreeForcetg\":0,\"cancelMoneyCodex\":\"\",\"days\":0,\"hours\":0,\"returnMoneyCodex\":\"\"}],\"chargeInclude\":\"\",\"chargeNoInclude\":\"\",\"childrenNum\":0,\"cityId\":\"\",\"cityName\":\"\",\"conds\":[{\"condId\":0,\"condTitle\":\"\",\"isApiece\":0,\"isGotta\":0,\"isSync\":0,\"marketPrice\":0,\"salePrice\":0,\"settlementPrice\":0,\"startNum\":0,\"treeId\":0,\"unit\":\"\"}],\"content\":\"\",\"creditTypeSet\":\"\",\"custField\":\"\",\"custFieldEx\":\"\",\"distributionDesc\":\"\",\"expressPrice\":0,\"goodsunit\":\"\",\"img\":\"\",\"imgs\":\"\",\"importantNote\":\"\",\"isChangeask\":0,\"isConds\":0,\"isConfirm\":0,\"isExpress\":0,\"isFree\":0,\"isOnlinepay\":0,\"isPackage\":0,\"isSingle\":0,\"isTimeslot\":0,\"isTop\":0,\"limitType\":0,\"marketPrice\":0,\"maxNum\":0,\"oldmanNum\":0,\"orderDesc\":\"\",\"orderPolicy\":\"\",\"packageProducts\":[{\"infoIdNode\":0,\"infoNameNode\":\"\",\"isDateNode\":0,\"numNode\":0,\"treeIdNode\":0,\"viewAddressNode\":\"\",\"viewIdNode\":\"\",\"viewNameNode\":\"\"}],\"payMinute\":0,\"peopleNum\":0,\"peoples\":\"\",\"posterImg\":\"\",\"priceEndDate\":\"\",\"priceStartDate\":\"\",\"prodDesc\":\"\",\"productName\":\"\",\"productNo\":0,\"pubVers\":\"\",\"purchaseLimit\":{\"limitCardDay\":0,\"limitCardGoods\":0,\"limitCardNum\":0,\"limitPhoneDay\":0,\"limitPhoneGoods\":0,\"limitPhoneNum\":0,\"limitTimeType\":0},\"refundNote\":\"\",\"saleEndDate\":\"\",\"salePrice\":0,\"saleStartDate\":\"\",\"settlementPrice\":0,\"specs\":[{\"specName\":\"\",\"specValues\":[]}],\"startDate\":\"\",\"startDay\":0,\"startMaxDay\":0,\"startMinute\":0,\"startNum\":0,\"startTime\":\"\",\"state\":0,\"themes\":[],\"ticketCount\":0,\"ticketTypeName\":\"\",\"ticketcodeType\":0,\"treeId\":0,\"userNote\":\"\",\"validityCon\":\"\",\"validityType\":0,\"verifyIntype\":0,\"verifyType\":0,\"viewAddress\":\"\",\"viewId\":\"\",\"viewLatitude\":\"\",\"viewLongitude\":\"\",\"viewName\":\"\",\"views\":[]}],\"size\":0,\"sizeAll\":0,\"startIndex\":0},\"msg\":\"\",\"state\":0}";
    private static String className = "productDetailRequestVo";
    private static String classDesc = "";
    private static String author = "曾碹";

    public static void main(String[] args) throws IOException, TemplateException {
//        Configuration cfg = buildConfiguration();
//        List<Map<String, Object>> models = buildModel();
//        models.forEach(model -> {
//            try {
//                Template temp = cfg.getTemplate("json2Java.ftlh");
//                Writer out = new OutputStreamWriter(System.out);
//                temp.process(model, out);
//            } catch (Throwable t) {
//                Console.error(t);
//            }
//        });
    }

    private static List<Map<String, Object>> buildModel() {
        return jsonObject2Model(JSON.parseObject(json), className, classDesc);
    }

    private static List<Map<String, Object>> jsonObject2Model(JSONObject jsonObject, String className, String classDesc) {
        List<Map<String, Object>> models = new ArrayList<>();
        Map<String, Object> model = new HashMap<>();
        models.add(model);
        model.put("className", StrUtil.upperFirst(className));
        model.put("classDesc", classDesc);
        model.put("author", author);
        // 基于json数据获取模型
        model.put(
            "fields",
            jsonObject
                .entrySet()
                .stream()
                .map(kv -> {
                    Object value = kv.getValue();
                    if (value instanceof JSONObject || value instanceof JSONArray) {
                        boolean isObject = value instanceof JSONObject;
                        if (value instanceof JSONArray && ((JSONArray) value).isEmpty()) {
                            return new HashMap() {{
                                put("name", kv.getKey());
                                put("type", "List");
                            }};
                        }
                        models.addAll(jsonObject2Model(
                            isObject ? ((JSONObject) value) : ((JSONArray) value).getJSONObject(0),
                            kv.getKey(),
                            null
                        ));
                        return new HashMap() {{
                            put("name", kv.getKey());
                            put(
                                "type",
                                isObject ? StrUtil.upperFirst(kv.getKey()) : StrUtil.format(
                                    "List<{}>",
                                    StrUtil.upperFirst(kv.getKey())
                                )
                            );
                        }};
                    }
                    return new HashMap() {{
                        put("name", kv.getKey());
                        put("type", value.getClass().getSimpleName());
                    }};
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList())
        );
        return models;
    }

    private static Configuration buildConfiguration() throws IOException {
        // Create your Configuration instance, and specify if up to what FreeMarker
        // version (here 2.3.22) do you want to apply the fixes that are not 100%
        // backward-compatible. See the Configuration JavaDoc for details.
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);

        // Specify the source where the template files come from. Here I set a
        // plain directory for it, but non-file-system sources are possible too:
        cfg.setDirectoryForTemplateLoading(new File(
            "/Users/vate/softcache/workspace_java/mines/learns/all-in/src/main/java/cn/vt/freemark"));

        // Set the preferred charset template files are stored in. UTF-8 is
        // a good choice in most applications:
        cfg.setDefaultEncoding("UTF-8");

        // Sets how errors will appear.
        // During web page *development* TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        return cfg;
    }

}
