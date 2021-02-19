package com.freelecspringboot2webservice.web.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class HelloResponseDtoTest {

    @Test
    @DisplayName("정상적으로 롬복이 getter메서드와 생성자를 만들어주는지 테스트")
    void lombokTest() {
        String name = "test";
        int amount = 100;

        HelloResponseDto dto = new HelloResponseDto(name, amount);

        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }


}