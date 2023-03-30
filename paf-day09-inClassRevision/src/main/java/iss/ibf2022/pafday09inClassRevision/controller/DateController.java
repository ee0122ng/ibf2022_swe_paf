package iss.ibf2022.pafday09inClassRevision.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path={""})
public class DateController {

    @PostMapping(path={"/dateToString"})
    public void convertToDate(@RequestBody MultiValueMap<String, String> date) throws ParseException {

        System.out.println(">>> date: " + date.getFirst("date"));
        Date d = new SimpleDateFormat("yyyy-MM-dd").parse(date.getFirst("date"));

        // sample example for java.sql.date
        // java.sql.Date sqlDate = Date.valueOf("date in string");
        
        System.out.println(">>> test: " + d.toString());
    }
    
}
