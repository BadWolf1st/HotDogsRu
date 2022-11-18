package com.example.hotdogsru.Controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Controller
public class DogsController {
    public DogsController() {
    }

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String sendIndexPage() {
        return "home.html";
    }

    @GetMapping(value = "/result", produces = MediaType.TEXT_HTML_VALUE)
    public String sendResultPage(){
        return "result.html";//TODO: Переделать файл под Template для того что бы грузить туда фотку...
    }

    //@GetMapping(value="home.html"){
    //
    //}

    @GetMapping(value = "/ErrorFile", produces = MediaType.TEXT_HTML_VALUE)
    public String sendErrorFilePage(){
        return "errorDownloadFile.html";
    }

    @GetMapping(value = "/ErrorNoFile", produces = MediaType.TEXT_HTML_VALUE)
    public String sendErrorNoFilePage(){
        return "errorNoFile.html";
    }

    @RequestMapping(value = "/uploading", method = RequestMethod.POST)
    public RedirectView provideInfo(@RequestParam("file")MultipartFile file){
        if(!file.isEmpty()){
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("-uploaded")));
                return new RedirectView("/result");
            } catch (Exception e){
                return new RedirectView("/ErrorFile");
            }
        } else {
            return new RedirectView("/ErrorNoFile");
        }
    }
}