package com.example.backend.Service.ChargesTest;

import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChargesServiceTest {

    @Autowired
    private ChargesTestService c;;

    @Test
    public void testStaticCsvTest()
    {
        JSONObject j = c.testStaticFile("Boundary");
        System.out.println(j);
    }
}
