package com.yanhuo.serviceedu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yanhuo.commonutils.R;
import com.yanhuo.serviceedu.entity.EduTeacher;
import com.yanhuo.serviceedu.query.TeacherQuery;
import com.yanhuo.serviceedu.service.EduTeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author yanhuo
 * @since 2023-02-09
 */
@Slf4j
@CrossOrigin//跨域
@RestController
@RequestMapping("/service_edu/teacher")
public class EduTeacherController {
    @Autowired
    private EduTeacherService teacherService;

    @GetMapping("getAll")
    public R findAllTeacher() {
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("All", list);
    }

    @DeleteMapping("{id}")
    public R DeleteTeacher(@PathVariable String id) {
        boolean remove = teacherService.removeById(id);
        return remove ? R.ok() : R.error();
    }

    @GetMapping("pageTeacher/{page}/{limit}")
    public R PageList(@PathVariable Long page, @PathVariable Long limit) {
        Page<EduTeacher> pageParam = new Page<>(page, limit);
        teacherService.page(pageParam, null);
        List<EduTeacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        return R.ok().data("total", total).data("rows", records);
    }

    @PostMapping("pageCondition/{page}/{limit}")
    public R RageTeacherCondition(@PathVariable Long page, @PathVariable Long limit, @RequestBody(required = false) TeacherQuery teacherQuery) {
        Page<EduTeacher> tPage = new Page<>(page, limit);
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.ge("gmt_modified", end);
        }
        wrapper.orderByDesc("gmt_create");
        teacherService.page(tPage, wrapper);
        long total = tPage.getTotal();
        List<EduTeacher> records = tPage.getRecords();
        return R.ok().data("total", total).data("rows", records);
    }

    @PostMapping("addTeacher")
    public R AddTeacher(@RequestBody EduTeacher teacher) {
        if (teacher.getName() != null) {
            boolean save = teacherService.save(teacher);
            return save ? R.ok() : R.error();
        }
        String mass = "Name is null";
        log.info(mass);
        return R.error().message(mass);
    }

    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id) {
        if (id != null) {
            EduTeacher byId = teacherService.getById(id);
            return byId != null ? R.ok().data("teacher", byId) : R.error();
        } else {
            return R.error().message("用户不存在_或服务器故障");
        }
    }

    @PostMapping("updateTeacher")
    public R ReviseTeacher(@RequestBody EduTeacher teacher) {
        boolean update = teacherService.updateById(teacher);
        return update ? R.ok() : R.error();
    }
}

