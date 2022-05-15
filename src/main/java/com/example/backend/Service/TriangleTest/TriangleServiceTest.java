package com.example.backend.Service.TriangleTest;

import com.example.backend.Entity.TriangleCase;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void testCaseBatchTest()
    {
        List<TriangleCase> tc = new ArrayList<>();
        TriangleCase myCase = new TriangleCase("1", 3, 4, 5, "Rt");
        tc.add(myCase);
        JSONObject j = t.testCaseBatch(tc);
        System.out.println(j);
    }
}
