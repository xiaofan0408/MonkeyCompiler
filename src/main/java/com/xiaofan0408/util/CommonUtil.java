package com.xiaofan0408.util;

import com.xiaofan0408.object.impl.MError;

public class CommonUtil {
    public static MError newError(String format, Object... a) {
        return new MError(
                String.format(format,a)
        );
    }
}
