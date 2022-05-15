package com.example.backend.Controller.Triangle;

import com.alibaba.fastjson.JSON;
import com.example.backend.Entity.TriangleCase;
import com.example.backend.Service.TriangleTest.TriangleTestService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@CrossOrigin
@Controller
public class TriangleController {

    @Autowired
    TriangleTestService t;

    @RequestMapping(value = "/triangle/staticTest",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getTriangleStaticTestResult(){
        return t.testStaticFile("!Boundary");
    }

    @RequestMapping(value = "/triangle/batchTest",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getTriangleBatchTestResult(@RequestBody String jsonStr) throws UnsupportedEncodingException {
        String urlStr = URLDecoder.decode(jsonStr, "UTF-8");
        System.out.println(urlStr.substring(0,urlStr.length()-1));
        // 传过来的json字符串前后带空格 所以要截取
        List<TriangleCase> list = JSON.parseArray(urlStr.substring(0,urlStr.length()-1), TriangleCase.class);
        System.out.println(list);
        return t.testCaseBatch(list);
    }
}

