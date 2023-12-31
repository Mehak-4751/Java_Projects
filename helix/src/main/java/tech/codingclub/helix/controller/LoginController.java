package tech.codingclub.helix.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tech.codingclub.helix.database.GenericDB;
import tech.codingclub.helix.entity.LoginResponse;
import tech.codingclub.helix.entity.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * User: rishabh
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

    private static Logger logger = Logger.getLogger(LoginController.class);

    @RequestMapping(method = RequestMethod.GET, value = "/helloworld")
    public String getQuiz(ModelMap modelMap, HttpServletResponse response, HttpServletRequest request) {
        return "hello";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/admin")
    public String loginByAdmin(ModelMap modelMap, HttpServletResponse response, HttpServletRequest request) {
        return "adminlogin";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user")
    public String loginByUser(ModelMap modelMap, HttpServletResponse response, HttpServletRequest request) {
        return "userlogin";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/welcome")
    public String welcomeLogin(ModelMap modelMap, HttpServletResponse response, HttpServletRequest request) {
        return "welcomelogin";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/welcome")
    public
    @ResponseBody
    LoginResponse handleWelcome(@RequestBody Member member, HttpServletRequest request, HttpServletResponse response) {
        List<Member> x= (List<Member>) new GenericDB<Member>().getRows(tech.codingclub.helix.tables.Member.MEMBER,Member.class,tech.codingclub.helix.tables.Member.MEMBER.EMAIL.eq(member.email).and(tech.codingclub.helix.tables.Member.MEMBER.PASSWORD.eq(member.password)),1 );
        if(x!=null && x.size()>0){
            Member memberTemp=x.get(0);
            memberTemp.role="cm";
            ControllerUtils.setUserSession(request,memberTemp);
            return new LoginResponse(member.id, true,"Logined Successfully");
        }else {
            return new LoginResponse(null,false,"Wrong Combination");
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/handle")
    public
    @ResponseBody
    String handleEncrypt(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) {
        return "ok";
    }
}