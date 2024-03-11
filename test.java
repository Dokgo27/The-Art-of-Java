import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
    public static void main(String[] args) {
        String input = "This is a sample string with a link: <a href=\"https://example.com\">Link</a> <a href=\\\"https://asdz.com\\\">Link</a>";
        Pattern pattern = Pattern.compile("<a\\s+href\\s*=\\s*\"?(.*?)[\"|>]");
        Matcher matcher = pattern.matcher(input);
        
        ArrayList<String> linkList = new ArrayList<>();

        while(matcher.find()){
            String link = matcher.group(1).trim();
            if(!linkList.contains(link)){
                System.out.println("Found link: " + link);
                linkList.add(link);
            }
        }
        /*
        if (matcher.find()) {
            String link = matcher.group(1).trim();

            while(true){
                if(linkList.contains(link)){
                    break;
                }
                System.out.println("Found link: " + link);
                linkList.add(link);
                link = matcher.group(1).trim();
            }

        } else {
            System.out.println("No link found.");
        }
         */
    }
}
