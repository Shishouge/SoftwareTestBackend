package com.example.backend.Service.SaleComTest;

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
