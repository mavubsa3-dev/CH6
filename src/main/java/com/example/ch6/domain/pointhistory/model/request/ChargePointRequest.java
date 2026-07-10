package com.example.ch6.domain.pointhistory.model.request;

import jakarta.validation.constraints.NotNull;

public record ChargePointRequest(
	@NotNull(message = "ID를 입력하세요") Long userId,
	Integer point
) {
}
