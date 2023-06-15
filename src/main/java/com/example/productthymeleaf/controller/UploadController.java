package com.example.productthymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;

@Controller
public class UploadController {
    @GetMapping("/upload")
    public String uploadPage() {
        return "/upload";
    }

    @PostMapping("/upload")
    public ModelAndView upload(@RequestParam MultipartFile file) {
        //MultipartFile là 1 class trong java, dùng chứa các định dạng file khác nhau
        ModelAndView modelAndView = new ModelAndView("/result");
        //phương thức lấy ra tên file
        String filename = file.getOriginalFilename();
        try {
            //FileCopyUtils là thư viện trong java, dùng để copy file sang 1 địa chỉ mới
            FileCopyUtils.copy(file.getBytes(),
                    new File("C:\\Users\\ADMIN\\Desktop\\product-thymeleaf\\src\\main\\webapp\\file\\" + filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelAndView.addObject("img", filename);
        return modelAndView;
    }
}
