package hu.dpc.edu.rest.repository;

/**
 * Created by vrg on 08/06/16.
 */
public class EntityNotFoundException extends RuntimeException {
    private final Class<?> entityType;
    private final Object entityId;

    public EntityNotFoundException(Class<?> entityType, Object id) {
        this(entityType, id, null);
    }

    public EntityNotFoundException(Class<?> entityType, Object id, Throwable cause) {
        super(entityType.getSimpleName() + " not found with id=" + id, cause);
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
