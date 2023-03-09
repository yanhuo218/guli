package com.yanhuo.serviceedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yanhuo.serviceedu.entity.EduChapter;
import com.yanhuo.serviceedu.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author yanhuo
 * @since 2023-02-16
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideo(String courseId);

    boolean deleteChapter(String id);

    boolean removeChapterCourseId(String courseId);
}
