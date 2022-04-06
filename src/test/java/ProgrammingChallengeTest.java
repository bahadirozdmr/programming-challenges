import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProgrammingChallengeTest {


   @ParameterizedTest
   @MethodSource("generateCityTrafficSource")
    void cityTrafficTest(String[] strArray){
        CityTraffic traffic=new CityTraffic();
        var response=traffic.findMaximumTraffic(strArray);
        assertNotEquals(response,-1);
    }

    private static Stream<Arguments> generateCityTrafficSource() {
        return Stream.of(
                Arguments.of((Object) new String[]{"1:[5]", "4:[5]", "3:[5]", "5:[1,4,3,2]", "2:[5,15,7]", "7:[2,8]", "8:[7,38]", "15:[2]", "38:[8]"}));
    }
}


