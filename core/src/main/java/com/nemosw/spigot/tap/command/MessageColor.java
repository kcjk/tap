package com.nemosw.spigot.tap.command;

public final class MessageColor
{
    public static String color(String s)
    {
        return replaceColorCode('&', '§', s);
    }

    public static String strip(String s)
    {
        return replaceColorCode('§', '&', s);
    }

    private static String replaceColorCode(char from, char to, String s)
    {
        int i = s.indexOf(from);

        if (i == -1)
            return s;

        char[] b = s.toCharArray();
        int length = b.length - 1;

        while (i < length)
        {
            if ((b[i] == from) && ("0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(b[(i + 1)]) > -1))
            {
                b[i] = to;
                b[(i + 1)] = Character.toLowerCase(b[(i + 1)]);
            }

            i++;
        }

        return new String(b);
    }

    private MessageColor()
    {}
}
