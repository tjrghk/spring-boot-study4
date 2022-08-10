package hello.springmvc.basic.request;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RequestBodyStringController {
    
    @PostMapping("/request-body-String-v1")
    public void requestBodtString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        
        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-String-v2")
    public void requestBodtStringV2(InputStream inputStream, Writer writer) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        
        writer.write("ok");
    }

    @PostMapping("/request-body-String-v3")
    public HttpEntity<String> requestBodtStringV3(RequestEntity<String> httpEntity) throws IOException {
        String messageBody = httpEntity.getBody();

        log.info("messageBody={}", messageBody);

        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @PostMapping("/request-body-String-v4")
    @ResponseBody
    public String requestBodtStringV4(@RequestBody String messageBody) throws IOException {
        log.info("messageBody={}", messageBody);

        return "ok";
    }
}
