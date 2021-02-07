public class Test
{
	public static void main(String[] paramArrayOfString)
	{
		if (paramArrayOfString.length < 2)
		{
			System.out.println("\n\nError - Must have 2 command line arguments\n\nExample:  java Test 20 16 \n\n");

			System.exit(1);
		}

		int i = 0; int j = 0;
		try
		{
			i = Integer.valueOf(paramArrayOfString[0]).intValue();
			j = Integer.valueOf(paramArrayOfString[1]).intValue();
		}
		catch (Exception localException)
		{
			System.out.println("\n\nError - Non-numeric command line arguments\n");
			System.exit(1);
		}

		if (i + j != 36)
		{
			System.out.println("\n\nError - Invalid sum not equal to 36\n");
			System.exit(1);
		}

		if (i == j)
		{
			System.out.println("\n\nError - Arguments are equal\n");
			System.exit(1);
		}

		if ((i < 10) || (j < 10))
		{
			System.out.println("\n\nError - Argument(s) less than 10 \n");
			System.exit(1);
		}

		if (i < 18)
		{
			System.out.println("\n\n" + i * j + "\n\n");
			System.exit(0);
		}
		else
		{
			System.out.println("\n\n" + i * j / (double)(i - j) + "\n\n");
			System.exit(0);
		}
	}
}