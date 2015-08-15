import java.util.UUID;

public class JavaInterop {
    public int integer;
    public String string;
    public UUID uuid;

    public JavaInterop(int i, String s, UUID u) {
        integer = i;
        string = s;
        uuid = u;
    }

    public static UUID someRandomUUID() {
        return UUID.randomUUID();
    }

    public String toString() {
        return "JavaInterop(" + Integer.toString(integer) + ",\"" + string + "\"," + uuid.toString() + ")";
    }
}
