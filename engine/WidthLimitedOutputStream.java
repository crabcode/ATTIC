package engine;

import java.io.*;

/**
 * A formatted output stream
 * 
 * @author Crabman
 * @version 1.0.0
 */
public class WidthLimitedOutputStream
{
	/**
	 * The maximum character width
	 */
	private int width;
	/**
	 * The main output stream
	 */
	private PrintStream out;
	
	/**
	 * Initialize the formatted output stream
	 * 
	 * @param out The main output stream
	 * @param width The maximum character width
	 */
	public WidthLimitedOutputStream(OutputStream out, int width)
	{
		this.out = new PrintStream(out);
		this.width = width;
	}
	
	/**
	 * Print formatted text to output
	 * 
	 * @param str The text to print
	 */
	public void print(String str)
	{
		// Start with indentation
		int currentWidth = 3;
		out.print("   ");
		
		// Break string into tokens for line wrapping
		StringTokenizer tokenizer = new StringTokenizer(str);
		
		while(tokenizer.hasMoreElements())
		{
			String token = tokenizer.nextToken();

			// If width is 0, the output is disabled
			if (width != 0)
			{
				// Check if adding token exceeds maximum width, break line if so
				if(currentWidth + token.length() > width)
				{
					out.println();
					out.print("   ");
					currentWidth = 3;
				}
			}
			
			// Check for pipes, which are used for manual line breaks
			if (token.endsWith("|"))
			{
				out.print(token.substring(0, token.length() - 1));
				out.println();
				out.print("   ");
				currentWidth = 3;
			}
			else
				out.print(token + " ");
			
			// Add token to current width
			currentWidth += token.length();
		}
		
		out.flush();
	}
	
	/**
	 * Print a line break
	 */
	public void println()
	{
		out.println();
	}
	
	/**
	 * Print formatted text with subsequent line break
	 * 
	 * @param str The text to print
	 */
	public void println(String str)
	{
		print(str);
		out.println();
	}
}
