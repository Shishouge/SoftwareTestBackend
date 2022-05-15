package com.example.backend.Service.ChargesTest;

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

    public JSONObject testStaticFile(String testType) {

        String address = "src/main/resources/static/ChargesTestData/";
        if (testType.equals("Boundary")) {
            address += "ChargesTest_B.xls";
        } else if (testType.equals("Equivalence")) {
            address += "ChargesTest_E.xls";
        } else {
            address += "ChargesTest_D.xls";
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
            wsheet.addCell(new Label(4, 0, "actualOutput"));
            wsheet.addCell(new Label(5, 0, "testResult"));


            for (int i = 1; i < sheet.getRows(); i++) {

                int num = Integer.valueOf(sheet.getCell(1,i).getContents());
                double time = Double.valueOf(sheet.getCell(2,i).getContents());
                // 如果输入的是 1.010这种 string就会错（猜测
                String expectedOutput = String.valueOf(sheet.getCell(3,i).getContents());
                String actualOutput = String.valueOf(charge.getCharge(num, time));

                Label actual_Output = new Label(4, i, actualOutput);
                wsheet.addCell(actual_Output);

                if(actualOutput.equals(expectedOutput)) {
                    Label testResult = new Label(5, i, "Pass");
                    wsheet.addCell(testResult);
                    pass_num++;
                } else {
                    Label testResult = new Label(5, i, "Error");
                    wsheet.addCell(testResult);
                    error_num++;
                    returnformat r = new returnformat(
                            num +"/"+ time,
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
