package com.demo.black_list_check.service;

import com.demo.black_list_check.entity.BlackList;
import com.demo.black_list_check.repository.BlackListRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelService {
    @Autowired
    private BlackListRepository blackListRepository;

    public void saveExcelData(MultipartFile file) throws Exception {
        List<BlackList> list = new ArrayList<>();
        try (InputStream is = file.getInputStream();
             Workbook workbook = new SXSSFWorkbook(new XSSFWorkbook(is), 100)) {
            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                Sheet sheet = workbook.getSheetAt(sheetIndex);
                for (int i = 1; i < sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    // Lấy ra các trường trong file Excel
                    int id = (int) row.getCell(0).getNumericCellValue();
                    String name = row.getCell(1).getStringCellValue();
                    String citizen_identification_card = row.getCell(2).getStringCellValue();
                    String identity_card = row.getCell(3).getStringCellValue();
                    String passport = row.getCell(4).getStringCellValue();
                    String dob = row.getCell(5).getStringCellValue();
                    String address = row.getCell(6).getStringCellValue();
                    String lists = row.getCell(7).getStringCellValue();
                    String source = row.getCell(8).getStringCellValue();

                    BlackList blacklist = new BlackList();
                    blacklist.setId(id);
                    blacklist.setFullName(name);
                    blacklist.setCitizen_identification_card(citizen_identification_card);
                    blacklist.setIdentity_card(identity_card);
                    blacklist.setPassport(passport);
                    blacklist.setDob(dob);
                    blacklist.setAddress(address);
                    blacklist.setList(lists);
                    blacklist.setSource(source);
                    list.add(blacklist);
                }
            }
        }
        blackListRepository.saveAll(list);
    }
}
