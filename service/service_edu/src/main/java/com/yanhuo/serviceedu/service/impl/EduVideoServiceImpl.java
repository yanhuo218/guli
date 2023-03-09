package com.yanhuo.serviceedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanhuo.commonutils.R;
import com.yanhuo.serviceedu.client.VideoClient;
import com.yanhuo.serviceedu.entity.EduVideo;
import com.yanhuo.serviceedu.mapper.EduVideoMapper;
import com.yanhuo.serviceedu.service.EduVideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author yanhuo
 * @since 2023-02-16
 */
@Slf4j
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VideoClient videoClient;

    @Override
    public boolean removeVideoCourseId(String courseId) {
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        queryWrapper.select("video_source_id");
        List<EduVideo> videoList = baseMapper.selectList(queryWrapper);
        List<String> videoSourceIdList = new ArrayList<>();
        for (int i = 0; i < videoList.size(); i++) {
            EduVideo video = videoList.get(i);
            String videoSourceId = video.getVideoSourceId();
            if (!StringUtils.isEmpty(videoSourceId)) {
                videoSourceIdList.add(videoSourceId);
            }
        }
        if (videoSourceIdList.size() > 0) {
            videoClient.removeVideoList(videoSourceIdList);
        }
        QueryWrapper<EduVideo> videoWrapper = new QueryWrapper<>();
        videoWrapper.eq("course_id", courseId);
        return baseMapper.delete(videoWrapper) != 0;
    }

    @Override
    public boolean removeVideoById(String id) {
        EduVideo eduVideo = baseMapper.selectById(id);
        String courseId = eduVideo.getVideoSourceId();
        R r = videoClient.removeVideo(courseId);
        String message = r.getMessage();
        baseMapper.deleteById(id);
        return Objects.equals(message, "成功");
    }
}
