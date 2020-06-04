package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dao.User;
import service.impl.EmailService;
import util.BCryptPasswordEncryption;
import util.Constants;
import util.IPasswordEncryption;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;

@Controller
public class ResetPasswordController implements Constants {

        @Autowired
        private EmailService emailService;
        
        IPasswordEncryption passwordEncryption = new BCryptPasswordEncryption();

        public User runSQL(String query) {

                Connection c = null;
                Statement s = null;

                try {
                        c = DriverManager.getConnection(Constants.DatabaseURL, Constants.DatabaseUserName, Constants.DatabasePassword);
                        s = c.createStatement();
                        ResultSet rs = s.executeQuery(query);
                        while (rs.next()) {

                                User user = new User();
                                user.setId(rs.getInt("userID"));
                                user.setFirstName(rs.getString("firstName"));
                                user.setLastName(rs.getString("lastName"));
                                user.setEmailId(rs.getString("email"));

                                System.out.println(user);
                                return user;
                        }

                } catch (Exception e) {

                        e.printStackTrace();

                } finally {

                        // step 6: close the connection

                        if (null != s)  {
                                try {
                                        s.close();
                                } catch (SQLException e) {
                                        e.printStackTrace();
                                }
                        }  if (null != c)  {
                                try {
                                        c.close();
                                } catch (SQLException e) {
                                        e.printStackTrace();
                                }
                        }
                }
                return null;
        }

        public void writeSQL(String query) {

                Connection c = null;
                Statement s = null;

                try {
                        c = DriverManager.getConnection(Constants.DatabaseURL, Constants.DatabaseUserName, Constants.DatabasePassword);
                        s = c.createStatement();
                        s.executeUpdate(query);

                } catch (Exception e) {

                        e.printStackTrace();

                } finally {

                        // step 6: close the connection

                        if (null != s)  {
                                try {
                                        s.close();
                                } catch (SQLException e) {
                                        e.printStackTrace();
                                }
                        }  if (null != c)  {
                                try {
                                        c.close();
                                } catch (SQLException e) {
                                        e.printStackTrace();
                                }
                        }
                }
        }

        @PostMapping("/resetPassword")

        public String PostReset(@RequestParam("email") String email, HttpServletRequest request, Model theModel) {

                // look up user in database by email

                User user = null;
                try {
                        user = runSQL("select * from UserContactInfo where email="+"'"+email+"'");

                } catch (Exception e) {
                        e.printStackTrace();
                }

                if(user == null) {

                        theModel.addAttribute("message", "We didn't find an account for that e-mail address.");

                }else {

                        theModel.addAttribute("message", "Reset password email has been sent.");

                        String appUrl = request.getScheme() + "://" + request.getServerName();

                        // Email message

                        SimpleMailMessage resetPasswordEmail = new SimpleMailMessage();

                        resetPasswordEmail.setFrom("CopyCatMeSupport@gmail.com");

                        resetPasswordEmail.setTo(user.getEmailId());

                        resetPasswordEmail.setSubject("Password Reset Request");

                        resetPasswordEmail.setText("To reset your password, click the link below:\n" + appUrl + ":8080/newPassword/"+user.getEmailId());

                        emailService.sendEmail(resetPasswordEmail);
                }
                return "success";
        }
        @GetMapping("/resetPassword")
        public String GetReset(){

                return "resetPassword";
        }
        @GetMapping("/newPassword/{param}")
        public String GetNew(){

                return "newPassword";
        }

        @PostMapping("/newPassword/{param}")
        public String PostNew(@PathVariable("param") String email, @RequestParam("password") String password, Model theModel) {
        	
        		User user = null;
        		user = runSQL("select * from UserContactInfo where email="+"'"+email+"'");

                writeSQL("UPDATE User SET password = '" + passwordEncryption.encryptPassword(password) +"' WHERE id = '"+ user.getId()+"'");

                theModel.addAttribute("message", "Password has been reset.");
                return "successfulReset.html";
        }

}
