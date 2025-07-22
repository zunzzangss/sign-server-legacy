package com.bezzangss.sign.common.resource;

import com.bezzangss.sign.common.CommonException;
import lombok.Builder;
import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;

import static com.bezzangss.sign.common.exception.ErrorCode.INTERNAL_SERVER_ERROR;

@Builder
@Getter
public class ResourceStream {
    private final InputStreamHandler inputStreamHandler;

    public void process(InputStreamConsumer consumer) {
        try {
            inputStreamHandler.handle(inputStream -> {
                try (InputStream is = inputStream) {
                    consumer.accept(is);
                }
            });
        } catch (IOException e) {
            throw new CommonException(INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @FunctionalInterface
    public interface InputStreamHandler {
        void handle(InputStreamConsumer consumer) throws IOException;
    }

    @FunctionalInterface
    public interface InputStreamConsumer {
        void accept(InputStream inputStream) throws IOException;
    }
}
