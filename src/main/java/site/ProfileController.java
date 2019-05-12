package site;

import POJOs.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;


@Controller
public class ProfileController {
    @RequestMapping(value="/Profile")
    public String ShowProfilePage(){

            return "/Profile/ProfilePage";
    }

    @RequestMapping(value="/Profile/EditDescription")
    public String EditDescription(Map<String, Object> model){
        model.put("editForm", new EditForm());
        return "/Profile/EditDescription";
    }

   @RequestMapping(value="/Profile/EditDescription", method=RequestMethod.POST)
    public String GetEditDescription(Map<String, Object> model, EditForm form, HttpSession session, HttpSession response) throws IOException{
        DAO dao = DAO.getInstance();
        //chagen tisUserDescription userDescription = (UserDescription)session.getAttribute("userDescription");
        UserDescription userDescription = new UserDescription();
        Address address = new Address();
        User user = (User)session.getAttribute("user");

        String birthday = form.getBirthday();
       System.out.println(birthday);
        Date date;
        //convert from string to sql.date
       if(birthday != null || !birthday.equals("")) {       //Pattern not correct?
           DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
           java.util.Date parsed = null;
           try {
               parsed = format.parse(birthday);
           } catch (ParseException e) {
               e.printStackTrace();
           }
           date = new Date(parsed.getTime());
           userDescription.setBirthday(date);
       }
       //convert image to inputsteam
       MultipartFile picture = form.getPicture();
       userDescription.setInputStream(picture.getInputStream());
      try {
           byte[] bytes = picture.getBytes();
           userDescription.setPicData(bytes);
           Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
           userDescription.setPic(blob);
       }catch(Exception c){ }


       userDescription.setcellPhone(form.getCellPhone());
       userDescription.setPhone(form.getPhone());
       userDescription.setTimeZone(form.getTimeZone());
       address.setStreet1(form.getStreet1());
       address.setStreet2(form.getStreet2());
       address.setCity(form.getCity());
       address.setState(form.getState());
       address.setZipCode(form.getZipCode());

       //add function to update in database
       dao.updateAddress(address,user);
       dao.updateUserDescription(userDescription,user);
       session.setAttribute("address",address);
       session.setAttribute("userDescription",userDescription);
       return "redirect:/Profile";
    }

