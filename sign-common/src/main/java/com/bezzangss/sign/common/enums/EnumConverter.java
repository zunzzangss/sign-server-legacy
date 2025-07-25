package com.bezzangss.sign.common.enums;

import com.bezzangss.sign.common.CommonException;

import static com.bezzangss.sign.common.exception.ErrorCode.INTERNAL_SERVER_ERROR;

public final class EnumConverter {
    public static <E extends Enum<E>> E from(Class<E> clazz, String name) {
        for (E enumConstant : clazz.getEnumConstants()) {
            if (enumConstant.name().equalsIgnoreCase(name)) {
                return enumConstant;
            }
        }

        throw new CommonException(INTERNAL_SERVER_ERROR, name);
    }
}
