package com.bezzangss.sign.common.inputstream;

import com.bezzangss.sign.common.exception.CommonException;
import com.bezzangss.sign.common.inputstream.interfaces.InputStreamConsumer;
import com.bezzangss.sign.common.inputstream.interfaces.InputStreamSupplier;

import java.io.IOException;
import java.io.InputStream;

import static com.bezzangss.sign.common.exception.ErrorCode.INTERNAL_SERVER_ERROR;

public class InputStreamHandler {
    private final InputStreamSupplier supplier;

    private InputStreamHandler(InputStreamSupplier supplier) {
        this.supplier = supplier;
    }

    public static InputStreamHandler create(InputStreamSupplier supplier) {
        return new InputStreamHandler(supplier);
    }

    public void consume(InputStreamConsumer consumer) {
        try (InputStream inputStream = supplier.get()) {
            consumer.accept(inputStream);
        } catch (IOException e) {
            throw new CommonException(INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
