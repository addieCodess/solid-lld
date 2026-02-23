public abstract class NotificationSender {

    protected final AuditLog audit;
    private final NotificationPreprocessor prep = new NotificationPreprocessor();

    protected NotificationSender(AuditLog audit) {
        this.audit = audit;
    }

    public final void send(Notification n) {
        Notification clean = prep.normalize(n);
        deliver(clean);
    }

    protected abstract void deliver(Notification n);
}
