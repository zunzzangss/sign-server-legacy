package com.bezzangss.sign.filesystem;

import com.bezzangss.sign.application.storage.port.out.StoragePort;
import com.bezzangss.sign.application.storage.port.out.dto.response.StorageWriteResponse;
import com.bezzangss.sign.common.inputstream.InputStreamHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import static com.bezzangss.sign.common.exception.ErrorCode.RESOURCE_WRITE_EXCEPTION;

@RequiredArgsConstructor
@Component
public class FileSystemAdapter implements StoragePort {
    private final Environment environment;

    @Override
    public String getStorageType() {
        return "FILE_SYSTEM";
    }

    @Override
    public InputStreamHandler read(String source) {
        return InputStreamHandler.create(() -> Files.newInputStream(Paths.get(source)));
    }

    @Override
    public StorageWriteResponse write(InputStreamHandler inputStreamHandler) {
        try {
            return this.throwingWrite(inputStreamHandler);
        } catch (IOException e) {
            throw new FileSystemException(RESOURCE_WRITE_EXCEPTION, e.getMessage());
        }
    }

    private StorageWriteResponse throwingWrite(InputStreamHandler inputStreamHandler) throws IOException {
        Path path = this.getPath();
        Files.createDirectories(path.getParent());

        AtomicLong size = new AtomicLong();
        inputStreamHandler.consume(inputStream -> size.set(Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING)));

        return StorageWriteResponse.builder()
                .source(path.toString())
                .size(size.get())
                .build();
    }

    private Path getPath() {
        LocalDate localDate = Instant.now().atZone(ZoneOffset.UTC).toLocalDate();

        return Paths.get(
                environment.getProperty("com.bezzangss.sign.storage.file-system.prefix-path", String.class),
                String.format("%04d", localDate.getYear()),
                String.format("%02d", localDate.getMonthValue()),
                String.format("%02d", localDate.getDayOfMonth()),
                UUID.randomUUID().toString()
        );
    }
}
