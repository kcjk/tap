package com.nemosw.spigot.tap.profile;

import java.util.UUID;

public interface Profile
{

    UUID getUniqueId();

    String getName();

    boolean isLegacy();

    String toString();

}
