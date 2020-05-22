
public class StringMatchAlgorithm_BF {


    public static int findSubString(String subString, String mainString) {
        if (isBlanK(subString) || isBlanK(mainString)){
            return -1;
        }
        char[] mainChars = mainString.toCharArray();
        char[] subChars = subString.toCharArray();
        int intervalCount = mainChars.length - subChars.length;
        for (int i =0; i <= intervalCount; i++){
            boolean isMatch = true;
            for (int j = 0; j < subChars.length ; j++){
                if (mainChars[j+i] != subChars[j]){
                    isMatch = false;
                }
            }
            if (isMatch){
                return i;
            }
        }
        return -1;
    }


    public static boolean isBlanK(String str) {
        if (str == null || str.length() == 0 || "".equals(str) || "".equals(str.trim())) {
            return true;
        } else {
            return false;
        }
    }
    public static void main(String[] args){
        String sub = "saga";
        String mainStr = "asgasgsagasgasfsagasfasfsfsdgsd";
        System.out.println(findSubString(sub,mainStr));
    }
}
