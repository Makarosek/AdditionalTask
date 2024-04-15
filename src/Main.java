import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {

        ThreePredicate<Object, Object, Object> condition = (x,y,z) -> {
            return (!Objects.isNull(x) && !Objects.isNull(y) && !Objects.isNull(z));
        };
        ThreeFunction<Object, Object, Object, Integer> ifTrue = (x, y, z) ->{
            return x.toString().length() + y.toString().length() + z.toString().length();
        };
        ThreeFunction<Object, Object, Object, Integer> ifFalse = (x,y,z) -> -1;

        ThreeFunction<Object, Object, Object, Integer> safeStringsLenght = ternaryOperator(condition, ifTrue, ifFalse);

        int result1 = safeStringsLenght.apply("1", "22", "333");
        int result2 = safeStringsLenght.apply("1", 2, new String());
        int result3 = safeStringsLenght.apply("1", "22", null);

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
    }

    public static <A, B, C, R> ThreeFunction<A, B, C, R> ternaryOperator(
            ThreePredicate<? super A, ? super B, ? super C> condition,
            ThreeFunction<A, B, C, R> ifTrue,
            ThreeFunction<A, B, C, R> ifFalse
    ){
        if(condition == null || ifTrue == null || ifFalse == null)
            throw new NullPointerException();

        return (arg1, arg2, arg3) -> {
            if(condition.test(arg1, arg2, arg3))
                return ifTrue.apply(arg1, arg2, arg3);
            else
                return ifFalse.apply(arg1, arg2, arg3);
        };
    };

}