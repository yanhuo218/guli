package com.yanhuo.serviceedu.controller;


import com.yanhuo.commonutils.R;
import com.yanhuo.serviceedu.entity.EduChapter;
import com.yanhuo.serviceedu.entity.chapter.ChapterVo;
import com.yanhuo.serviceedu.service.EduChapterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.Thread.sleep;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author yanhuo
 * @since 2023-02-16
 */
@Slf4j
@CrossOrigin//跨域
@RestController
@RequestMapping("/service_edu/chapter")
public class EduChapterController {
    @Autowired
    private EduChapterService chapterService;

    @GetMapping("getChapterVideo/{courseId}")
    public R getChapterVideo(@PathVariable String courseId) {
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<ChapterVo> list = chapterService.getChapterVideo(courseId);
        return R.ok().data("List", list);
    }

    @PostMapping("addChapter")
    public R save(@RequestBody EduChapter eduChapter) {
        boolean save = chapterService.save(eduChapter);
        return save ? R.ok() : R.error();
    }

    @GetMapping("getChapter/{id}")
    public R getChapter(@PathVariable String id) {
        EduChapter chapter = chapterService.getById(id);
        if (chapter == null) {
            return R.error();
        }
        return R.ok().data("chapter", chapter);
    }

    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody EduChapter chapter) {
        boolean update = chapterService.updateById(chapter);
        return update ? R.ok() : R.error();
    }

    @DeleteMapping("{id}")
    public R deleteChapter(@PathVariable String id) {
        boolean delete = chapterService.deleteChapter(id);
        return delete ? R.ok() : R.error().message("请删除小节后删除");
    }
}

