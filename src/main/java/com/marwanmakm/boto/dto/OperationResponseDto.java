package com.marwanmakm.boto.dto;

import java.sql.Timestamp;

public record OperationResponseDto(Timestamp timestamp, String message, String description) {}
