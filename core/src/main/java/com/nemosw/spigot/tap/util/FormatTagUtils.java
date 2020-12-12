package com.nemosw.spigot.tap.util;

import java.util.UUID;

public final class FormatTagUtils
{

    public static final String TAG_REGEX = "^(§.){8}";

    public static String createRandomTag()
    {
        return toTag(UUID.randomUUID());
    }

    public static boolean isTag(String s)
    {
        return isTag(s, 0);
    }

    public static boolean isTag(String s, int offset)
    {
        if (s.length() < offset + 16)
            return false;

        for (int i = 0; i < 8; i++)
        {
            if (s.charAt(offset + (i << 1)) != '§')
                return false;
        }

        return true;
    }

    public static String toTag(UUID uniqueId)
    {
        return toTag(uniqueId, new StringBuilder()).toString();
    }

    public static StringBuilder toTag(UUID uniqueId, StringBuilder tag)
    {
        write(tag, uniqueId.getMostSignificantBits());
        write(tag, uniqueId.getLeastSignificantBits());

        return tag;
    }

    private static void write(StringBuilder tag, long l)
    {
        char c = '§';

        tag.append(c).append((char) (l >>> 48)).append(c).append((char) (l >>> 32)).append(c).append((char) (l >>> 16)).append(c).append((char) (l));
    }

    public static UUID toUUID(String tag, int offset)
    {
        return new UUID(read(tag, offset), read(tag, offset + 8));
    }

    private static long read(String s, int index)
    {
        return ((long) s.charAt(index + 1) << 48) + ((long) s.charAt(index + 3) << 32) + ((long) s.charAt(index + 5) << 16) + ((long) s.charAt(index + 7));
    }

    private FormatTagUtils()
    {}

}
