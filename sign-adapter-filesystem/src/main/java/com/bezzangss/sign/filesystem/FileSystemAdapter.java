package com.bezzangss.sign.filesystem;

import com.bezzangss.sign.application.resources.port.out.ResourceStoragePort;
import com.bezzangss.sign.application.resources.port.out.dto.request.ResourceStorageWriteRequest;
import com.bezzangss.sign.application.resources.port.out.dto.response.ResourceStorageWriteResponse;
import com.bezzangss.sign.common.resource.ResourceStream;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.concurrent.atomic.AtomicLong;

import static com.bezzangss.sign.common.exception.ErrorCode.RESOURCE_WRITE_EXCEPTION;

@RequiredArgsConstructor
@Component
public class FileSystemAdapter implements ResourceStoragePort {
    private final Environment environment;

    @Override
    public String resourceType() {
        return "FILE_SYSTEM";
    }

    @Override
    public ResourceStream read(String source) {
        return ResourceStream.builder()
                .inputStreamHandler(consumer -> consumer.accept(Files.newInputStream(Paths.get(source))))
                .build();
    }

    @Override
    public ResourceStorageWriteResponse write(ResourceStorageWriteRequest resourceStorageWriteRequest) {
        try {
            return this.throwingWrite(resourceStorageWriteRequest);
        } catch (IOException e) {
            throw new FileSystemException(RESOURCE_WRITE_EXCEPTION, e.getMessage());
        }
    }

    private ResourceStorageWriteResponse throwingWrite(ResourceStorageWriteRequest resourceStorageWriteRequest) throws IOException {
        String id = resourceStorageWriteRequest.getId();
        String source = resourceStorageWriteRequest.getSource();
        ResourceStream resourceStream = resourceStorageWriteRequest.getResourceStream();

        Path path = ObjectUtils.isEmpty(source) ? this.getPath(id) : Paths.get(source);

        AtomicLong size = new AtomicLong();
        Files.createDirectories(path.getParent());
        resourceStream.process(consumer -> size.set(Files.copy(consumer, path, StandardCopyOption.REPLACE_EXISTING)));

        return ResourceStorageWriteResponse.builder()
                .source(path.toString())
                .size(size.get())
                .build();
    }

    private Path getPath(String id) {
        LocalDate localDate = Instant.now().atZone(ZoneOffset.UTC).toLocalDate();

        return Paths.get(
                environment.getProperty("com.bezzangss.sign.resource.prefix-path", String.class),
                String.format("%04d", localDate.getYear()),
                String.format("%02d", localDate.getMonthValue()),
                String.format("%02d", localDate.getDayOfMonth()),
                id
        );
    }
}
