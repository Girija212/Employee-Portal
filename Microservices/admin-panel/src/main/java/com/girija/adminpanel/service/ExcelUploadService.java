package com.girija.adminpanel.service;

import com.girija.adminpanel.entity.Employee;
import com.girija.adminpanel.exception.ExcelProcessingException;
import com.girija.adminpanel.repository.EmployeeRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Service
public class ExcelUploadService {

    @Autowired
    private EmployeeRepository repo;

    public void upload(MultipartFile file) {

        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);
                if (row == null) continue;

                Employee emp = mapRowToEmployee(row);
                repo.save(emp);
            }

            workbook.close();

        } catch (Exception ex) {
            throw new ExcelProcessingException("Failed to process Excel file: " + ex.getMessage(), ex);
        }
    }

    // ----------------------------------------------------------
    // Map Excel Row → Employee Entity
    // Indexes are corrected to match your uploaded Excel file
    // ----------------------------------------------------------
    private Employee mapRowToEmployee(Row row) {

        Employee emp = new Employee();

        emp.setAssociateId(getLong(row.getCell(0)));
        emp.setAssociateName(getString(row.getCell(1)));
        emp.setHomeManagerId(getLong(row.getCell(2)));
        emp.setHomeManagerName(getString(row.getCell(3)));
        emp.setHomeManagerGrade(getString(row.getCell(4)));
        emp.setFteOrCwr(getString(row.getCell(5)));
        emp.setActiveOrExits(getString(row.getCell(6)));

        emp.setYearAndMonthOfJoining(parseYearMonth(row.getCell(7)));

        emp.setGrade(getString(row.getCell(9)));          
        emp.setServiceLine(getString(row.getCell(12)));   
        emp.setVertical(getString(row.getCell(13)));      
        emp.setOnsiteOrOffshore(getString(row.getCell(16)));
        emp.setRegion(getString(row.getCell(17)));
        emp.setLocation(getString(row.getCell(18)));

        emp.setProjectId(getLong(row.getCell(19)));
        emp.setProjectName(getString(row.getCell(20)));
        emp.setAccountId(getLong(row.getCell(21)));
        emp.setAccountName(getString(row.getCell(22)));
        emp.setParentCustId(getLong(row.getCell(23)));
        emp.setParentCustName(getString(row.getCell(24)));

        return emp;
    }

    // ----------------------------------------------------------
    // Helper: Parse YYYY-MM into LocalDate (default day = 1)
    // ----------------------------------------------------------
    private LocalDate parseYearMonth(Cell cell) {
        String value = getString(cell);
        return LocalDate.parse(value + "-01");
    }

    // ----------------------------------------------------------
    // Safe Getters
    // ----------------------------------------------------------
    private String getString(Cell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf(cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            default -> "";
        };
    }

    private Long getLong(Cell cell) {
        if (cell == null) return 0L;
        return switch (cell.getCellType()) {
            case NUMERIC -> (long) cell.getNumericCellValue();
            case STRING -> {
                String v = cell.getStringCellValue();
                yield v.isEmpty() ? 0L : Long.parseLong(v);
            }
            default -> 0L;
        };
    }
}