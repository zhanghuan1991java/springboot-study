package com.didispace.web.M001_userManage;

import com.didispace.mybatis.a_user_image.A_USER_IMAGE;
import com.didispace.mybatis.a_user_image.A_USER_IMAGE_Mapper;
import lombok.extern.slf4j.Slf4j;
import oracle.sql.BLOB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Map;

@RestController
@Slf4j
public class UserImageController {

    @Autowired
    private A_USER_IMAGE_Mapper image_mapper;

    /**
     * 跳转  到图片上传页面
     * @return
     */
    @RequestMapping(value = "/upload_image_html/{id}")
    public ModelAndView upload_image_html(@PathVariable("id") String id) {
        log.info("upload_image_html : ....用户id：" + id);
        ModelAndView mv = new ModelAndView();
        //用户id
        mv.addObject("id",id);
        mv.setViewName("/thymeleaf/a_logon_manage/userImageUpload");
        return mv;
    }

    /**
     * 图片上传
     * @param file
     * @return
     */
    @RequestMapping(value = "/uploadImage",method = RequestMethod.POST)
    public ModelAndView uploadImage(@RequestParam("file") MultipartFile file , @RequestParam("user_id") String id) throws IOException {
        String fileName = file.getOriginalFilename();
        log.info("文件名称——>"+ fileName);
        log.info("用户id  ——>" + id);

        byte[] content = file.getBytes();
        A_USER_IMAGE a_user_image = new A_USER_IMAGE();
        a_user_image.setImage(content);
        a_user_image.setUser_id(id);
        image_mapper.insertUserImage(a_user_image);

        //设置提示信息
        ModelAndView mv = new ModelAndView();
        mv.addObject("retMsg", "用户["+id+"]上传图片["+fileName+"]成功!");
        mv.setViewName("/thymeleaf/a_logon_manage/userManage");

        return mv;
    }

    @RequestMapping(value = "/getImage/{id}")
    public void showImage(@PathVariable("id") String id,
                          HttpServletResponse response, HttpServletRequest request)
            throws ServletException, IOException, SQLException {

        Map map = image_mapper.selectUserImage(id);

        if (map != null && map.size() > 0) {
            BLOB blob = (BLOB) map.get("IMAGE");
            byte[] bytes = blob.getBytes(1L, (int) blob.length());

            OutputStream out = new BufferedOutputStream(response.getOutputStream());
            out.write(bytes);
            out.flush();
            out.close();
        }else {
            log.info("id:"+id + "不存在图片");
        }



    }
}
