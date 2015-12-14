package com.greenlionsoft.pollution.madrid.tools;

import android.content.Context;

public class ResourceUtil {

    private ResourceUtil() {

    }

    /**
     * Gets string given a stringName
     *
     * @param context
     * @param stringName
     * @return
     */
    public static String getStringFromId(Context context, String stringName) {

        String text = "unknown";

        if (null == stringName || stringName.isEmpty()) {
            return text;
        }

        int id = context.getResources().getIdentifier(stringName, "string", context.getPackageName());
        if (id != 0) {
            text = context.getString(id);
        }

        return text;
    }

}
