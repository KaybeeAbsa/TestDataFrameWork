package Basic;

public class Slipts {

    private static float parse(String s) {
        String str = s.substring(s.indexOf('(')+1, s.length()-1);
        String[] numbers = str.split(",");
        return Float.parseFloat(numbers[1]) - Float.parseFloat(numbers[0]);

    }

}
