package com.example.backend.Controller.Calendar;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.backend.Entity.CalendarData;
import com.example.backend.Service.CalendarTest.CalendarTestService;
import com.example.backend.Service.CalendarTest.SystemTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
public class CalendarController {
    @Autowired
    CalendarTestService calendarTestService;
    @Autowired
    SystemTestService systemTestService;

    @RequestMapping(value = "/getCalendarTestResult",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getCalendarTestResult(){
        return calendarTestService.nextDay();
    }

    @RequestMapping(value = "/systemTest",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getsystemTest(@RequestBody String jsonStr) throws UnsupportedEncodingException {
        String urlStr = URLDecoder.decode(jsonStr, "UTF-8");
        System.out.println(urlStr);
        List<CalendarData> list = JSON.parseArray(urlStr.substring(0,urlStr.length()-1), CalendarData.class);
        return systemTestService.doSystemTest(list);
    }
}
