package pinorobotics.jrosmoveit.exceptions;

public class JRosMoveItException extends Exception {

    private static final long serialVersionUID = 1L;

    public JRosMoveItException(String fmt, Object... args) {
        super(String.format(fmt, args));
    }

    public JRosMoveItException(Exception e) {
        super(e);
    }
}
