package pl.bihuniak.functional8p;

public class ATest {
    public String a;
    public String b;
    public String c;

    public ATest(String a, String b, String c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public String toString() {
        return "ATest{" +
            "a='" + a + '\'' +
            ", b='" + b + '\'' +
            ", c='" + c + '\'' +
            '}';
    }
}
