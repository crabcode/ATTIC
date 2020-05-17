package engine;

/**
 * Breaks a string into tokens for parsing
 * 
 * @author Crabman
 * @version 1.0.0
 */
public class StringTokenizer
{
	/**
	 * Array of tokens
	 */
	private String[] tokens;
	/**
	 * Pointer to the current token
	 */
	private int pointer;
	
	/**
	 * Initializes StringTokenizer with plural detection disabled by default
	 * 
	 * @param str The string to tokenize
	 */
	public StringTokenizer(String str)
	{
		this(str, false);
	}

	/**
	 * Initializes StringTokenizer
	 * 
	 * @param str The string to tokenize
	 * @param processed Whether the string should be processed for plural detection
	 */
	public StringTokenizer(String str, boolean processed)
	{
		tokens = str.split("\\s");
		pointer = 0;
		
		if (processed)
		{
			for (int i = 0; i < tokens.length; i++)
				tokens[i] = process(tokens[i]);
		}
	}
	
	/**
	 * Gets the next token and increments the pointer
	 * 
	 * @return Next token
	 */
	public String nextToken()
	{
		if (tokens != null && pointer >= 0 && hasMoreElements())
		{
			String token = tokens[pointer];
			pointer++;
			
			return token;
		}
		else
			return null;
	}
	
	/**
	 * Checks whether there are more tokens
	 * 
	 * @return Whether more tokens exist
	 */
	public boolean hasMoreElements()
	{
		return pointer < tokens.length;
	}
	
	/**
	 * Gets the token count
	 * 
	 * @return Token count
	 */
	public int countTokens()
	{
		return tokens.length;
	}
	
	/**
	 * Removes trailing S's for plural detection
	 * 
	 * @param str String to process
	 * @return The processed string
	 */
	public static String process(String str)
	{
		if (str.endsWith("s") || str.endsWith("S"))
			str = str.substring(0, str.length() - 1);

		return str;
	}
}
