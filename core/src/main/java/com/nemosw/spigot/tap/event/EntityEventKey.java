package com.nemosw.spigot.tap.event;

final class EntityEventKey
{

    private Class<?> eventClass;

    private EntityExtractor<?> extractor;

    private int hashCode;

    EntityEventKey set(Class<?> eventClass, EntityExtractor<?> extractor)
    {
        this.eventClass = eventClass;
        this.extractor = extractor;
        this.hashCode = eventClass.hashCode() ^ extractor.hashCode();

        return this;
    }

    @Override
    public int hashCode()
    {
        return this.hashCode;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof EntityEventKey)
        {
            EntityEventKey other = (EntityEventKey) obj;

            return this.eventClass == other.eventClass && this.extractor == other.extractor;
        }

        return false;
    }

}
