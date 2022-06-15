package com.example.backend.Service.SaleComTest;

import com.example.backend.Entity.SaleComCase;
import com.example.backend.Solution.Calendar.returnformat;
import com.example.backend.Solution.SaleCom.SaleCom;
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
public class SaleComTestService {
    private SaleCom sc;
    private JSONObject json;
    private List<returnformat> errorList;

    public SaleComTestService(){
        this.sc = new SaleCom();
        this.json = new JSONObject();
        this.errorList = new ArrayList<>();
    }

    public JSONObject testCaseBatch(List<SaleComCase> saleComCaseList){
        int right_num=0;
        int error_num=0;
        JSONObject totalResult= new JSONObject();
        List<JSONObject> resultList=new ArrayList<>();
        List<JSONObject> error_info=new ArrayList<>();

        for(int i =0;i<saleComCaseList.size();i++){
            JSONObject returnedjson =new JSONObject();
            String actual= sc.checkReward(
                    (int)saleComCaseList.get(i).getMainUnit(),(int)saleComCaseList.get(i).getScreen(),(int)saleComCaseList.get(i).getPerpheral()
            );
            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String testTime = sdf.format(new java.util.Date());

            returnedjson.put("id",saleComCaseList.get(i).getId());
            returnedjson.put("mainUnit", saleComCaseList.get(i).getMainUnit());
            returnedjson.put("screen", saleComCaseList.get(i).getScreen());
            returnedjson.put("perpheral", saleComCaseList.get(i).getPerpheral());
            returnedjson.put("expectedOutput",saleComCaseList.get(i).getExpectedOutput());
            returnedjson.put("actualOutput",actual);
            returnedjson.put("info","Success");
            returnedjson.put("testTime",testTime);

            if(actual.equals(saleComCaseList.get(i).getExpectedOutput())){
                returnedjson.put("testResult","Pass");
                right_num++;
            }else{
                returnedjson.put("testResult","Error");
                error_info.add(returnedjson);
                error_num++;
            }

            resultList.add(returnedjson);

        }
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

    public JSONObject testStaticFile(){
        String address = "src/main/resources/static/SaleComTestData/SaleComTest1.xls";
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

                int mainUnit=Integer.valueOf(sheet.getCell(1,i).getContents());
                int screen=Integer.valueOf(sheet.getCell(2,i).getContents());
                int perpheral=Integer.valueOf(sheet.getCell(3,i).getContents());
                String expectedOutput = String.valueOf(sheet.getCell(4,i).getContents());
                String actualOutput = sc.checkReward(mainUnit,screen,perpheral);

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
                            mainUnit +"/"+ screen +"/"+ perpheral,
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
