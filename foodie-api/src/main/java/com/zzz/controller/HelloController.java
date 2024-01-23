package com.zzz.controller;

import com.zzz.pojo.Carousel;
import com.zzz.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangzhongzhen wrote on 2024/1/23
 * @version 1.0
 * @description:
 */
@RestController
public class HelloController {

    @Autowired
    private CarouselService carouselService;

    @GetMapping("/hello")
    public String hello() {
        return carouselService.getById("c-10011").toString();
    }

}
