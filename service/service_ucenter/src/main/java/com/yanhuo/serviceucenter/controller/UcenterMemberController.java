package com.yanhuo.serviceucenter.controller;


import com.yanhuo.commonutils.R;
import com.yanhuo.serviceucenter.entity.vo.LoginVo;
import com.yanhuo.serviceucenter.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author com/yanhuo
 * @since 2023-03-08
 */
@RestController
@RequestMapping("/service_ucenter/ucenter")
public class UcenterMemberController {
    @Autowired
    private UcenterMemberService memberService;

    public R login(@RequestBody LoginVo loginVo){
        String token = memberService.login(loginVo);
        return R.ok().data("token",token);
    }
}

