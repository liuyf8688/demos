package com.liuyf.demos.spring.web.mvc.controllers;

import java.util.Map.Entry;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.fasterxml.jackson.annotation.JsonView;
import com.liuyf.demos.spring.web.mvc.model.User;

@Controller
public class ProgrammerController {

    @RequestMapping("/programmers")
    public String get(Model model) {
        
        model.addAttribute("username", "liuyf");
        System.out.println("==========================");
        return "programmers";
    }
    
    @ModelAttribute
    public void beforeGet(Model model) {
        System.out.println("++++++++++++++++++++++++++");
    }
    
    @ModelAttribute
    public void beforeGet2(Model model) {
        System.out.println("RRRRRRRRRRRRRRRRRRRRRRRRRR");
    }
    
    // http://localhost:8080/spring-framework-web-mvc-demos/mvc/matrix/variables/11;matrix1=111;matrix2=222
    @RequestMapping("/matrix/variables/{matrix}")
    public void anotherMatrix(
            @PathVariable Integer matrix,
            @MatrixVariable(name="matrix1", pathVar="matrix") Integer matrix1,
            @MatrixVariable(name="matrix2", pathVar="matrix") Integer matrix2) {
        System.out.println(matrix + " ====== " + matrix1 + " ====== " + matrix2);
    }
    
    @RequestMapping(value = "/http/options")
    public void processOptions() {
    }
    
    
    @RequestMapping(value = "/binding/result")
    public String processWithBindingResult(@Valid @ModelAttribute("user") User user, BindingResult result) {
        System.out.println("1111111111111111111111111111111111");
        
//        new UserValidator().validate(user, result);
        
        System.out.println(result.hasErrors());
        System.out.println(result.getErrorCount());
        
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println(error.getObjectName() + " === " + error.getCode() + " === " + error.getDefaultMessage());
            }
        }
        
        return null;
    }
    
    @RequestMapping(value = "/user/json")
    @ResponseBody
    public User userJson() {
        
        User user = getUser();
        return user;
    }
    
    @RequestMapping(value = "/user/json/view")
    @ResponseBody
    @JsonView(com.liuyf.demos.spring.web.mvc.model.User.userJsonView.class)
    public User userJsonView() {
        
        return getUser();
    }

    private static User getUser() {
        User user = new User();
        user.setName("liuyf");
        user.setPhone("13800138000");
        return user;
    }
    
    @RequestMapping(value = "/callable")
    @ResponseBody
    public Callable<User> callable() {
        
        return new Callable<User>() {

            @Override
            public User call() throws Exception {
                
                Thread.sleep(5000L);
                
                return getUser();
            }
        };
    }
    
    @RequestMapping(value = "/user/list")
    public String listUser() {
        return "user-view";
    }
    
    @RequestMapping(value = "/redirect")
    public String redirect() {
        
        return "redirect:http://www.baidu.com";
    }
    
    @RequestMapping(value = "/redirect/with/response/status")
    @ResponseStatus(code = HttpStatus.FOUND)
    public String redirectWithResponseStatus() {
        
        return "redirect:http://www.baidu.com";
    }
    
    @RequestMapping(value = "/redirect/flash/attributes", method = RequestMethod.GET)
    public String redirectWithFlashAttributesUsingGet(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        
        redirectAttributes.addFlashAttribute("liuyf", "liuyf");
        redirectAttributes.addFlashAttribute("liuyf2", "liuyf");
        redirectAttributes.addFlashAttribute("liuyf3", "liuyf");
        
        System.out.println("============= in redirectWithFlashAttributes");
        
        return "redirect:/mvc/flash/attributes";
    }
    
    @RequestMapping(value = "/redirect/flash/attributes", method = RequestMethod.POST)
    public String redirectWithFlashAttributes(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        
        redirectAttributes.addFlashAttribute("liuyf", "liuyf");
        redirectAttributes.addFlashAttribute("liuyf2", "liuyf");
        redirectAttributes.addFlashAttribute("liuyf3", "liuyf");
        
        System.out.println("============= in redirectWithFlashAttributes");
        
        return "redirect:/mvc/flash/attributes";
    }
    
    @RequestMapping(value = "/flash/attributes")
    public String flashAttributes(HttpServletRequest request) {
        
//        System.out.println(RequestContextUtils.getInputFlashMap(request));
        
        for (Entry<String, ?> input : RequestContextUtils.getInputFlashMap(request).entrySet()) {
            System.out.println(input.getKey() + " ===>>> " + input.getValue());
        }
        System.out.println("====================");
        
        //
        for (Entry<String, ?> output : RequestContextUtils.getOutputFlashMap(request).entrySet()) {
            System.out.println(output.getKey() + " ===>>> " + output.getValue());
        }
        
        return "redirect-view";
    }
}
