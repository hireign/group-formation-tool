package CSCI5308.GroupFormationTool.Security;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Database.DefaultDatabaseConfiguration;
import CSCI5308.GroupFormationTool.Email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;

@Controller
public class ResetPasswordController implements IPasswordEncryption{

        @Autowired
        private EmailService emailService;

        IPasswordEncryption passwordEncryption = new IPasswordEncryption() {
                @Override
                public String encryptPassword(String rawPassword) {
                        return null;
                }

                @Override
                public boolean matches(String rawPassword, String encryptedPassword) {
                        return false;
                }
        };

        DefaultDatabaseConfiguration defaultdb = new DefaultDatabaseConfiguration();
        public User runSQL(String query) {

                Connection c = null;
                Statement s = null;

                try {
                        c = DriverManager.getConnection(defaultdb.getDatabaseURL(),defaultdb.getDatabaseUserName(), defaultdb.getDatabasePassword());
                        s = c.createStatement();
                        ResultSet rs = s.executeQuery(query);
                        while (rs.next()) {

                                User user = new User();
                                user.setId(rs.getInt("userID"));
                                user.setFirstName(rs.getString("firstName"));
                                user.setLastName(rs.getString("lastName"));
                                user.setEmail(rs.getString("email"));

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
                        c = DriverManager.getConnection(defaultdb.getDatabaseURL(), defaultdb.getDatabaseUserName(), defaultdb.getDatabasePassword());
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

                        resetPasswordEmail.setTo(user.getEmail());

                        resetPasswordEmail.setSubject("Password Reset Request");

                        resetPasswordEmail.setText("To reset your password, click the link below:\n" + appUrl + ":8080/newPassword/"+user.getEmail());

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

        @Override
        public String encryptPassword(String rawPassword) {
                return null;
        }

        @Override
        public boolean matches(String rawPassword, String encryptedPassword) {
                return false;
        }
}
