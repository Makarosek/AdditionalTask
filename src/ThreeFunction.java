import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface ThreeFunction<A, B, C, R> {
    R apply(A a, B b, C c);
}
