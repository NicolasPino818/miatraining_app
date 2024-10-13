package com.inovisoft.backend_miatraining.logic.DTOs.emailDTO;

import lombok.Builder;

@Builder
public record MailBody(
    String to,
    String subject,
    String text
) {
}
