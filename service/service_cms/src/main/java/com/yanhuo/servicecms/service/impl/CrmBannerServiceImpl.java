package com.yanhuo.servicecms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanhuo.servicecms.entity.CrmBanner;
import com.yanhuo.servicecms.mapper.CrmBannerMapper;
import com.yanhuo.servicecms.service.CrmBannerService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author com/yanhuo
 * @since 2023-02-27
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Override
    public boolean updateBannerById(CrmBanner banner) {
        return false;
    }

    @Override
    public boolean removeBannerById(String id) {
        return false;
    }

    @Override
    public List<CrmBanner> selectIndexList() {
        return baseMapper.selectList(null);
    }
}
