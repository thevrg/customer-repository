package hu.dpc.edu.rest.repository;

/**
 * Created by vrg on 08/06/16.
 */
public class OptimisticLockException extends RuntimeException {
    private final Class<?> entityType;
    private final Object entityId;

    public OptimisticLockException(Class<?> entityType, Object id) {
        this(entityType, id, null);
    }

    public OptimisticLockException(Class<?> entityType, Object id, Throwable cause) {
        super(entityType.getSimpleName() + " with id=" + id + " was modified by another client", cause);
        this.entityType = entityType;
        this.entityId = id;
    }

    public Class<?> getEntityType() {
        return entityType;
    }

    public Object getEntityId() {
        return entityId;
    }
}
