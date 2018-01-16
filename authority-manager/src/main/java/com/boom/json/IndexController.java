package com.boom.json;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 2017/12/12 下午8:44
 * @Description
 */
@Controller
public class IndexController {

    private static final Logger log = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/index")
    public String index() {
        log.info("IndexController.index()===>index page");
        return "index";
    }
}
