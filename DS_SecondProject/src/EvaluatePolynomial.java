import java.util.ArrayList;
import java.util.Scanner;

public class EvaluatePolynomial {
    ArrayList<PolyLinkedList> polyLinkedLists = new ArrayList<>();

    private int[] getDegrees(int degree) {//fills the degrees (ex: if the degree of the expression is 2, then the expression will be aX^2+bX^1+cX^0)
        int[] degrees = new int[degree + 1];
        for (int i = 0; i < degree; i++) {
            degrees[i] = degree - i;
        }
        return degrees;
    }

    public void createPolynomial(int degree) {//calls the two methods getDegrees & getCoefficient and created the expression, then adds it to the list
        int[] degrees = getDegrees(degree);
        polyLinkedLists.add(new PolyLinkedList(degrees, getCoefficient(degrees)));
    }

    private int[] getCoefficient(int[] degrees) {//gets coefficients of each X in the expression from the user
        Scanner sc = new Scanner(System.in);
        int[] coefficients = new int[degrees.length];
        for (int i = 0; i < degrees.length; i++) {
            System.out.println("Enter Coefficient of X^" + degrees[i]);
            coefficients[i] = sc.nextInt();
            sc.nextLine();
        }
        return coefficients;
    }

    public String addition(PolyLinkedList p1, PolyLinkedList p2, boolean add) {//this handles the addition (& the subtraction, the 'add' variable is false if the operation is subtraction)
        int[] degrees;
        int[] coefficients;

        int sign = 1;
        boolean bigFirst = true;
        if (!add) {//we will use this with subtraction to change the sign of the second operand (to the right of the positive sign)
            sign = -1;
        }

        if (p1.size() == p2.size()) {//the easy case is: the two expressions are the same degree
            degrees = new int[p1.size()];
            coefficients = new int[p1.size()];
            for (int i = 0; i < p1.size(); i++) {
                degrees[i] = p1.get(i).getPower();
                coefficients[i] = p1.get(i).getCoefficient() + (sign * p2.get(i).getCoefficient());
            }
        } else {
            PolyLinkedList biggerDegree = p1.size() > p2.size() ? p1 : p2;//assign the polynomial with the higher degree to this variable
            PolyLinkedList smallerDegree = p1.size() < p2.size() ? p1 : p2;//and the poly with the less degree to this variable
            int difference = biggerDegree.size() - smallerDegree.size();

            degrees = new int[biggerDegree.size()];
            coefficients = new int[biggerDegree.size()];
            if (p1.size() != biggerDegree.size()){
                //this variable will be important in determining which variable will be first (to the left of the negative sign) during the subtraction
                bigFirst = false;
            }
            for(int i = 0; i < difference; i++){//fill the degrees that is in the bigger expression and not in the smaller one
                degrees[i] = biggerDegree.get(i).getPower();
                //if it is a subtraction and p1 is not the bigger expression: multiply the coefficients of p2 by -1 (as if the expression was 0-aX^b)
                coefficients[i] = !add && !bigFirst ? -1 * biggerDegree.get(i).getCoefficient() : biggerDegree.get(i).getCoefficient();
            }
            for (int i = difference; i < biggerDegree.size(); i++){
                degrees[i] = biggerDegree.get(i).getPower();
                int big = biggerDegree.get(i).getCoefficient();//
                int small = smallerDegree.get(i-difference).getCoefficient();
                //if it is a subtraction and the second expression 'p2' is bigger then swap it so that p1 is to the left of the minus
                int result = !add && !bigFirst ? small/*p1*/ - big/*p2*/ : big + sign*small;
                coefficients[i] = result;
            }
        }
        return new PolyLinkedList(degrees, coefficients).getPolynomialExpression();
    }

    public double[] evaluate(PolyLinkedList expression){
        if (expression.size() > 3) {
            System.out.println("Please choose a quadratic equation..");
            return new double[]{0, 0};
        }
        int a = expression.get(0).getCoefficient();
        int b = expression.get(1).getCoefficient();
        int c = expression.get(2).getCoefficient();

        double root = Math.sqrt(b*b - 4*a*c);
        double result1 = (-b + root)/(2*a);
        double result2 = (-b - root)/(2*a);

        return new double[]{result1, result2};
    }

    public void printExpressions(){
        if (polyLinkedLists.isEmpty()) {
            System.out.println("List is empty");
            return;
        }
        for (int i = 0; i < polyLinkedLists.size(); i++){
            System.out.println((i+1)+". "+polyLinkedLists.get(i).getPolynomialExpression());
        }
    }
}

