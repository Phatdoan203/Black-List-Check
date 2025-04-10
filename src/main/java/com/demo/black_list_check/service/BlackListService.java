package com.demo.black_list_check.service;

import com.demo.black_list_check.dto.BlackListDTO;
import com.demo.black_list_check.dto.res.BlackListResponse;
import com.demo.black_list_check.entity.BlackList;
import com.demo.black_list_check.repository.BlackListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BlackListService {
    @Autowired
    private BlackListRepository blackListRepository;

    public BlackListResponse findBlackLists(String fullName, String address, String citizen_identification_card) {
        List<BlackList> findByNameAndAddress = blackListRepository.findByFullNameAndAddress(fullName, address);
        if (findByNameAndAddress.isEmpty()) {
            return new BlackListResponse("User not found", null );
        }
        List<BlackList> filteredList = findByNameAndAddress.stream()
                .filter(b -> b.getCitizen_identification_card().equals(citizen_identification_card))
                .collect(Collectors.toList());
        if (!filteredList.isEmpty()) {
            return new BlackListResponse("Warning this person is exact match in black list", filteredList );
        }
        return new BlackListResponse("Warning this person has 70% match in black list", findByNameAndAddress );
    }

    public Page<BlackListDTO> getBlackList(Pageable pageable) {
        Page<BlackListDTO> blackList = blackListRepository.findAll(pageable)
                .map(blackListDTO -> new BlackListDTO(blackListDTO.getId(), blackListDTO.getFullName(), blackListDTO.getCitizen_identification_card(), blackListDTO.getIdentity_card(), blackListDTO.getPassport(), blackListDTO.getDob(), blackListDTO.getAddress(), blackListDTO.getList(), blackListDTO.getSource()));
        return blackList;
    }

    public BlackList updateBlackList(BlackListDTO blackListDTO, int id) {
        try {
            BlackList blackList = blackListRepository.findById(id).orElseThrow(() -> {
                new RuntimeException("User not found" + id);
                return null;
            });
            blackList.setFullName(blackListDTO.getFullName());
            blackList.setCitizen_identification_card(blackListDTO.getCitizenIdentificationCard());
            blackList.setIdentity_card(blackListDTO.getIdentityCard());
            blackList.setPassport(blackListDTO.getPassport());
            blackList.setDob(blackListDTO.getDob());
            blackList.setAddress(blackListDTO.getAddress());
            blackList.setList(blackListDTO.getList());
            blackList.setSource(blackListDTO.getSource());

            BlackList updatedBlackList = blackListRepository.save(blackList);
            return updatedBlackList;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        try {
            blackListRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
