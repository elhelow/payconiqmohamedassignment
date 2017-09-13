package com.payconiq.mohamedassignment.utils;

import java.util.List;

/**
 * Created by Mohamed Hemdan on 9/9/2017.
 */

public class ObjectUtil {
    public static boolean isEmpty(String string) {
        return string == null || string.length() == 0;
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isEmptyList(List list) {
        return list == null || list.isEmpty();
    }
}
