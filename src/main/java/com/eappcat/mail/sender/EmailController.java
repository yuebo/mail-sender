package com.eappcat.mail.sender;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.eappcat.mail.sender.entity.SystemParameter;
import com.eappcat.mail.sender.repository.SystemParameterRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
@Slf4j
public class EmailController {
    @Autowired
    private JavaMailSenderImpl javaMailSender;
    @Autowired
    private VelocityEngine velocityEngine;

    @Autowired
    private SystemParameterRepository systemParameterRepository;
    @GetMapping("/load")
    public Object loadConfig(){
        Optional<SystemParameter> systemParameter=systemParameterRepository.findById("mail");
        if(systemParameter.isPresent()){
            return JSON.parse(systemParameter.get().getConfig());
        }
        return Collections.emptyMap();
    }

    @PostMapping("/save")
    public int saveConfig(@RequestBody String data){
        Optional<SystemParameter> systemParameter=systemParameterRepository.findById("mail");
        if(systemParameter.isPresent()){
            systemParameter.get().setConfig(data);
            systemParameterRepository.save( systemParameter.get());
        }else {
            systemParameterRepository.save(new SystemParameter("mail",data));
        }
        return 1;
    }

    @PostMapping("/send")
    public int saveAndSendConfig(@RequestBody String data) throws Exception{
        this.saveConfig(data);
        JSONObject jsonObject=JSON.parseObject(data).getJSONObject("config");
        javaMailSender.setUsername(jsonObject.getString("sender"));
        javaMailSender.setPassword(jsonObject.getString("password"));


        try(BufferedReader bufferedReader=new BufferedReader(new StringReader(jsonObject.getString("file")))) {
            String line=null;
            while ((line=bufferedReader.readLine())!=null){
                String[] values=StringUtils.split(line,",");
                if(values==null||values.length<2){
                    log.error("error with line: {}",line);
                    continue;
                }
                Context context=new VelocityContext();
                context.put("column", Arrays.asList(values));
                String mail=values[0];
                if(!mail.contains("@")){
                    mail=values[0].concat("@qq.com");
                }
                StringWriter stringWriter=new StringWriter();
                velocityEngine.evaluate(context,stringWriter,"template",jsonObject.getString("template"));
                log.info("send to {} with {}", mail,stringWriter.toString());
                try {
                    sendMail(mail,context,stringWriter.toString(),jsonObject.getString("topic"),jsonObject.getString("sender"));
                }catch (Exception e){
                    log.error("error to send mail to {}",mail);
                }
            }
        }


        return 1;
    }

    private void sendMail(String concat, Context context, String s,String topic,String sender) throws Exception {
        log.info("sending mail to {} with code", concat, context.get("code"));
        MimeMessage message=javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(sender);
        helper.setTo(concat);
        helper.setSubject(topic);
        helper.setText(s,true);
        javaMailSender.send(message);
    }
}
