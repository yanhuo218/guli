package com.yanhuo.cos.controller;

import com.yanhuo.commonutils.R;
import com.yanhuo.cos.entity.VideoInfoVo;
import com.yanhuo.cos.service.CosService;
import com.yanhuo.servicebase.exceptionhandler.GuliException;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;

import static com.yanhuo.cos.utils.ConstantPropertiesUtil.DEFAULT_URL;
import static com.yanhuo.cos.utils.MultipartFileToFileUtils.multipartFileToFile;

@CrossOrigin //跨域
@RestController
@RequestMapping("/edu_cos/file")
public class FileUploadController {
    @Autowired
    private CosService cosService;

    @PostMapping("upload")
    public R upload(@RequestParam("file") MultipartFile file) {
        try {
            String uuid = UUID.randomUUID().toString().replaceAll("_", "");
            String date = new DateTime().toString("yyyy/MM/dd");
            String key = "AVATAR/"+date + "/" + uuid + file.getOriginalFilename();
            String Url = DEFAULT_URL + key;
            boolean update = cosService.updateFileAvatar(multipartFileToFile(file),key);
            return update?R.ok().message("文件上传成功").data("url", Url):R.error();
        } catch (Exception e) {
            throw new GuliException(20001,"错误");
        }
    }

    @PostMapping("uploadVideo")
    public R uploadVideo(@RequestParam("file") MultipartFile file) {
        try {
            String filename = file.getOriginalFilename();
            InputStream inputStream = file.getInputStream();
            String fileUrl = "service\\service_cos\\src\\main\\resources\\Videos\\" + filename;
            File file1 = new File(fileUrl);
            OutputStream outputStream = Files.newOutputStream(file1.toPath());
            byte[] byteStr = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(byteStr)) > 0) {
                outputStream.write(byteStr, 0, len);
            }
            VideoInfoVo videoInfo = cosService.UploadFileVideo(fileUrl);
            inputStream.close();
            outputStream.flush();
            outputStream.close();
            file1.delete();
            return R.ok().data("videoInfo", videoInfo);
        } catch (Exception e) {
            throw new GuliException(20001, "保存失败" + e);
        }
    }

    @DeleteMapping("deleteVideo/{id}")
    public R DeleteVideo(@PathVariable String id) {
        boolean delete = cosService.deleteVideo(id);
        return delete ? R.ok() : R.error();
    }

    @DeleteMapping("deleteListVideo")
    public R removeVideoList(@RequestParam("sourceIdList") List<String> sourceIdList) {
        boolean r = cosService.removeVideoList(sourceIdList);
        return r ? R.ok() : R.error();
    }
}
