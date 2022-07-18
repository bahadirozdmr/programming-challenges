import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import string.MinimumDeletion;
import string.Palindrome;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ProgrammingChallengeTest {


   @ParameterizedTest
   @MethodSource("generateCityTrafficSource")
    void cityTrafficTest(String[] strArray){
        CityTraffic traffic=new CityTraffic();
        var response=traffic.findMaximumTraffic(strArray);
        assertNotEquals(response,-1);
    }

    @ParameterizedTest
    @MethodSource("generateWeightedPathSource")
    void weightedPath(String[] strArray) {
       WeightedPath weightedPath=new WeightedPath();
        var response=weightedPath.findMinimumPath(strArray);
        assertNotEquals(response,-1);
    }

    @ParameterizedTest
    @MethodSource("generateFarthestNodeSource")
    void farthestNode(String[] strArray,int actualResponse) {
       FarthestNode farthestNode=new FarthestNode();
       var response=farthestNode.findFarthestNode(strArray);
        assertEquals(actualResponse, response);
    }

    @ParameterizedTest
    @MethodSource("generateVertexConverting")
    void vertexConvering(String[] strArrays,String result) {
       VertexConvering vertexConvering=new VertexConvering();
       var output=vertexConvering.covering(strArrays);
       assertEquals(output,result);

    }

    @ParameterizedTest
    @MethodSource("generateHamiltonianPath")
    void hamiltonianPath(String[] strArrays,String result) {
       HamiltonianPath path=new HamiltonianPath();
       var output=path.findPath(strArrays);
        assertEquals(output,result);
    }

    @ParameterizedTest
    @MethodSource("generateKaprekarsConstant")
    void kaprekarsConstantant(int number,int result) {
       KaprekarsConstant kaprekars=new KaprekarsConstant();
       var output=kaprekars.performKaprekars(number);
        assertEquals(output,result);
    }

    @ParameterizedTest
    @MethodSource("generateMinDeletionSource")
    void generateMinDeletion(String s,int result) {
        var minDeletion=new MinimumDeletion();
        var response=minDeletion.minDeletions(s);
        assertEquals(result,response);
    }

    @ParameterizedTest
    @MethodSource("generatePalindromeSource")
    void generatePalindromeSource(String s,int result) {
       var palindrome=new Palindrome();
       var response=palindrome.minMovesToMakePalindrome(s);
       assertEquals(result,response);
    }

    private static Stream<Arguments> generateCityTrafficSource() {
        return Stream.of(
                Arguments.of((Object) new String[]{"1:[5]", "4:[5]", "3:[5]", "5:[1,4,3,2]", "2:[5,15,7]", "7:[2,8]", "8:[7,38]", "15:[2]", "38:[8]"}));
    }

    private static Stream<Arguments> generateWeightedPathSource(){
       return Stream.of(
                Arguments.of((Object)new String[]{"7","A","B","C","D","E","F","G","A|B|1","A|E|9","B|C|2","C|D|1","D|F|2","E|D|6","F|G|2"}));
    }

    private static Stream<Arguments> generateFarthestNodeSource(){
        return Stream.of(
                Arguments.of(new String[]{"a-b","b-c","b-d"},2),Arguments.of(new String[]{ "b-e","b-c","c-d","a-b","e-f" },4));
    }

    private static  Stream<Arguments> generateVertexConverting(){
        return Stream.of(
                Arguments.of(new String[]{"(A,B,C,D)","(A-B,A-D,B-D,A-C)","(A,B)"},"yes"),Arguments.of(new String[]{"(A,B,C,D)","(A-B,A-D,B-D,A-C)","(C,B)"},"(A-D)"));
    }

    private static  Stream<Arguments> generateHamiltonianPath(){
        return Stream.of(
                Arguments.of(new String[]{"(A,B,C,D)","(A-B,A-D,B-D,A-C)","(C,A,D,B)"},"yes"),Arguments.of(new String[]{"(X,Y,Z,Q)","(X-Y,Y-Q,Y-Z)","(Z,Y,Q,X)"},"Q"),
                Arguments.of(new String[]{"(A,B,C)","(B-A,C-B)","(C,B,A)"},"yes")
                );
    }

    private static  Stream<Arguments> generateKaprekarsConstant(){
        return Stream.of(
                Arguments.of(1495,3),Arguments.of(3524,3),Arguments.of(2111,5),Arguments.of(9831,7)
        );
    }
    private static  Stream<Arguments> generateMinDeletionSource(){
        return Stream.of(
                Arguments.of("aaabbbcc",2)
        );
    }

    private static Stream<Arguments> generatePalindromeSource() {
        return Stream.of(
                Arguments.of("letetl",1),Arguments.of("aabb",2)
        );
    }


}


