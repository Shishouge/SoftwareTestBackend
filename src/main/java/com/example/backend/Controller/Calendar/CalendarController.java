package com.example.backend.Controller.Calendar;

import com.example.backend.Service.CalendarTest.CalendarTestService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
public class CalendarController {
    @Autowired
    CalendarTestService t1;
    @RequestMapping(value = "/getCalendarTestResult",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getCalendarTestResult(){
        return t1.nextDay();
    }
}
