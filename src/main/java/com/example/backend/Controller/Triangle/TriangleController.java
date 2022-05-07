package com.example.backend.Controller.Triangle;

import com.example.backend.Service.TriangleTest.TriangleTestService;
import net.sf.json.JSONObject;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin
@Controller
public class TriangleController {
    @Autowired
    TriangleTestService t;
    @RequestMapping(value = "/getTriangleTestResult",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getTriangleTestResult(){
        return t.testStaticFile("!Boundary");
    }
}

