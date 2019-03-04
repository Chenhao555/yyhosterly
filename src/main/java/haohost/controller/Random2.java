package haohost.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import haohost.Util.CodeUtil;

@Controller
@RequestMapping("/ran")
public class Random2{

	  /* 获取验证码
	     *
	     * @param response
	     * @param session
	     */
	    @RequestMapping("/getVerifyCode")
	    public void generate(HttpServletResponse response, HttpSession session) {
	    	System.out.print("我到过这里");
	        ByteArrayOutputStream output = new ByteArrayOutputStream();
	        CodeUtil codeUtil = new CodeUtil();
	        String verifyCodeValue = codeUtil.drawImg(output);
	        System.out.println(verifyCodeValue);
	        session.setAttribute("verifyCode", verifyCodeValue);
	        try {
	            ServletOutputStream out = response.getOutputStream();
	            output.writeTo(out);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}
