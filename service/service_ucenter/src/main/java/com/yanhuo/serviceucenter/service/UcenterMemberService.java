package com.yanhuo.serviceucenter.service;

import com.yanhuo.serviceucenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yanhuo.serviceucenter.entity.vo.LoginVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author com/yanhuo
 * @since 2023-03-08
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(LoginVo loginVo);
}
