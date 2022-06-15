package com.example.backend.Controller.SaleCom;

import com.alibaba.fastjson.JSON;
import com.example.backend.Entity.SaleComCase;
import com.example.backend.Entity.TriangleCase;
import com.example.backend.Service.SaleComTest.SaleComTestService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@CrossOrigin
@Controller
public class SaleComController {
    @Autowired
    SaleComTestService t;
    @RequestMapping(value = "/saleCom/SaleComTestResult",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getSaleComTestResult(@RequestBody String jsonStr)throws UnsupportedEncodingException {
        String urlStr = URLDecoder.decode(jsonStr, "UTF-8");
        System.out.println(urlStr.substring(0,urlStr.length()-1));
        // 传过来的json字符串格式会乱加东西 所以要截取
        List<SaleComCase> list = JSON.parseArray(urlStr.substring(0,urlStr.length()-1), SaleComCase.class);
        System.out.println(list);

        return t.testCaseBatch(list);
    }
}
