package com.nemosw.spigot.tap.profile;

import java.util.UUID;

public interface ProfileSupport
{

    Profile getProfile(String name);

    Profile getProfile(UUID uniqueId);

}
