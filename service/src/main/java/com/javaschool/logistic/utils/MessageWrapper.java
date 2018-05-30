package com.javaschool.logistic.utils;


import org.springframework.stereotype.Component;

@Component
public class MessageWrapper {

    public String getMessage(String username, String email, String password){
        return "<div style=\"font-size: 18px; color: darkblue\">\n" +
                "    <H1>LOGIWEB</H1>\n" +
                "    <p>Dear <i><strong>" + username + "</strong></i>, you have been added to the LOGIWEB system.</p>\n" +
                "    <p>Now you can log in to the link:\n" +
                "    <a href=\"http://206.189.24.66:8080/login\">logiweb.com/login</a></p>\n" +
                "    <p>Your login information:</p>\n" +
                "    <p>Email: <i><strong>" + email + "</strong></i></p>\n" +
                "    <p>Password: <i><strong>" + password + "</strong></i></p>\n" +
                "    <p><strong>Do not tell anyone your password!</strong></p>\n" +
                "    <p>Thanks, have a nice day.</p>\n" +
                "</div>";
    }

}
