package com.bezzangss.sign.web.internal;

public class InternalWebRestDocConstant {
    public static final String SUCCESS = "성공 여부";
    public static final String ERROR = "에러";
    public static final String CONTENTS = "본문";

    public static class Common {
        public static final String REQUIRED = "[.required]#[필수]# ";
        public static final String OPTIONAL = "[.optional]#[선택]# ";

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

    public static class TemplateDocument {
        public static final String TEMPLATE_DOCUMENT = "서식 문서 (Template Document)";
        public static final String ID = "서식 문서 (Template Document) 아이디";
        public static final String NAME = "서식 문서 (Template Document) 명";
        public static final String DESCRIPTION = "설명";
        public static final String STATUS = "진행 상태 +\nNONE : 진행 상태 없음 +\nDELETED : 삭제";
        public static final String CREATED_AT = Common.CREATED_AT;
        public static final String LAST_MODIFIED_AT = Common.LAST_MODIFIED_AT;
    }
}
