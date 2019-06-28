package ak.quiz.quiz.model;

public interface EmailSender {
    boolean sendEmail(String to, String subject, String content);
}