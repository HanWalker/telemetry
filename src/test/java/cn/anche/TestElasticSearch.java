package cn.anche;

import org.apache.commons.codec.digest.DigestUtils;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;

import java.net.InetAddress;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class TestElasticSearch {
    @Test
    public void test1() throws Exception {
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(
                new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
        XContentBuilder builder = XContentFactory.jsonBuilder().startObject()
                .field("id", 1)
                .field("title", "ElasticSearch是搜索服务器。")
                .field("content", "它能够达到实时搜索，稳定，可靠，快速，安装使用方便。")
                .endObject();
        //创建索引、创建文档类型、设置唯一主键。同时创建文档
        client.prepareIndex("blog2", "article", "2").setSource(builder).get();//执行动作
        //关闭资源
        client.close();
    }

    @Test
    public void queryId() throws Exception {
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(
                new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
        GetResponse getResponse = client.prepareGet("blog2", "article", "2").get();
        String sourceAsString = getResponse.getSourceAsString();
        System.out.println(sourceAsString);
    }

    @Test
    public void test2() {
        String decrypt = "{\"flag\":true,\"operate\":\"addUser\",\"userInfos\":[{\"innerCode\":\"c0817c9a05ff41b4a5e8bc04c77dc70a\",\"account\":\"16947218@qq.com\",\"fullName\":\"秦海鑫\",\"userStatus\":\"1\",\"leaderFlag\":\"2\",\"userRankCode\":\"0402\",\"userRank\":\"县处级副职\",\"userTypeCode\":\"\",\"sex\":\"1\",\"position\":\"副主任（主持工作）\",\"majorPosition\":\"0\",\"positionCode\":\"0403\",\"md5Pwd\":\"\",\"userPwd\":\"BHAfrtVLS10u62bQlyHv3zpVck6+jUbgElJNqmYrqzgAGkfTromXQGBiy6lo25vL0aM/h2ND+g9yL/XAKD0YVyr/8vKVRMcwPs+MHWBAGEz4XNrx2OB/52bh4/rUJstKNbUJUEGyJIDmj3VS\",\"office\":\"910\",\"mobile\":\"13730633934\",\"telephone\":\"028-80589001\",\"email\":\"16947218@sthjt.sc.gov.cn\",\"regionCode\":\"51000000000000000000\",\"regionName\":\"四川省生态环境厅\",\"userType\":\"\",\"secretType\":\"2\",\"userDeptId\":\"ce44cefdd77f4f17a69957f213dd710d\",\"userDeptName\":\"办公室\",\"sortNo\":510010200,\"hasEmail\":\"Y\",\"userOrgId\":\"510000              \",\"userOrgName\":\"四川省生态环境厅\",\"deptId\":\"ce44cefdd77f4f17a69957f213dd710d\",\"returnId\":\"d43d7405ee5c453d8caff9de4b0227d0\"}]}";
        JSONObject maps = (JSONObject) JSON.parse(decrypt);
        System.out.println(maps);
        if (maps != null) {
            // 组织机构
            // 接收用户信息
            JSONArray deptInfos = maps.getJSONArray("deptInfos");
            JSONArray userinfo = maps.getJSONArray("userInfos");
            String s=DigestUtils.md5Hex("123456");
            System.out.println(s);
            if (userinfo != null) {
               // System.out.println("解密后获取用户消息" + userinfo);
            }
        }
    }
}