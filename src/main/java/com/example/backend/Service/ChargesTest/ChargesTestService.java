package com.example.backend.Service.ChargesTest;

import com.example.backend.Entity.ChargeCase;
import com.example.backend.Solution.Calendar.returnformat;
import com.example.backend.Solution.Charges.Charges;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChargesTestService {

    private Charges charge;
    private JSONObject json;
    private List<returnformat> errorList;

    public ChargesTestService() {
        this.charge = new Charges();
        this.json = new JSONObject();
        this.errorList = new ArrayList<>();
    }

    public JSONObject testCaseBatch(List<ChargeCase> chargeCaseList) {
        int right_num=0;
        int error_num=0;
        JSONObject totalResult= new JSONObject();
        List<JSONObject> resultList=new ArrayList<>();
        List<JSONObject> error_info=new ArrayList<>();
        for(int i=0;i<chargeCaseList.size();i++)
        {
            System.out.println(chargeCaseList.get(i).getTelTime());
            System.out.println(chargeCaseList.get(i).getDelayPayTimes());
            JSONObject returnedjson =new JSONObject();
            String actual= charge.checkType(chargeCaseList.get(i).getTelTime(),chargeCaseList.get(i).getDelayPayTimes());

            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String testTime = sdf.format(new java.util.Date());

            returnedjson.put("id",chargeCaseList.get(i).getId());
            returnedjson.put("telTime",chargeCaseList.get(i).getTelTime());
            returnedjson.put("delayPayTimes",chargeCaseList.get(i).getDelayPayTimes());
            returnedjson.put("expectedOutput",chargeCaseList.get(i).getExpectedResult().toString());
            returnedjson.put("actualOutput",actual);
            returnedjson.put("info","Success");
            returnedjson.put("testTime",testTime);
            System.out.print(actual + "   ");
            System.out.print(chargeCaseList.get(i).getExpectedResult().toString() + "   ");
            if(actual.equals(chargeCaseList.get(i).getExpectedResult())) {
                System.out.print("Equal");
                returnedjson.put("testResult","Success");
                right_num++;
            } else {
                System.out.print("Error");
                returnedjson.put("testResult","Error");
                error_info.add(returnedjson);
                error_num++;
            }
            System.out.println();
            resultList.add(returnedjson);
        }

        // 统计变量
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

        return totalResult;
    }

    public JSONObject testStaticFile(String testType) {
        return null;
    }
}
