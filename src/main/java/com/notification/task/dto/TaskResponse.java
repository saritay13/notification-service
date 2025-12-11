package com.notification.task.dto;

import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

public record TaskResponse(Long id,
                           String status,
                           String title,
                           LocalDateTime createdAt) {
}
