package com.yanhuo.serviceedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yanhuo.serviceedu.entity.EduVideo;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author yanhuo
 * @since 2023-02-16
 */
public interface EduVideoService extends IService<EduVideo> {

    boolean removeVideoCourseId(String courseId);

    boolean removeVideoById(String id);
}
