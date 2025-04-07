package com.demo.black_list_check.service;

import com.demo.black_list_check.entity.BlackList;
import com.demo.black_list_check.repository.BlackListRepository;
import org.apache.poi.ss.usermodel.*;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelService {

    private final BlackListRepository blackListRepository;

    public ExcelService(BlackListRepository blackListRepository) {
        this.blackListRepository = blackListRepository;
    }

    @Transactional
    public void saveExcelData(MultipartFile file) throws Exception {
        List<BlackList> list = new ArrayList<>();
        try (InputStream is = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(is);
        ) {
            for (int indexSheet = 0; indexSheet < workbook.getNumberOfSheets(); indexSheet++) {
                Sheet sheet = workbook.getSheetAt(indexSheet);
                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    if (row == null) continue; // Bỏ qua nếu dòng trống
                    BlackList blacklist = new BlackList();

                    blacklist.setFullName(getCellValue(row.getCell(0)));
                    blacklist.setCitizen_identification_card(getCellValue(row.getCell(1)));
                    blacklist.setIdentity_card(getCellValue(row.getCell(2)));
                    blacklist.setPassport(getCellValue(row.getCell(3)));
                    blacklist.setDob(getCellValue(row.getCell(4)));
                    blacklist.setAddress(getCellValue(row.getCell(5)));
                    blacklist.setList(getCellValue(row.getCell(6)));
                    blacklist.setSource(getCellValue(row.getCell(7)));

                    list.add(blacklist);
                }
            } //Sheet sheet = workbook.getSheetAt(1);
        }
        blackListRepository.saveAll(list);
    }



    private String getCellValue(Cell cell) {
        if (cell == null) return null;

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf((int) cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return null;
        }
    }
}
