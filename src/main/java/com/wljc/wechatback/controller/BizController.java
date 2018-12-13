package com.wljc.wechatback.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 功能描述：业务控制类
 *
 * @Auther: hd
 * @Date: 2018/11/26
 */
@Slf4j
@Controller
@RequestMapping("/biz")
public class BizController {


    @RequestMapping("/storeInfo")
    public String storeInfo(){
        return "storeInfo";
    }

    @RequestMapping("/doctorInfo")
    public String doctorInfo(){
        return "doctorInfo";
    }

    @RequestMapping("/employInfo")
    public String employInfo(){
        return "employInfo";
    }

    @RequestMapping("/userInfo")
    public String userInfo(){
        return "userInfo";
    }
}
