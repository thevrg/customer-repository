package hu.dpc.edu.rest.entity;

/**
 * Created by vrg on 08/06/16.
 */
public abstract class EntityBase {
    private Long id;
    private String digest;

    private DigestBuilder digestBuilder = new DigestBuilder();

    public EntityBase() {
    }

    public EntityBase(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
        markDirty();
    }

    protected void markDirty() {
        digest = null;
    }

    public String getDigest() {
        if (digest == null) {
            updateDigest();
        }
        return digest;
    }

    private void updateDigest() {
        digestBuilder.update(id);
        updateDigest(digestBuilder);
        digest = digestBuilder.build();
    }

    protected abstract void updateDigest(DigestBuilder digestBuilder);
}
