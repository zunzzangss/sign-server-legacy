package com.bezzangss.sign.common.inputstream.interfaces;

import java.io.IOException;
import java.io.InputStream;

@FunctionalInterface
public interface InputStreamConsumer {
    void accept(InputStream inputStream) throws IOException;
}
