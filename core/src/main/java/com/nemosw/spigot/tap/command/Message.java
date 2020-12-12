package com.nemosw.spigot.tap.command;

public final class Message
{
    public static final String HELP_DESCRIPTION = "명령의 도움말을 확인합니다.";
    public static final String NOT_EXISTS_PERFORMABLE_COMMAND = "사용 가능한 명령이 없습니다.";
    public static final String NO_PERMISSION = "당신은 이 명령을 실행할 권한이 없습니다.";
    public static final String NO_COMPONENT = "알 수 없는 명령입니다. 혹시 이 명령을 찾으세요?";
    public static final String CANNOT_PERFORM_IN_CONSOLE = "콘솔에서 사용 할 수 없는 명령입니다.";

    public static String createErrorMessage(String label, String componentLabel, Throwable t, String className)
    {
        StringBuilder err = new StringBuilder(64).append('/').append(label).append(" §6").append(componentLabel)
                .append(" §r명령을 실행하는 도중 오류가 발생했습니다. \n§c").append(t.getClass().getName()).append(": ").append(t.getLocalizedMessage())
                .append("§7");

        StackTraceElement[] elements = t.getStackTrace();

        for (StackTraceElement element : elements)
        {
            if (element.getClassName().equals(className))
                break;

            err.append("\nat ").append(element);
        }

        return err.toString();
    }

    private Message() {}
}
