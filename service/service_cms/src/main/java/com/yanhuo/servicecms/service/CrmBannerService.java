package com.yanhuo.servicecms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yanhuo.servicecms.entity.CrmBanner;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author com/yanhuo
 * @since 2023-02-27
 */
public interface CrmBannerService extends IService<CrmBanner> {

    boolean updateBannerById(CrmBanner banner);

    boolean removeBannerById(String id);

    List<CrmBanner> selectIndexList();
}
