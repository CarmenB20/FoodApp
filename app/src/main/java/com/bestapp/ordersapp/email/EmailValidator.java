package com.bestapp.ordersapp.email;

import org.springframework.context.annotation.Configuration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Configuration
public class EmailValidator {
    private static final String EMAIL_PATTERN = "([a-zA-Z0-9_.-]+)@([a-zA-Z]+)([\\.])([a-zA-Z]+)";
    private static Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    public EmailValidator(){
        pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
    }

    public boolean isValid(String email){
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
