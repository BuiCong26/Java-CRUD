package com.globits.da.controller;

import com.globits.da.dto.SimpleDTO;
import com.globits.da.service.MyFirstApiService;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class NewApiController {
   private MyFirstApiService myFirstApiService;
//    public static final String hello = "Hello";
//    @GetMapping("/my-first-api")
//    public String firstApi(@RequestParam(value = "name") String name){
//        return hello+ " "  + name;
//    }
//    @GetMapping("/my-first-api")
//    public String firstApi(@RequestParam(value = "name") String name){
//        try {
//            return myFirstApiService.myFirstApi(name);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//    @GetMapping("/my-first-api/{name}")
//    public String firstApi(@PathVariable("name") String name){
//        try {
//            return myFirstApiService.myFirstApi(name);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
    @GetMapping("/my-first-api")
    public void firstApi(@RequestParam("file") MultipartFile file){
        try {
//            System.out.println(new String(file.getBytes(), StandardCharsets.UTF_8));      //read txt
//            FileInputStream fis= new FileInputStream((File) file);
            XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream());
            XSSFSheet sheet=wb.getSheetAt(0);
            FormulaEvaluator formulaEvaluator= wb.getCreationHelper().createFormulaEvaluator();
            for(Row row : sheet){
                for(Cell cell : row){
                    switch (formulaEvaluator.evaluateInCell(cell).getCellType()){
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.print(cell.getNumericCellValue()+ "\t\t");
                            break;
                        case Cell.CELL_TYPE_STRING:
                            System.out.print(cell.getStringCellValue()+ "\t\t");
                            break;
                    }
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @PostMapping("/my-first-dto")
    public SimpleDTO firstDto(@RequestBody SimpleDTO simpleDTO){
        try{
            return myFirstApiService.myFirstDTO(simpleDTO);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
