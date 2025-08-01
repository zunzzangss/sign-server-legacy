package com.bezzangss.sign.common.exception;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ErrorCodeAsciidoc {
    public static void main(String[] args) throws IOException {
        Path output = Paths.get("../sign/src/adoc/error-code.adoc");
        Files.createDirectories(output.getParent());

        try (BufferedWriter writer = Files.newBufferedWriter(output)) {
            writer.write("++++\n");
            writer.write("<details>\n");
            writer.write("<summary>보기</summary>\n");
            writer.write("++++\n\n");

            writer.write("[cols=\"1,3,1\", options=\"header\"]\n");
            writer.write("|===\n");
            writer.write("|code |message |name\n");

            for (ErrorCode errorCode : ErrorCode.values()) {
                writer.write("|" + errorCode.getCode() + " |" + errorCode.getMessage().replace("\n", " ") + " |" + errorCode.name() + "\n");
            }

            writer.write("|===\n");

            writer.write("++++\n");
            writer.write("</details>\n");
            writer.write("++++\n");
        }
    }
}
