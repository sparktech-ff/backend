package com.sparktechcode.ff.core.common.payloads;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class AuditDto {
    private Instant created;
    private Instant updated;
}
