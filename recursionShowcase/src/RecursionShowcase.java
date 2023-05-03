/* ------------------------------------------------------------------------------------------------------------
 * May 3rd, 2023
 * ------------------------------------------------------------------------------------------------------------
 */
import java.util.ArrayList;
import java.util.Scanner;

public class RecursionShowcase
{
	/*---PSVM---*/
	@SuppressWarnings("unchecked")//Ok
	public static void main(String[] args)
	{
		/*---Declarations---*/
		Scanner scan = new Scanner(System.in);
		
		String rev = "";
		
		int intInputTemp1, intInputTemp2, m1, m2;
		
		/*---Operations---*/
		System.out.print("___RECURSION_SHOWCASE___\n\n~2D (JAGGED) ARRAY INSTANTIATION~\n");
		do//Check for bad input
		{
            System.out.print("# of Rows: ");
            while (!scan.hasNextInt()) 
            {
                System.out.print("\nINVALID INPUT!  Please enter an integer value!\n# of Rows: ");
                scan.nextLine();
            }

            intInputTemp1 = scan.nextInt();
            scan.nextLine();

            if (intInputTemp1 <= 0 || intInputTemp1 >= Integer.MAX_VALUE || intInputTemp1 <= Integer.MIN_VALUE)
                System.out.print("\nINVALID INPUT!  Please enter a positive, reasonable integer value!\n");
        }while (intInputTemp1 <= 0 || intInputTemp1 >= Integer.MAX_VALUE || intInputTemp1 <= Integer.MIN_VALUE);
		
		ArrayList<Integer>[] matrix = new ArrayList[intInputTemp1];//Instantiate new array of ArrayList<Integer> objects
		//Populate array of ArrayLists
		for (int a = 0; a < matrix.length; a++)
		{
		    matrix[a] = new ArrayList<Integer>();//Instantiate each row

		    do //Check for bad input
		    {
		        System.out.print("# of elements in row " + (a + 1) + ": ");//Know how many columns to take input for per row 'a'
		        while (!scan.hasNextInt()) 
		        {
		            System.out.print("\nINVALID INPUT!  Please enter an integer value!\n# of elements in row " + (a + 1) + ": ");
		            scan.nextLine();
		        }

		        intInputTemp1 = scan.nextInt();
		        scan.nextLine();

		        if (intInputTemp1 <= 0 || intInputTemp1 >= Integer.MAX_VALUE || intInputTemp1 <= Integer.MIN_VALUE)
		            System.out.print("\nINVALID INPUT!  Please enter a positive, reasonable integer value!\n");
		    } while (intInputTemp1 <= 0 || intInputTemp1 >= Integer.MAX_VALUE || intInputTemp1 <= Integer.MIN_VALUE);

		    for (int b = 0; b < intInputTemp1; b++)//Populate columns in row 'a'
		    {
		        do 
		        {
		            System.out.print("# in (" + (a + 1) + ", " + (b + 1) + "): ");
		            while (!scan.hasNextInt())
		            {
		                System.out.print("\nINVALID INPUT!  Please enter an integer value!\n# in (" + (a + 1) + ", " + (b + 1) + "): ");
		                scan.nextLine();
		            }

		            intInputTemp2 = scan.nextInt();
		            scan.nextLine();

		            if (intInputTemp2 >= Integer.MAX_VALUE || intInputTemp2 <= Integer.MIN_VALUE)
		                System.out.print("\nINVALID INPUT!  Please enter a reasonable integer value!\n");
		        }while (intInputTemp2 >= Integer.MAX_VALUE || intInputTemp2 <= Integer.MIN_VALUE);

		        matrix[a].add(intInputTemp2);
		    }
		}
		
		int numRow = matrix.length - 1, numCol = matrix[numRow].size() - 1;//Set necessary related values
		
		System.out.print("\n\n~MULTIPLICAND + MULTIPLIER INSTANTIATION~\n");
		do//Check for bad input
		{
            System.out.print("Multiplicand: ");
            while (!scan.hasNextInt()) 
            {
                System.out.print("\nINVALID INPUT!  Please enter an integer value!\nMultiplicand: ");
                scan.nextLine();
            }

            m1 = scan.nextInt();
            scan.nextLine();

            if (m1 >= Integer.MAX_VALUE || m1 <= Integer.MIN_VALUE)
                System.out.print("\nINVALID INPUT!  Please enter a reasonable integer value!\n");
        }while (m1 >= Integer.MAX_VALUE || m1 <= Integer.MIN_VALUE);
		do//Check for bad input
		{
            System.out.print("Multiplier: ");
            while (!scan.hasNextInt()) 
            {
                System.out.print("\nINVALID INPUT!  Please enter an integer value!\nMultiplier: ");
                scan.nextLine();
            }

            m2 = scan.nextInt();
            scan.nextLine();

            if (m2 >= Integer.MAX_VALUE || m2 <= Integer.MIN_VALUE)
                System.out.print("\nINVALID INPUT!  Please enter a reasonable integer value!\n");
        }while (m2 >= Integer.MAX_VALUE || m2 <= Integer.MIN_VALUE);
		
		System.out.print("\n\n~INPUT STRING-TO-BE-REVERSED~\nString: ");
		rev = scan.nextLine();
		while (rev.length() >= Integer.MAX_VALUE || rev.length() <= Integer.MIN_VALUE)//Check for bad input
		{
			System.out.print("\nINVALID INPUT!  Please enter a reasonable String: ");
			rev = scan.nextLine();
		}
		
		int revLength = rev.length() - 1;//Set necessary related values
		
		System.out.print("\n\nRecursive 2D-Array Summation:\n\t--> " + recMatrixSum(matrix, numRow, numCol)
				+ "\n\nRecursive Product:\n\t--> " + recProduct(m1, m2)
				+ "\n\nRecursively-Reversed String:\n\t--> " + recReverseString(rev, revLength));
		
		scan.close();//Close Scanner
	}

	/*---Methods---*/
	//Iterates through each row/column and sums values @ indices; begins @ last column of last row
	public static int recMatrixSum(ArrayList<Integer>[] ints, int nR, int nC)
	{
		int sumOut = 0;
		
		if (nR < 0)//If no more rows, do nothing
			sumOut += 0;
		else//If there are still row indices to be counted
		{
			if (nC >= 0)//If there are still column indices to be counted
				sumOut += (ints[nR].get(nC) + recMatrixSum(ints, nR, nC - 1));//Start @ last column in row and work backwards
			else//If no more columns to be counted
				if (nR - 1 >= 0)//If a previous row exists
					sumOut += recMatrixSum(ints, nR - 1, ints[nR - 1].size() - 1);//Go to previous row and begin @ last column in the row
		}
		
		return sumOut;
	}
	
	//Determines product of a multiplicand and a multiplier based on their inherent values, via recursive summation
	public static int recProduct(int m, int n)
	{
		int prodOut = 0;
		return prodOut -= (n < 0) ? (m - recProduct(m, n + 1)) : (n > 0) ? -(m + recProduct(m, n - 1)) : 0;
	}
	
	//Reverses string by printing char @ last index first; sends next-last index to next recursive call until
	//final char in string is reached
	public static String recReverseString(String rS, int rSL)
	{
		String revOut = "";
		return revOut += (rSL >= 0) ? (rS.charAt(rSL) + recReverseString(rS, rSL - 1)) : "";
	}
}