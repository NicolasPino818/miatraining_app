package com.inovisoft.backend_miatraining.mailing.DTO;

import lombok.Builder;

@Builder
public record MailBody(
    String to,
    String subject,
    String text
) {
}
