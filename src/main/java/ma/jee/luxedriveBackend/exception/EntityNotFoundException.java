package ma.jee.luxedriveBackend.exception;

public class EntityNotFoundException extends LuxeDriveException {
    public EntityNotFoundException(String entityName, Long entityId) {
        super(String.format("%s with ID %d not found", entityName, entityId));
    }
}
