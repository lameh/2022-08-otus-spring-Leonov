package ru.otus.spring.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ResponseEntity {

    private final int questionNum;
    private final int answerNum;
}
