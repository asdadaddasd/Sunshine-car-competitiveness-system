package com.bd2001;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;

public class CtripHttpClientUtil {

    private static String URL = "https://sec-m.ctrip.com/restapi/soa2/11281/QueryProductListV3.json";

    /**
     * 伪装携程接送机的消息头
     *
     * @param url
     * @return
     */
    private static HttpPost getHttpPost(String url) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Host", "sec-m.ctrip.com");
        httpPost.setHeader("Connection", "keep-alive");
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("x-ctrip-pageid", "214251");
        httpPost.setHeader("User-Agent",
                "Mozilla/5.0 (Linux; Android 8.1.0; MI 6X Build/OPM1.171019.011; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/62.0.3202.84 Mobile Safari/537.36_eb64__Ctrip_CtripWireless_7.15.2");
        httpPost.setHeader("Referer",
                "https://m.ctrip.com/webapp/carch/chf/list?listparams=eyJiaXp0eXBlIjozMiwicHR0eXBlIjoxOCwibG9jdHlwZSI6MSwiZHB0Ijp7ImN0aWQiOjEsImN0bm0iOiLkuK3lm70iLCJjaWQiOjIsImNubSI6IuS4iua1tyIsImFkZHIiOiLngavovabnq5kiLCJhZGRyZHRsIjoi5LiK5rW35biC6Z2Z5a6J5Yy65LiK5rW35biCLemdmeWuieWMui3np6PpmbXot68zMDPlj7ciLCJsbmciOjEyMS40NTU2MzQwMywibGF0IjozMS4yNDk2NzY0OX0sImFyciI6eyJjdGlkIjoxLCJjdG5tIjoi5Lit5Zu9IiwiY2lkIjoyLCJjbm0iOiLkuIrmtbciLCJhZGRyIjoi6Jm55qGl5Zu96ZmF5py65Zy6VDEiLCJhZGRyZHRsIjoiIiwibG5nIjoxMjEuMzUzMTA2LCJsYXQiOjMxLjE5ODQ4OX0sInVkdCI6IjIwMTgtMTAtMjUgMTM6NDAiLCJjdGlkIjoxLCJjdG5tIjoi5Lit5Zu9IiwiY2lkIjoyLCJjbm0iOiLkuIrmtbciLCJsb2NubSI6IuiZueahpeWbvemZheacuuWculQxIiwibG9jY2QiOiJTSEEiLCJsb2NzdWJjZCI6MTAwMDIsImxvY3N1YnR5cGUiOjEsInBvaWFkZHIiOiLngavovabnq5kiLCJwb2lkdGxhZGRyIjoi5LiK5rW35biC6Z2Z5a6J5Yy65LiK5rW35biCLemdmeWuieWMui3np6PpmbXot68zMDPlj7ciLCJwb2lsYXQiOjMxLjI0OTY3NjQ5LCJwb2lsbmciOjEyMS40NTU2MzQwM30%3D&from_native_page=1");
        httpPost.setHeader("Accept-Encoding", "gzip, deflate");
        httpPost.setHeader("Accept-Language", "zh-CN,en-US;q=0.9");
        httpPost.setHeader("X-Requested-With", "ctrip.android.view");
        httpPost.setHeader("Content-Typ", "application/json");
        return httpPost;
    }

    /**
     * 获得设置爬虫的消息体，通过分析发现部分值是固定的
     *
     * @param pttype   17:接机 18:送机
     * @param udt      时间
     * @param cid      城市id 送机的时候可以不传
     * @param locnm    机场名称
     * @param loccd    机场三字码
     * @param locsubcd 机场编码
     * @param poiaddr  地址
     * @param poilat   经度
     * @param poilng   维度
     * @return
     */
    private static JSONObject getHttpBody(int pttype, String udt, int cid, String locnm, String loccd, int locsubcd,
                                          String poiaddr, String poilat, String poilng) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pttype", pttype);
        jsonObject.put("loctype", 1);
        jsonObject.put("udt", udt);
        if (cid > 0) {
            jsonObject.put("cid", cid);
        }
        jsonObject.put("locnm", locnm);
        jsonObject.put("loccd", loccd);
        jsonObject.put("locsubcd", locsubcd);
        jsonObject.put("poiaddr", poiaddr);
        jsonObject.put("poilat", poilat);
        jsonObject.put("poilng", poilng);
        jsonObject.put("sf", "app");
        jsonObject.put("chtype", 7);
        jsonObject.put("head", new HashMap());
        jsonObject.put("wlver", "7152.1024");
        return jsonObject;
    }

    /**
     * 接送机发送请求
     *
     * @param pttype   17接机 18送机
     * @param udt      用车时间
     * @param jobParam 任务参数
     * @return
     */
    public static String connetCtrip(int pttype, String udt, CtripParamInfo jobParam) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String content = null;
        HttpPost httpPost = getHttpPost(URL);
        JSONObject httpBody = getHttpBody(pttype, udt, jobParam.getCityId(), jobParam.getAirportName(),
                jobParam.getLoccd(), jobParam.getLocsubcd(), jobParam.getAddress(), jobParam.getLatitude(), jobParam.getLongitude());
        try {
            if (httpBody != null) {
                httpPost.setEntity(new StringEntity(httpBody.toString()));
            }
            response = httpclient.execute(httpPost);
            content = EntityUtils.toString(response.getEntity());
            return content;
        } catch (Exception e) {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e1) {
                    //logger.error("爬取携程" + (pttype == 17 ? "接机" : "送机") + "失败!");
                    e1.printStackTrace();
                }
            }
        }
        return content;
    }
}