    @RequestMapping(value="/image")
    public ResponseEntity<byte[]> ShowProfileImage(HttpSession session, HttpServletResponse response) throws IOException, SQLException {
        HttpHeaders headers = new HttpHeaders();
        UserDescription desc = (UserDescription)session.getAttribute("userDescription");
        byte[] pic = desc.getPicData();
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(pic,headers,HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value="/Profile/AddEducation")
    public String AddEducation(Map<String, Object> model) {
        model.put("educationHistory",new EducationHistory());
        model.put("educationForm", new EducationForm());
        return "/Profile/AddEducation";
    }

    @RequestMapping(value="/Profile/AddEducation", method=RequestMethod.POST)
    public String GetAddEducation(Map<String, Object> model, EducationForm form,HttpSession session) {
        DAO dao = DAO.getInstance();
        User user = (User)session.getAttribute("user");
        EducationHistory History = new EducationHistory();
        History.setUsersId(user.getUserId());
        History.setDegreeType(form.getDegreeType());
        History.setDegreeDiscipline(form.getDegreeDiscipline());
        History.setYearsAchieved(form.getYearsAchieved());
        dao.addEducationHistory(History,user);

        ArrayList<EducationHistory> educationHistory = (ArrayList<EducationHistory>)(session.getAttribute("educationHistory"));
        educationHistory = dao.getAllEducation(user);
        session.setAttribute("educationHistory", educationHistory);
        return "redirect:/Profile";
    }

    @RequestMapping(value="/Profile/AddWork")
    public String AddWork(Map<String, Object> model) {
        model.put("workHistory",new WorkHistory());
        model.put("workForm", new WorkForm());
        return "/Profile/AddWork";
    }

    @RequestMapping(value="/Profile/AddWork",method=RequestMethod.POST)
    public String GetAddWork(Map<String, Object> model, WorkForm form,HttpSession session) {
        DAO dao = DAO.getInstance();
        User user = (User)session.getAttribute("user");
        WorkHistory History = new WorkHistory ();
        History.setUsersId(user.getUserId());
        History.setJobTitle(form.getJobTitle());
        History.setCompanyName(form.getCompanyName());
        History.setYearsOfService(form.getYearsOfService());
        dao.addWorkHistory(History,user);

        ArrayList<WorkHistory> workHistory = (ArrayList<WorkHistory>)session.getAttribute("workHistory");
        workHistory = dao.getAllWork(user);
        session.setAttribute("workHistory", workHistory);

          return "redirect:/Profile";
    }


    @RequestMapping(value="/Profile/EditWork/{current}")
    public String editWork(@PathVariable("current") int current, Map<String, Object> model){
        DAO dao = DAO.getInstance();
        WorkHistory workHistory = dao.getWorkById(current);
        model.put("workHistory",workHistory);
        model.put("workForm", new WorkForm());
        return "/Profile/EditWork";
    }

    @RequestMapping(value="/Profile/EditWork/{current}",method=RequestMethod.POST)
    public String editWork(@PathVariable("current") int current,Map<String, Object> model,WorkForm form, HttpSession session){

        System.out.println(form.getCompanyName());
        WorkHistory work = new WorkHistory();
        work.setJobTitle(form.getJobTitle());
        work.setCompanyName(form.getCompanyName());
        work.setYearsOfService(form.getYearsOfService());

        //update in dao
        DAO dao = DAO.getInstance();
        dao.updateWork(work,current);


        ArrayList<WorkHistory> workHistory = (ArrayList<WorkHistory>)session.getAttribute("workHistory");
        workHistory = dao.getAllWork((User)session.getAttribute("user"));
        session.setAttribute("workHistory", workHistory);


        return "redirect:/Profile";
    }

    @RequestMapping(value="/Profile/DeleteWork/{current}")
    public String deleteWork(@PathVariable("current") int current, Map<String, Object> model, HttpSession session){
        DAO dao = DAO.getInstance();
        dao.deleteWork(current);

        ArrayList<WorkHistory> workHistory = (ArrayList<WorkHistory>)session.getAttribute("workHistory");
        workHistory = dao.getAllWork((User)session.getAttribute("user"));
        session.setAttribute("workHistory", workHistory);

        return "redirect:/Profile";
    }

    @RequestMapping(value="/Profile/EditEducation/{current}")
    public String editEducation(@PathVariable("current") int current, Map<String, Object> model){
        DAO dao = DAO.getInstance();
        EducationHistory educationHistory= dao.getEducationById(current);
        model.put("educationHistory",educationHistory);
        model.put("educationForm", new EducationForm());
        return "/Profile/EditEducation";
    }

    @RequestMapping(value="/Profile/EditEducation/{current}",method=RequestMethod.POST)
    public String editEducation(@PathVariable("current") int current,Map<String, Object> model,EducationForm form, HttpSession session){
        EducationHistory edu = new EducationHistory();
        edu.setDegreeType(form.getDegreeType());
        edu.setDegreeDiscipline(form.getDegreeDiscipline());
        edu.setYearsAchieved(form.getYearsAchieved());

        //update in dao
        DAO dao = DAO.getInstance();
        dao.updateEducation(edu,current);


        ArrayList<EducationHistory> educationHistory = (ArrayList<EducationHistory>)(session.getAttribute("educationHistory"));
        educationHistory = dao.getAllEducation((User)session.getAttribute("user"));
        session.setAttribute("educationHistory", educationHistory);


        return "redirect:/Profile";
    }

    @RequestMapping(value="/Profile/DeleteEducation/{current}")
    public String deleteEducation(@PathVariable("current") int current, Map<String, Object> model, HttpSession session){
        DAO dao = DAO.getInstance();
        dao.deleteEducation(current);

        ArrayList<EducationHistory> educationHistory = (ArrayList<EducationHistory>)(session.getAttribute("educationHistory"));
        educationHistory = dao.getAllEducation((User)session.getAttribute("user"));
        session.setAttribute("educationHistory", educationHistory);


        return "redirect:/Profile";

    }


}
