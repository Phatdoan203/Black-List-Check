package com.demo.black_list_check.controller;

import com.demo.black_list_check.dto.BlackListDTO;
import com.demo.black_list_check.dto.req.BlackListRequest;
import com.demo.black_list_check.dto.res.BlackListResponse;
import com.demo.black_list_check.entity.BlackList;
import com.demo.black_list_check.service.BlackListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/checking")
public class BlackListController {
    @Autowired
    private BlackListService blackListService;

    @GetMapping("/isMatch")
    public ResponseEntity<?> isMatch(@RequestParam String fullname, @RequestParam String address, @RequestParam String citizen_identification_card) {
        BlackListRequest request = new BlackListRequest(fullname, address, citizen_identification_card);
        BlackListResponse result = blackListService.findBlackLists(request.getFullname(), request.getAddress(), request.getCitizen_identification_card());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/find")
    public ResponseEntity<?> getAll (@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "5") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<BlackListDTO> response = blackListService.getBlackList(pageable);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody BlackListDTO request, @RequestParam int id) {
        try {
            BlackList updatedList = blackListService.updateBlackList(request, id);
            return ResponseEntity.ok(updatedList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestParam int id) {
        try {
            blackListService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
