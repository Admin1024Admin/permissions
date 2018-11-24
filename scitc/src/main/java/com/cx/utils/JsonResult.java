package com.cx.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 用于输出json的工具类
 * @author:Teacher黄
 * @date:Created at 2018/10/25
 */
public class JsonResult {


    /**
     * 输出json字符串
     * @param response
     * @param value 转换json的对象
     */
    public static void response(HttpServletResponse response, Object value){


        try {
            // 设置类型以及编码
            response.setContentType("application/json;charset=utf-8");
            // 将集合转换成json字符串
            ObjectMapper objectMapper = new ObjectMapper();
            String valueStr = objectMapper.writeValueAsString(value);
            // 输出
            response.getWriter().write(valueStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *  带状态码以及提示的响应
     * @param response
     * @param code : 状态码
     * @param msg : 提示信息
     * @param value : 对应的值
     */
    public static void response(HttpServletResponse response, Integer code, String msg , Object value){

        /**
         *
         * {
         *     code:200,
         *     msg:success!,
         *     data:[数据]
         * }
         *
         */

        try {
            // 设置类型以及编码
            response.setContentType("application/json;charset=utf-8");
            // 组装map
            Map<String,Object> dataMap = new HashMap<>(16);
            dataMap.put("code",code);
            dataMap.put("msg",msg);
            dataMap.put("data",value);

            // 将集合转换成json字符串
            ObjectMapper objectMapper = new ObjectMapper();
            String valueStr = objectMapper.writeValueAsString(dataMap);
            // 输出
            response.getWriter().write(valueStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
