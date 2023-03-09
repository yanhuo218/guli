package com.yanhuo.serviceedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanhuo.serviceedu.entity.EduChapter;
import com.yanhuo.serviceedu.entity.EduVideo;
import com.yanhuo.serviceedu.entity.chapter.ChapterVo;
import com.yanhuo.serviceedu.entity.chapter.VideoVo;
import com.yanhuo.serviceedu.mapper.EduChapterMapper;
import com.yanhuo.serviceedu.service.EduChapterService;
import com.yanhuo.serviceedu.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author yanhuo
 * @since 2023-02-16
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public List<ChapterVo> getChapterVideo(String courseId) {
        QueryWrapper<EduChapter> ChapterWrapper = new QueryWrapper<>();
        ChapterWrapper.eq("course_id", courseId);
        List<EduChapter> eduChapters = baseMapper.selectList(ChapterWrapper);

        QueryWrapper<EduVideo> VideoWrapper = new QueryWrapper<>();
        VideoWrapper.eq("course_id", courseId);
        List<EduVideo> eduVideos = eduVideoService.list(VideoWrapper);


        ArrayList<ChapterVo> chapterVos = new ArrayList<>();

        for (EduChapter chapter : eduChapters) {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);
            String id = chapter.getId();
            ArrayList<VideoVo> videoVos = new ArrayList<>();
            for (EduVideo eduVideo : eduVideos) {
                VideoVo videoVo = new VideoVo();
                BeanUtils.copyProperties(eduVideo, videoVo);
                if (Objects.equals(eduVideo.getChapterId(), id)) {
                    videoVos.add(videoVo);
                }
            }
            chapterVo.setChildren(videoVos);
            chapterVos.add(chapterVo);
        }

        return chapterVos;
    }

    @Override
    public boolean deleteChapter(String id) {
        QueryWrapper<EduVideo> VideoWrapper = new QueryWrapper<>();
        VideoWrapper.eq("chapter_id", id);
        int count = eduVideoService.count(VideoWrapper);
        if (count == 0) {
            baseMapper.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeChapterCourseId(String courseId) {
        QueryWrapper<EduChapter> chapterWrapper = new QueryWrapper<>();
        chapterWrapper.eq("course_id", courseId);
        return baseMapper.delete(chapterWrapper) != 0;
    }
}
