package com.demo.black_list_check.controller;

import com.demo.black_list_check.entity.BlackList;
import com.demo.black_list_check.repository.BlackListRepository;
import com.demo.black_list_check.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/excel")
public class ExcelController {
    @Autowired
    private ExcelService excelService;
    @Autowired
    private BlackListRepository blackListRepository;
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            excelService.saveExcelData(file);
            return ResponseEntity.ok("Upload thành công!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Có lỗi xảy ra: " + e.getMessage());
        }
    }

    @PostMapping("/test-save")
    public ResponseEntity<String> testSave() {
        BlackList blacklist = new BlackList();
        blacklist.setFullName("Nguyen Van A");
        blacklist.setCitizen_identification_card("123456789");
        blacklist.setIdentity_card("0123456789");
        blacklist.setPassport("AB1234567");
        blacklist.setDob("1990-01-01");
        blacklist.setAddress("Ha Noi");
        blacklist.setList("Danh sách kiểm tra");
        blacklist.setSource("Nguồn kiểm tra");

        blackListRepository.save(blacklist);

        return ResponseEntity.ok("Lưu thành công!");
    }

}
