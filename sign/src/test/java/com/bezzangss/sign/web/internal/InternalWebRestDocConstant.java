package com.bezzangss.sign.web.internal;

public class InternalWebRestDocConstant {
    public static final String SUCCESS = "성공 여부";
    public static final String ERROR = "에러";
    public static final String CONTENTS = "본문";

    public static class Common {
        public static final String REQUIRED = "[.required]#[필수]# ";
        public static final String OPTIONAL = "[.optional]#[선택]# ";

        public static final String EMAIL = "이메일";
        public static final String CELL_PHONE = "휴대폰 번호";

        public static final String CREATED_AT = "생성일시 (UTC Timezone Timestamp)";
        public static final String LAST_MODIFIED_AT = "최종수정일시 (UTC Timezone Timestamp)";
    }

    public static class Resource {
        public static final String RESOURCE = "리소스 (Resource)";
        public static final String ID = "리소스 (Resource) 아이디";
        public static final String TYPE = "타입 +\nFILE_SYSTEM : 파일 시스템";
        public static final String SOURCE = "소스";
        public static final String SIZE = "사이즈";
        public static final String CREATED_AT = Common.CREATED_AT;
        public static final String LAST_MODIFIED_AT = Common.LAST_MODIFIED_AT;
    }

    public static class BaseDocument {
        public static final String BASE_DOCUMENT = "기초 문서 (Base Document)";
        public static final String ID = "기초 문서 (Base Document) 아이디";
        public static final String TYPE = "타입 +\nTEMPLATE_DOCUMENT : " + TemplateDocument.TEMPLATE_DOCUMENT;
    }

    public static class TemplateDocument {
        public static final String TEMPLATE_DOCUMENT = "서식 문서 (Template Document)";
        public static final String ID = "서식 문서 (Template Document) 아이디";
        public static final String NAME = "서식 문서 (Template Document) 명";
        public static final String DESCRIPTION = "설명";
        public static final String STATUS = "진행 상태 +\nNONE : 진행 상태 없음 +\nDELETED : 삭제";
        public static final String CREATED_AT = Common.CREATED_AT;
        public static final String LAST_MODIFIED_AT = Common.LAST_MODIFIED_AT;
    }

    public static class MetaDocument {
        public static final String META_DOCUMENT = "메타 문서 (Meta Document)";
        public static final String ID = "메타 문서 (Meta Document) 아이디";
        public static final String TYPE = "타입 +\nSTANDARD_DOCUMENT : " + StandardDocument.STANDARD_DOCUMENT + " +\nGROUP_DOCUMENT : " + GroupDocument.GROUP_DOCUMENT;
    }

    public static class StandardDocument {
        public static final String STANDARD_DOCUMENT = "일반 문서 (Standard Document)";
        public static final String ID = "일반 문서 (Standard Document) 아이디";
        public static final String NAME = "일반 문서 (Standard Document) 명";
        public static final String DESCRIPTION = "설명";
        public static final String CREATED_AT = Common.CREATED_AT;
        public static final String LAST_MODIFIED_AT = Common.LAST_MODIFIED_AT;
    }

    public static class GroupDocument {
        public static final String GROUP_DOCUMENT = "그룹 문서 (Group Document)";
        public static final String ID = "그룹 문서 (Group Document) 아이디";
        public static final String NAME = "그룹 문서 (Group Document) 명";
        public static final String DESCRIPTION = "설명";
        public static final String CREATED_AT = Common.CREATED_AT;
        public static final String LAST_MODIFIED_AT = Common.LAST_MODIFIED_AT;
    }

    public static class Document {
        public static final String DOCUMENT = "문서 (Document)";
        public static final String ID = "문서 (Document) 아이디";
        public static final String NAME = "문서 (Document) 명";
        public static final String DESCRIPTION = "설명";
        public static final String STATUS = "진행 상태 +\nNONE : 진행 상태 없음 +\nPROCESSING : 진행 중 +\nCOMPLETED : 완료 +\nDELETED : 삭제";
        public static final String META_DOCUMENT_TYPE = MetaDocument.TYPE;
        public static final String META_DOCUMENT_ID = MetaDocument.ID;
        public static final String CREATED_AT = Common.CREATED_AT;
        public static final String LAST_MODIFIED_AT = Common.LAST_MODIFIED_AT;
    }

    public static class Publisher {
        public static final String PUBLISHER = "발행자 (Publisher)";
        public static final String ID = "발행자 (Publisher) 아이디";
        public static final String NAME = "발행자 (Publisher) 명";
        public static final String EMAIL = Common.EMAIL;
        public static final String CELL_PHONE = Common.CELL_PHONE;
        public static final String DOCUMENT_ID = Document.ID;
        public static final String CREATED_AT = Common.CREATED_AT;
        public static final String LAST_MODIFIED_AT = Common.LAST_MODIFIED_AT;
    }

    public static class Signer {
        public static final String SIGNER = "서명자 (Signer)";
        public static final String ID = "서명자 (Signer) 아이디";
        public static final String NAME = "서명자 (Signer) 명";
        public static final String EMAIL = Common.EMAIL;
        public static final String CELL_PHONE = Common.CELL_PHONE;
        public static final String ORDER = "순서";
        public static final String STATUS = "진행 상태 +\nNONE : 진행 상태 없음 +\nWAITING : 대기 중 +\nREADY : 서명 준비 +\nSIGNED : 서명 완료";
        public static final String DOCUMENT_ID = Document.ID;
        public static final String CREATED_AT = Common.CREATED_AT;
        public static final String LAST_MODIFIED_AT = Common.LAST_MODIFIED_AT;
    }

    public static class Cc {
        public static final String CC = "참조자 (Cc)";
        public static final String ID = "참조자 (Cc) 아이디";
        public static final String NAME = "참조자 (Cc) 명";
        public static final String EMAIL = Common.EMAIL;
        public static final String CELL_PHONE = Common.CELL_PHONE;
        public static final String DOCUMENT_ID = Document.ID;
        public static final String CREATED_AT = Common.CREATED_AT;
        public static final String LAST_MODIFIED_AT = Common.LAST_MODIFIED_AT;
    }
}
