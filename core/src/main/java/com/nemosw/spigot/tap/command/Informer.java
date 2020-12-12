package com.nemosw.spigot.tap.command;

public interface Informer<T>
{
    void information(int index, StringBuilder builder, T o);
}
