package com.bezzangss.sign.common.mapstruct;

import java.util.Optional;

public interface CommonMapper {
    default <T> T map(Optional<T> optional) {
        return optional == null ? null : optional.orElse(null);
    }

    default <T> Optional<T> map(T t) {
        return Optional.ofNullable(t);
    }
}
