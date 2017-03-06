import java.io.*;
import java.util.StringTokenizer;

/**
 * Created by baris on 03.03.2017.
 * Source : http://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/
 */
public class FastReader
{
    BufferedReader br;
    StringTokenizer st;

    public FastReader()
    {
        br = new BufferedReader(new
                InputStreamReader(System.in));
    }

    public FastReader(String filepath) throws FileNotFoundException {
        br = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
    }


    String next()
    {
        while (st == null || !st.hasMoreElements())
        {
            try
            {
                st = new StringTokenizer(br.readLine());
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt()
    {
        return Integer.parseInt(next());
    }

    long nextLong()
    {
        return Long.parseLong(next());
    }

    double nextDouble()
    {
        return Double.parseDouble(next());
    }

    String nextLine()
    {
        String str = "";
        try
        {
            str = br.readLine();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return str;
    }
}