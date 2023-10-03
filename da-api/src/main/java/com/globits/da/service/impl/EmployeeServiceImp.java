package com.globits.da.service.impl;

import com.globits.da.dto.search.SearchEmployeeDTO;
import com.globits.da.domain.Employee;
import com.globits.da.repository.EmployeeRepo;
import com.globits.da.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class EmployeeServiceImp implements EmployeeService {

    private EmployeeRepo employeeRepo;

    @Override
    public List<Employee> findAll(){
        return employeeRepo.findAll();
    }
    @Override
    public Employee findById(int id){
        return employeeRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("id not found: "+ id));
    }
    @Override
    public Employee findWithConditions(SearchEmployeeDTO searchEmployeeDTO){
        return employeeRepo.findEmployeeByObject(searchEmployeeDTO);
    }
    @Transactional
    @Override
    public void deleteById(int id){
        employeeRepo.deleteById(id);
    }
    public boolean exportExcel(){
        String[] lstHeader ={"Id","Code","Name","Email","Phone","Age"};
        Date date = new Date();
        try {
            List<Employee> employee = employeeRepo.findAll();
            String fileName = "EmployeeToExcel.xlsx";
            String excelPathFile = "D:/EmployeeToExcel.xlsx";
            writeExcel(employee,excelPathFile,lstHeader);
//            File file = new File(excelPathFile);
//            String mimeType = "application/vnd.ms-excel";
//            String typeFile = "xlsx";
            InputStreamResource resource = new InputStreamResource(new FileInputStream(excelPathFile));
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    private void writeExcel(List<Employee> objects, String excelPathFile, String[] lstHeader) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Employee");
        int rowIndex = 0;
        writeHeader(sheet, rowIndex, lstHeader);
        rowIndex++;
        for (Employee obj : objects) {
            String oneEmp = obj.toString();
            String[] partEmp = oneEmp.split(",");
            Row row = sheet.createRow(rowIndex);
            writeBook(partEmp, row, lstHeader.length);
            rowIndex++;
        }
        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);
        try(OutputStream os = new FileOutputStream(excelPathFile)){
            workbook.write(os);
        }
        System.out.println("Done!!!");
    }
    private void writeHeader(Sheet sheet, int rowIndex, String[] lstHeader) {
        CellStyle cellStyle = createStyleForHeader(sheet);
        Row row = sheet.createRow(rowIndex);
        for (int i = 0; i < lstHeader.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(lstHeader[i]);
        }
    }
    private CellStyle createStyleForHeader(Sheet sheet) {
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14);
        font.setColor(IndexedColors.WHITE.getIndex());

        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }
    private void writeBook(Object[] obj,Row row, int size){
        for (int i = 0; i < size; i++) {
            Cell cell = row.createCell(i);
            if (obj[i] != null) {
                cell.setCellValue(obj[i].toString());
            }
        }
    }
    private void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }
}
