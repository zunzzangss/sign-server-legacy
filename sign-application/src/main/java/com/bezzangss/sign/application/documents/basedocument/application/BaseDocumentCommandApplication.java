package com.bezzangss.sign.application.documents.basedocument.application;

import com.bezzangss.sign.application.documents.basedocument.application.bridge.BaseDocumentCommandApplicationBridge;
import com.bezzangss.sign.application.documents.basedocument.port.in.BaseDocumentCommandApplicationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Component
public class BaseDocumentCommandApplication implements BaseDocumentCommandApplicationPort, BaseDocumentCommandApplicationBridge {
}
