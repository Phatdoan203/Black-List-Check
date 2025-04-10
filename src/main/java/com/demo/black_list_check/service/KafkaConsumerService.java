package com.demo.black_list_check.service;


import com.demo.black_list_check.entity.Transactions;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class KafkaConsumerService {
    @Autowired
    private TelegramService telegramService;

    @KafkaListener(topics = "check_money_laudering", groupId = "checking-group", containerFactory = "kafkaListenerContainerFactory")
    public void consumeAML(Transactions transaction) {
        try {
            boolean isMoneyLaundering = checkMoneyLaundering(transaction);
            if (isMoneyLaundering) {
                telegramService.sendMessage("Cảnh báo: Giao dịch " + transaction.getId() + " có dấu hiệu rửa tiền!");
            } else {
                telegramService.sendMessage("Giao dịch : " + transaction.getId() + " hợp lệ.");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean checkMoneyLaundering(Transactions transactions) {
        return transactions.getAmount().compareTo(new BigDecimal("50000")) > 0;
    }
}
