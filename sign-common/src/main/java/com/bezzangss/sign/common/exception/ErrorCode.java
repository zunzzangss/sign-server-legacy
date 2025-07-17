package com.bezzangss.sign.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    BAD_REQUEST(400_00_00, "잘못된 요청."),
    INTERNAL_SERVER_ERROR(500_00_00, "서버 내부 오류."),

    NOT_FOUND_ARGUMENT_EXCEPTION(400_00_01, "인자가 존재하지 않습니다."),
    ILLEGAL_ARGUMENT_EXCEPTION(400_00_02, "인자가 부적합합니다."),
    BIND_EXCEPTION(400_00_03, "데이터 바인딩 실패."),
    JSON_PROCESSING_EXCEPTION(400_00_04, "JSON 처리 실패."),

    HTTP_MESSAGE_NOT_READABLE_EXCEPTION(400_00_10, "HTTP 메시지 읽기 실패."),

    /**
     * TEMPLATE_DOCUMENT
     */

    TEMPLATE_DOCUMENT_STATUS_IS_NONE_EXCEPTION(400_20_21, "NONE 상태입니다."),
    TEMPLATE_DOCUMENT_STATUS_IS_DELETE_EXCEPTION(400_20_22, "DELETE 상태입니다."),

    TEMPLATE_DOCUMENT_IS_NOT_UPDATABLE_EXCEPTION(400_20_31, "변경할 수 없는 상태입니다."),

    TEMPLATE_DOCUMENT_NOT_FOUND_EXCEPTION(404_20_00, "정보가 존재하지 않습니다."),
    ;

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
