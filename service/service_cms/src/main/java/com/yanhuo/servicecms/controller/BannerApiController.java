package com.yanhuo.servicecms.controller;

import com.yanhuo.commonutils.R;
import com.yanhuo.servicecms.entity.CrmBanner;
import com.yanhuo.servicecms.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/edu_cms/banner")
@CrossOrigin //跨域
public class BannerApiController {

    @Autowired
    private CrmBannerService bannerService;

    @Cacheable(value = "banner", key = "'selectIndexList'")
    @GetMapping("getAllBanner")
    public R index() {
        List<CrmBanner> list = bannerService.selectIndexList();
        return R.ok().data("bannerList", list);
    }

}
