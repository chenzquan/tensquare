package com.tensquare.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class SmsUtil {
    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";
    @Autowired
    private Environment env;
}
