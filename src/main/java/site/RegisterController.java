package site;

import POJOs.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class RegisterController {

    @RequestMapping(value="/Register", method=RequestMethod.GET)
    public String registerForm(Map<String, Object> model) {
        model.put("registerForm",new RegisterForm());
        return "/Register";
    }

    @RequestMapping(value="/Register", method=RequestMethod.POST)
    public String GetRegistrationInfo(Map<String, Object> model, RegisterForm form,HttpSession session){
        String method = form.getMethod();
        if(method.equals("GET")){
        return "/ERROR";
        }
        else{
            User user = new User();
            Address address = new Address();
            UserDescription userDescription = new UserDescription();
            user.setLogonId(form.getLogonId());
            user.setPassword(form.getPassword());
            address.setStreet1(form.getStreet1());
            address.setStreet2(form.getStreet2());
            address.setCity(form.getCity());
            address.setState(form.getState());
            address.setZipCode(form.getZipCode());

            DAO dao = DAO.getInstance();
            dao.addAddress(address);
            dao.addUser(user,address);
            dao.addUserDescription(userDescription, user);

            session.setAttribute("user",user);
            session.setAttribute("address",address);
            session.setAttribute("userDescription", userDescription);
            return "redirect:/Profile";
        }
    }

    @RequestMapping(value="/SignIn")
    public String Login(Map<String, Object> model){
        model.put("loginForm",new RegisterForm());
        return "/SignIn";
    }

    @RequestMapping(value="/SignIn", method=RequestMethod.POST)
    public String GetLoginInfo(Map<String, Object> model, RegisterForm form,HttpSession session){
        DAO dao = DAO.getInstance();
        User temp = new User();
        temp.setLogonId(form.getLogonId());
        temp.setPassword(form.getPassword());

        System.out.println(dao.verifyUser(temp));

        if(dao.verifyUser(temp) == true) {

            //GET ALL USER INFO BEFORE REDIRECTING TO PROFILEPAGE
            User user = dao.getCurrentUser(temp);
            Address address = dao.getCurrentAddress(user);
            UserDescription userDescription = dao.getCurrentDescription(user);
            ArrayList<WorkHistory> workHistory= dao.getAllWork(user);
            ArrayList<EducationHistory> educationHistory= dao.getAllEducation(user);

            session.setAttribute("user", user);
            session.setAttribute("address", address);
            session.setAttribute("userDescription", userDescription);
            session.setAttribute("workHistory", workHistory);
            session.setAttribute("educationHistory", educationHistory);


            return "redirect:/Profile";
        }
        //If not in db then return register
        return "redirect:/Register";
    }

}
