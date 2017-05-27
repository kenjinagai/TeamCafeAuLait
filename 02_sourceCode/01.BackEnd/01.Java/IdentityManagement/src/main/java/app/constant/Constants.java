package app.constant;

/**
 * Constants.
 *
 * @author Kenji Nagai.
 */
public final class Constants {
    public static final String TEMPLATE = "Hello, %s";
    public static final String XML_MYBATIS_SETTING = "mybatis-config.xml";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final int MAX_TEXT_LENGHT = 15;
    public static final String COOKIE_NAME_CSRF = "XSRF-TOKEN";
    public static final String CMD_PYTHON = "python2";
    public static final String FILE_PYTHON_CARD_READ = "/opt/get-card-id.py";
    public static final int MILLISECOUND_WAIT_CMD = 3000;

    private Constants() {
    }
}
