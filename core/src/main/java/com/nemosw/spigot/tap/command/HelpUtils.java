package com.nemosw.spigot.tap.command;

public class HelpUtils
{
    public static String createHelp(String label, String componentLabel)
    {
        return createHelp(new StringBuilder(), label, componentLabel).toString();
    }

    public static String createHelp(String label, String componentLabel, String usage)
    {
        return createHelp(new StringBuilder(), label, componentLabel, usage).toString();
    }

    public static String createHelp(String label, String componentLabel, String usage, String description)
    {
        return createHelp(new StringBuilder(), label, componentLabel, usage, description).toString();
    }

    public static StringBuilder createHelp(StringBuilder builder, String label, String componentLabel)
    {
        return builder.append("§r/").append(label).append(" §6").append(componentLabel);
    }

    public static StringBuilder createHelp(StringBuilder builder, String label, String componentLabel, String usage)
    {
        createHelp(builder, label, componentLabel);

        if (usage != null)
            builder.append(" §r").append(usage);

        return builder;
    }

    public static StringBuilder createHelp(StringBuilder builder, String label, String componentLabel, String usage, String description)
    {
        createHelp(builder, label, componentLabel, usage);

        if (description != null)
            builder.append(" §r").append(description);

        return builder;
    }
}
