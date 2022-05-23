package com.example.backend.Controller.SaleCom;

import com.example.backend.Service.SaleComTest.SaleComTestService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin
@Controller
public class SaleComController {
    @Autowired
    SaleComTestService t;
    @RequestMapping(value = "/getSaleComTestResult",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getSaleComTestResult(){
        return t.testStaticFile();
    }
}
