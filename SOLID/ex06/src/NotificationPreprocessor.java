public class NotificationPreprocessor {

    public Notification normalize(Notification n) {

        if (n == null)
            throw new IllegalArgumentException("notification cannot be null");

        String subject = n.subject == null ? "" : n.subject;
        String body = n.body == null ? "" : n.body;
        String email = n.email == null ? "" : n.email;
        String phone = n.phone == null ? "" : n.phone;

        return new Notification(subject, body, email, phone);
    }
}
