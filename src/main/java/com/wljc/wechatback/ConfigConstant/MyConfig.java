package com.wljc.wechatback.ConfigConstant;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述： 自定义配置文件
 *
 * @Auther: hd
 * @Date: 2018/12/13
 */
@Data
@Configuration
public class MyConfig {

    @Value("${my.sysPath}")
    private String sysPath;

    @Value("${my.picPath}")
    private String picPath;

}
