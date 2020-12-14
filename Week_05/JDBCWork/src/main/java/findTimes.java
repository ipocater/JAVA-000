public class findTimes {

    public static void main(String[] args){
        String mainStr = "12323454556767";
        String subStr = "23?45?67";
        int count = findCounts(mainStr,subStr);
        System.out.println("次数是："+count);
    }

    private static int findCounts(String mainStr, String subStr) {
        int result = 0;
        String[] subArr = subStr.split("\\?");
        if (subArr.length == 1){
            return count(mainStr,subStr);
        }
        while (mainStr.indexOf(subArr[0]) != -1){
            mainStr = mainStr.substring(mainStr.indexOf(subArr[0])+subArr[0].length());
            result += findCounts(mainStr, subStr.substring(subStr.indexOf("?") + 1));
        }

        return result;
    }

    public static int count(String text,String sub){
        int count =0, start =0;
        while((start=text.indexOf(sub,start))>=0){
            start += sub.length();
            count ++;
        }
        return count;
    }
}
