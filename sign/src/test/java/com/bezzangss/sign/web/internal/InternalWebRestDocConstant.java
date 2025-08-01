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
}
