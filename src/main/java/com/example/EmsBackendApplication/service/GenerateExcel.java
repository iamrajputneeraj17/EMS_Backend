package com.example.EmsBackendApplication.service;

import com.example.EmsBackendApplication.entity.Employee;
import com.example.EmsBackendApplication.repository.EmployeeRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class GenerateExcel {
    @Autowired
    private EmployeeRepository employeeRepository;

    public byte[] generateEmpExcel() throws IOException {
        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Sheet sheet = workbook.createSheet("Employees");

        // Creating header row for heading
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("First Name");
        headerRow.createCell(2).setCellValue("Last Name");
        headerRow.createCell(3).setCellValue("Email");

        //For data
        int rowIdx = 1;

        List<Employee> employees = employeeRepository.findAll();
        for (Employee emp: employees)
        {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(emp.getId());
            row.createCell(1).setCellValue(emp.getFirstName());
            row.createCell(2).setCellValue(emp.getLastName());
            row.createCell(3).setCellValue(emp.getEmail());
        }

        workbook.write(out);
        return out.toByteArray();
    }
}
