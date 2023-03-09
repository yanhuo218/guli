package com.yanhuo.servicecms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yanhuo.commonutils.R;
import com.yanhuo.servicecms.entity.CrmBanner;
import com.yanhuo.servicecms.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author com/yanhuo
 * @since 2023-02-27
 */
@CrossOrigin
@RestController
@RequestMapping("/edu_cms/banner")
public class CrmBannerController {
    @Autowired
    private CrmBannerService bannerService;

    @GetMapping("pageBanner/{page}/{limit}")
    public R pageBanner(@PathVariable Long limit, @PathVariable Long page) {
        Page<CrmBanner> bannerPage = new Page<>(page,limit);
        bannerService.page(bannerPage, null);
        return R.ok().data("items", bannerPage.getRecords()).data("total", bannerPage.getTotal());
    }

    @PostMapping("save")
    public R addBanner(@RequestBody CrmBanner banner) {
        if(banner!=null) {
            boolean save = bannerService.save(banner);
            return save ? R.ok() : R.error();
        }
        return R.error();
    }

    @GetMapping("getById/{id}")
    public R BannerById(@PathVariable String id) {
        CrmBanner byId = bannerService.getById(id);
        return byId != null ? R.ok().data("Banner", byId) : R.error();
    }

    @PutMapping("update")
    public R updateBanner(@RequestBody CrmBanner banner) {
        boolean update = bannerService.updateById(banner);
        return update ? R.ok() : R.error();
    }

    @DeleteMapping("deleteBanner/{id}")
    public R deleteBanner(@PathVariable String id) {
        boolean delete = bannerService.removeById(id);
        return delete ? R.ok() : R.error();
    }
}

