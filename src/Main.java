import java.util.ArrayList;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
    	System.out.printf( removeJoiner( "👩‍💻" ) + "\n" );
        System.out.printf( removeJoiner( "👨‍👩‍👧‍👦" ) + "\n" );

    	System.out.printf( addJoiner( "👩💻" ) + "\n" );
    	System.out.printf( addJoiner( "👨👧" ) + "\n" );
    }
    
    public static String removeJoiner( String emoji ){
        IntPredicate zwjFilter = (codePoint) -> { return codePoint == 0x200D ? true : false; };
        IntStream emojiCodePointsFiltered = emoji.codePoints().filter( zwjFilter.negate() );
    	
        return emojiCodePointsFiltered
        		.collect (
        		        StringBuilder :: new, 
        				StringBuilder :: appendCodePoint, 
        				StringBuilder :: append )
        		.toString();
    }
    
    public static String addJoiner( String emojis ){
    	List<String> emojiList = new ArrayList<String>();
    	
    	emojis.codePoints().forEach(cp -> emojiList.add( new StringBuilder().appendCodePoint( cp ).toString() ) );
    	
        return String.join("\u200D", emojiList);
    }     
}
