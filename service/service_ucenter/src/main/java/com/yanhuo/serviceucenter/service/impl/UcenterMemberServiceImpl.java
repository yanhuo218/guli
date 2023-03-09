package com.yanhuo.serviceucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yanhuo.servicebase.exceptionhandler.GuliException;
import com.yanhuo.serviceucenter.entity.UcenterMember;
import com.yanhuo.serviceucenter.entity.vo.LoginVo;
import com.yanhuo.serviceucenter.mapper.UcenterMemberMapper;
import com.yanhuo.serviceucenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanhuo.serviceucenter.util.MD5;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author com/yanhuo
 * @since 2023-03-08
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Override
    public String login(LoginVo loginVo) {
        String mail = loginVo.getMail();
        String password = loginVo.getPassword();
        if (StringUtils.isEmpty(mail) || StringUtils.isEmpty(password)){
            throw new GuliException(20001,"账号格式错误");
        }
        UcenterMember user = baseMapper.selectOne(new QueryWrapper<UcenterMember>().eq("mail", mail));
        if(null == user) {
            throw new GuliException(20001,"账号不存在");
        }
        if (!MD5.encrypt(password).equals(user.getPassword())){
            throw new GuliException(20001,"密码错误");
        }
        if(user.getIsDisabled()) {
            throw new GuliException(20001,"账号已封禁");
        }
        return null;
    }
}
