package com.example.backend.Service.TriangleTest;

import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TriangleServiceTest {

    @Autowired
    private TriangleTestService t;;

    @Test
    public void testStaticCsvTest()
    {
        JSONObject j = t.testStaticFile("!Boundary");
        System.out.println(j);
    }
}
