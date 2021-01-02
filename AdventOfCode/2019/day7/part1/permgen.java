import java.util.Vector;
import java.util.Enumeration;

// because ... https://introcs.cs.princeton.edu/java/23recursion/Permutations.java.html

public class permgen
{
	public static void permutation(String str, Vector<String> options) { 
		permutation("", str, options); 
	}
	
	private static void permutation(String prefix, String str, Vector<String> options) {
		int n = str.length();
		if (n == 0)
			options.add(prefix);
		else {
			for (int i = 0; i < n; i++)
				permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n), options);
		}
	}

    public static final void main (String[] args)
    {
		Vector<String> options = new Vector<String>();

		permutation("01234", options);

		Enumeration<String> iter = options.elements();

		while (iter.hasMoreElements())
		{
			System.out.println(iter.nextElement());
		}
    }
}