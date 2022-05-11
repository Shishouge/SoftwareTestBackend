package com.example.backend.Service.CalendarTest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.backend.Entity.CalendarData;
import com.example.backend.Solution.Calendar.Calendar;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SystemTestService {
    public JSONObject doSystemTest(List<CalendarData> calendarDataList )
    {
        JSONObject totalResult=new JSONObject();
        int right_num=0;
        int error_num=0;
        List<JSONObject> resultList=new ArrayList<>();
        List<JSONObject> error_info=new ArrayList<>();
        for(int i=0;i<calendarDataList.size();i++)
        {
            JSONObject returnedjson =new JSONObject();
            String actual=new Calendar(calendarDataList.get(i).getYear(),calendarDataList.get(i).getMonth(),calendarDataList.get(i).getDay()).nextDay();
            returnedjson.put("id",calendarDataList.get(i).getId());
            returnedjson.put("year",calendarDataList.get(i).getYear());
            returnedjson.put("month",calendarDataList.get(i).getMonth());
            returnedjson.put("day",calendarDataList.get(i).getDay());
            returnedjson.put("expectation",calendarDataList.get(i).getExpectation());
            returnedjson.put("actual",actual);
            returnedjson.put("info","成功");
            String test_result="测试未通过";
            if(actual.equals(calendarDataList.get(i).getExpectation()))
                test_result="测试通过";
            returnedjson.put("test_result",test_result);
            SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd");
            String test_time = tempDate.format(new java.util.Date());
            returnedjson.put("test_time",test_time);
            if(test_result.equals("测试未通过"))
            {
                error_info.add(returnedjson);
                error_num++;
            }
            else
                right_num++;
            resultList.add(returnedjson);
        }
        List<JSONObject> pieData=new ArrayList<>();
        JSONObject temp=new JSONObject();
        temp.put("value",right_num);
        temp.put("name","right");
        pieData.add(temp);
        JSONObject temp1=new JSONObject();
        temp1.put("value",error_num);
        temp1.put("name","error");
        pieData.add(temp1);

        totalResult.put("result_list",resultList);
        totalResult.put("pieData",pieData);
        totalResult.put("error_info",error_info);
        System.out.println(totalResult);
//        for(int i=0;i<resultList.size();i++)
//        {
//            System.out.println(resultList.get(i));
//        }
        return totalResult;

    }
}
