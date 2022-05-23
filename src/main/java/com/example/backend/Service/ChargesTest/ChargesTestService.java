package com.example.backend.Service.ChargesTest;

import com.example.backend.Entity.ChargeCase;
import com.example.backend.Entity.TriangleCase;
import com.example.backend.Solution.Calendar.returnformat;
import com.example.backend.Solution.Charges.Charges;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
            System.out.println(chargeCaseList.get(i).getMonth());
            JSONObject returnedjson =new JSONObject();
            double actual= charge.getCharge(
                    chargeCaseList.get(i).getNum(),chargeCaseList.get(i).getTime(),
                    chargeCaseList.get(i).getYear(),chargeCaseList.get(i).getMonth());

            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String testTime = sdf.format(new java.util.Date());

            returnedjson.put("id",chargeCaseList.get(i).getId());
            returnedjson.put("year",chargeCaseList.get(i).getYear());
            returnedjson.put("month",chargeCaseList.get(i).getMonth());
            returnedjson.put("num",chargeCaseList.get(i).getNum());
            returnedjson.put("time",chargeCaseList.get(i).getTime());
            returnedjson.put("expectedOutput",chargeCaseList.get(i).getExpectedOutput());
            returnedjson.put("actualOutput",actual);
            returnedjson.put("info","Success");
            returnedjson.put("testTime",testTime);

            if(actual == chargeCaseList.get(i).getExpectedOutput()) {
                returnedjson.put("testResult","Pass");
                right_num++;
            } else {
                returnedjson.put("testResult","Error");
                error_info.add(returnedjson);
                error_num++;
            }

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

        String address = "src/main/resources/static/ChargeTestData/";
        if (testType.equals("Decision")) {
            address += "ChargeTetData_D.xls";
        }
        File file = new File(address);

        try {
            int pass_num = 0;
            int error_num = 0;

            // 获得原始数据
            Workbook wb = Workbook.getWorkbook(file);
            // 创建副本
            WritableWorkbook workbook = Workbook.createWorkbook(file, wb);
            Sheet sheet = wb.getSheet(0);
            WritableSheet wsheet = workbook.getSheet(0);

            // 添加新列
            wsheet.addCell(new Label(6, 0, "actualOutput"));
            wsheet.addCell(new Label(7, 0, "testResult"));


            for (int i = 1; i < sheet.getRows(); i++) {
                int year = Integer.valueOf(sheet.getCell(1,i).getContents());
                int month = Integer.valueOf(sheet.getCell(2,i).getContents());
                int num = Integer.valueOf(sheet.getCell(3,i).getContents());
                double time = Double.valueOf(sheet.getCell(4,i).getContents());

                // 如果输入的是 1.010这种 string就会错（猜测

                double expectedOutput = Double.valueOf(sheet.getCell(5,i).getContents());
                double actualOutput = charge.getCharge(num, time, year, month);

                Label actual_Output = new Label(6, i, String.valueOf(actualOutput));
                wsheet.addCell(actual_Output);

                if(actualOutput == (expectedOutput)) {
                    Label testResult = new Label(7, i, "Pass");
                    wsheet.addCell(testResult);
                    pass_num++;
                } else {
                    Label testResult = new Label(7, i, "Error");
                    wsheet.addCell(testResult);
                    error_num++;
                    returnformat r = new returnformat(
                            num +"/"+ time,
                            String.valueOf(expectedOutput), String.valueOf(actualOutput));
                    errorList.add(r);
                }
            }

            json.put("right_num",pass_num);
            json.put("error_num",error_num);
            json.put("error_info",errorList);

            workbook.write();
            workbook.close();
            return json;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }
        return null;
    }
}
