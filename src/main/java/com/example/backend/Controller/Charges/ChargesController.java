package com.example.backend.Controller.Charges;

import com.alibaba.fastjson.JSON;
import com.example.backend.Entity.ChargeCase;
import com.example.backend.Entity.TriangleCase;
import com.example.backend.Service.ChargesTest.ChargesTestService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@CrossOrigin
@Controller
public class ChargesController {
    @Autowired
    ChargesTestService c;

    @RequestMapping(value = "/charge/staticTest",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getChargesTestResult(){
        return c.testStaticFile("Decision");
    }

    @RequestMapping(value = "/charge/batchTest",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getChargesBatchTestResult(@RequestBody String jsonStr) throws UnsupportedEncodingException {

        String urlStr = URLDecoder.decode(jsonStr, "UTF-8");
        System.out.println(urlStr.substring(0,urlStr.length()-1));
        List<ChargeCase> list = JSON.parseArray(urlStr.substring(0,urlStr.length()-1), ChargeCase.class);
        System.out.println(list);
        return c.testCaseBatch(list);
    }

}
