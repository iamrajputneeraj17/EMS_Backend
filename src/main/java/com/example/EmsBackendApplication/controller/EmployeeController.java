package com.example.EmsBackendApplication.controller;


import com.example.EmsBackendApplication.DTO.EmployeeDTO;
import com.example.EmsBackendApplication.entity.Employee;
import com.example.EmsBackendApplication.service.EmployeeService;
import com.example.EmsBackendApplication.service.GenerateExcel;
import com.example.EmsBackendApplication.service.PdfGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.util.List;


@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private GenerateExcel excel;

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmp(@RequestBody EmployeeDTO employeeDTO)
    {
        EmployeeDTO dto = employeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/{empId}")
    public ResponseEntity<EmployeeDTO> findById(@PathVariable("empId") Long empId)
    {
        EmployeeDTO dto = employeeService.empById(empId);
        log.info("=====================Employee By Id ");
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/doPayemnt")
    public String doPayment()
    {
        log.info("Entered into payment processing");
        try{
            log.info("Payment about to start!");
            throw new RuntimeException("No Balance Exception!");
        } catch (RuntimeException e) {
            log.error("Unable to process payment" + e.getMessage());

            e.printStackTrace();
            log.error("Exception: "+ AppUtil.getLogSupport(e));
//            throw new RuntimeException(e);
        }
        return "Success!";
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmp()
    {
        List<EmployeeDTO> dto = employeeService.getAllEmp();
        return ResponseEntity.ok(dto);
    }

    @PutMapping("{empId}")
    public ResponseEntity<EmployeeDTO> updateEmp(@PathVariable("empId") Long empId, @RequestBody EmployeeDTO employeeDTO)
    {
        EmployeeDTO dto = employeeService.updateEmployee(empId, employeeDTO);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("{empId}")
    public ResponseEntity<String> deleteEmp(@PathVariable("empId") Long empId)
    {
        employeeService.deleteEmployee(empId);
        return ResponseEntity.ok("Employee Deleted Successfully!");
    }


    @Autowired
    private PdfGeneratorService pdfGeneratorService;

    @GetMapping("/view")
    public ResponseEntity<byte[]> viewPdf(@RequestParam String customer, @RequestParam double amount) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            // PDF creation using ByteArrayOutputStream
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            document.add(new Paragraph("Invoice"));
            document.add(new Paragraph("Customer Name: " + customer));
            document.add(new Paragraph("Amount: ₹" + amount));
            document.add(new Paragraph("Thank you for your business!"));

            document.close();

            byte[] pdfBytes = out.toByteArray();

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=Invoice_" + customer + ".pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfBytes);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }


//        Component	Role
//        ByteArrayOutputStream	Captures PDF in memory
//        PdfWriter	Writes content to the output stream
//        PdfDocument	Represents the actual PDF
//        Document	Helps you add structured content like paragraphs
//.add(...)	Adds content to the PDF
//                .close()	Finalizes and writes everything to the stream
    }


    @GetMapping("/empExcelDownload")
    public ResponseEntity<byte[]> downLoadExcel() throws IOException {
        byte[] excelData = excel.generateEmpExcel();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.setContentDispositionFormData("attachment","employees.xlsx");

        return new ResponseEntity<>(excelData, headers, HttpStatus.OK);

    }
}
