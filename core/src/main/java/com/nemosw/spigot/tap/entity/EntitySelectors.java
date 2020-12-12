package com.nemosw.spigot.tap.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.function.Predicate;

/**
 * 개체를 선택 할 때 사용 하는 Predicate 목록입니다.
 *
 * @author Nemo
 */
public final class EntitySelectors
{
    private EntitySelectors() {}

    /**
     * 유효한 {@link Entity}를 선택합니다.
     */
    public static final Predicate<Entity> VALID = Entity::isValid;

    /**
     * 유효한 {@link Item}을 선택합니다.
     */
    public static final Predicate<Entity> ITEM = VALID.and(entity -> entity instanceof Item);

    /**
     * 유효한 {@link Player}를 선택합니다.
     */
    public static final Predicate<Entity> PLAYER = VALID.and(entity -> entity instanceof Player);

    /**
     * 유효한 {@link LivingEntity}를 선택합니다.
     */
    public static final Predicate<Entity> LIVING = VALID.and(entity -> entity instanceof LivingEntity);

    /**
     * {@link Player}를 제외하고 유효한 {@link LivingEntity}를 선택합니다.
     */
    public static final Predicate<Entity> CREATURE = VALID.and((entity) -> entity instanceof LivingEntity && !(entity instanceof Player));
}
