package com.yanhuo.serviceedu.controller;


import com.yanhuo.commonutils.R;
import com.yanhuo.serviceedu.entity.EduVideo;
import com.yanhuo.serviceedu.service.EduVideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author yanhuo
 * @since 2023-02-16
 */
@Slf4j
@CrossOrigin//跨域
@RestController
@RequestMapping("/service_edu/video")
public class EduVideoController {
    @Autowired
    private EduVideoService videoService;

    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo) {
        videoService.save(eduVideo);
        return R.ok();
    }

    @GetMapping("{id}")
    public R getByIdVideo(@PathVariable String id) {
        EduVideo byId = videoService.getById(id);
        return byId != null ? R.ok().data("video", byId) : R.error();
    }

    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id) {
        boolean removeById = videoService.removeVideoById(id);
        return removeById ? R.ok() : R.error();
    }

    @PostMapping("updateVideo")
    public R update(@RequestBody EduVideo video) {
        boolean update = videoService.updateById(video);
        return update ? R.ok() : R.error();
    }
}

