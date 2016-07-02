package com.mcshivpuri.shivpurimunicipalcouncil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Rohit shukla on 29-06-2016.
 * this class helps in validating email and Name.
 */
public class ValidateAll {

    public boolean isValidName(String name) {
        if (name != null && name.length() > 2 && name.length() < 30) {

            String NAME_PATTERN = "^[A-Z|a-z][A-Za-z]+[A-Za-z\\s]*|[\\w]*";
            Pattern pattern = Pattern.compile(NAME_PATTERN);
            Matcher matcher = pattern.matcher(name);
            return matcher.matches();
        } else {
            return false;
        }
    }

    public boolean isValidEmail(String email) {

        String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


}
