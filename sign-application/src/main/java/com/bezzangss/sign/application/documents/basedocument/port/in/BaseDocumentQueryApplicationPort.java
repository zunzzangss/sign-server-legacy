package com.bezzangss.sign.application.documents.basedocument.port.in;

import java.util.Optional;

public interface BaseDocumentQueryApplicationPort {
    Optional<String> findResourceIdByIdAndType(String id, String type);
}
