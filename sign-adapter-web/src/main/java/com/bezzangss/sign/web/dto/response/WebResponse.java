package com.bezzangss.sign.web.dto.response;

import com.bezzangss.sign.common.exception.CommonException;
import com.bezzangss.sign.web.WebException;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import static com.bezzangss.sign.common.exception.ErrorCode.INTERNAL_SERVER_ERROR;

@Getter
public class WebResponse<T> {
    private final boolean success;
    private final T contents;
    private final Error error;

    @Builder
    private WebResponse(boolean success, T contents, Error error) {
        this.success = success;
        this.contents = contents;
        this.error = error;
        this.validate();
    }

    public static <T> WebResponse<T> success(T contents) {
        return WebResponse.<T>builder()
                .success(true)
                .contents(contents)
                .build();
    }

    public static <T> WebResponse<T> failure(CommonException commonException) {
        return WebResponse.<T>builder()
                .success(false)
                .error(
                        Error.builder()
                                .code(commonException.getErrorCode().getCode())
                                .message(String.format("%s %s", commonException.getErrorCode().getMessage(), commonException.getMessage()))
                                .build()
                )
                .build();
    }

    private void validate() {
        if (this.success) {
            if (ObjectUtils.isEmpty(this.contents)) throw new WebException(INTERNAL_SERVER_ERROR, "not found contents.");
        } else {
            if (ObjectUtils.isEmpty(this.error)) throw new WebException(INTERNAL_SERVER_ERROR, "not found error.");
        }
    }

    @Getter
    private static class Error {
        private final int code;
        private final String message;

        @Builder
        private Error(int code, String message) {
            this.code = code;
            this.message = message;
        }
    }
}
