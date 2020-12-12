package com.nemosw.spigot.tap.packet;

import com.github.noonmaru.math.Vector;
import com.github.noonmaru.math.VectorSpace;
import com.nemosw.spigot.tap.Effect;
import com.nemosw.spigot.tap.Particle;
import com.nemosw.spigot.tap.firework.FireworkEffect;
import com.nemosw.spigot.tap.sound.Sound;
import com.nemosw.spigot.tap.sound.SoundCategory;

public interface EffectPacket
{

    Packet effect(Effect effect, int x, int y, int z, int data);

    Packet explosion(double x, double y, double z, float radius, VectorSpace records, Vector push);

    Packet particle(Particle particle, float x, float y, float z, float offsetX, float offsetY, float offsetZ, float particleData, int particleCount, int... data);

    Packet firework(FireworkEffect firework, double x, double y, double z);

    @Deprecated
    default Packet sound(Sound sound, SoundCategory category, double x, double y, double z, float volume, float pitch)
    {
        return namedSound(sound, category, x, y, z, volume, pitch);
    }

    Packet namedSound(Sound sound, SoundCategory category, double x, double y, double z, float volume, float pitch);

    Packet customSound(String sound, SoundCategory category, double x, double y, double z, float volume, float pitch);

    Packet stopSound(SoundCategory category, String sound);

    Packet thunderbolt(double x, double y, double z);

}
