package com.example.backend.Controller.Charges;

import com.example.backend.Service.ChargesTest.ChargesTestService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin
@Controller
public class ChargesController {
    @Autowired
    ChargesTestService c;

    @RequestMapping(value = "/getChargesTestResult",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getChargesTestResult(){
        return c.testStaticFile("Boundary");
    }
}
