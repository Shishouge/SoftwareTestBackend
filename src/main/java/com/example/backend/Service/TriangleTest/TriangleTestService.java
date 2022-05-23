package com.example.backend.Service.TriangleTest;
import com.example.backend.Entity.TriangleCase;
import com.example.backend.Solution.Calendar.returnformat;
import com.example.backend.Solution.Triangle.*;

import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.*;
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
public class TriangleTestService {
    private Triangle t;
    private JSONObject json;
    private List<returnformat> errorList;

    public TriangleTestService() {
        this.t = new Triangle();
        this.json = new JSONObject();
        this.errorList = new ArrayList<>();
    }

    public JSONObject testCaseBatch(List<TriangleCase> triangleCaseList) {

        int right_num=0;
        int error_num=0;
        JSONObject totalResult= new JSONObject();
        List<JSONObject> resultList=new ArrayList<>();
        List<JSONObject> error_info=new ArrayList<>();

        for(int i=0;i<triangleCaseList.size();i++)
        {
            JSONObject returnedjson =new JSONObject();
            String actual= t.checkType(
                    triangleCaseList.get(i).getA(),triangleCaseList.get(i).getB(),triangleCaseList.get(i).getC());

            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String testTime = sdf.format(new java.util.Date());

            returnedjson.put("id",triangleCaseList.get(i).getId());
            returnedjson.put("a",triangleCaseList.get(i).getA());
            returnedjson.put("b",triangleCaseList.get(i).getB());
            returnedjson.put("c",triangleCaseList.get(i).getC());
            returnedjson.put("expectedOutput",triangleCaseList.get(i).getExpectedOutput());
            returnedjson.put("actualOutput",actual);
            returnedjson.put("info","Success");
            returnedjson.put("testTime",testTime);

            if(actual.equals(triangleCaseList.get(i).getExpectedOutput())) {
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

        String address = "src/main/resources/static/TriangleTestData/";
        if (testType.equals("Boundary")) {
            address += "TriangleTest_B.xls";
        } else {
            address += "TriangleTest_E.xls";
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
            wsheet.addCell(new Label(5, 0, "actualOutput"));
            wsheet.addCell(new Label(6, 0, "testResult"));


            for (int i = 1; i < sheet.getRows(); i++) {

                int slide1=Integer.valueOf(sheet.getCell(1,i).getContents());
                int slide2=Integer.valueOf(sheet.getCell(2,i).getContents());
                int slide3=Integer.valueOf(sheet.getCell(3,i).getContents());
                String expectedOutput = String.valueOf(sheet.getCell(4,i).getContents());
                String actualOutput = t.checkType(slide1, slide2, slide3);

                Label actual_Output = new Label(5, i, actualOutput);
                wsheet.addCell(actual_Output);

                if(actualOutput.equals(expectedOutput)) {
                    Label testResult = new Label(6, i, "Pass");
                    wsheet.addCell(testResult);
                    pass_num++;
                } else {
                    Label testResult = new Label(6, i, "Error");
                    wsheet.addCell(testResult);
                    error_num++;
                    returnformat r = new returnformat(
                            slide1 +"/"+ slide2 +"/"+ slide3,
                            expectedOutput, actualOutput);
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