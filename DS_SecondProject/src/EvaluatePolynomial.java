import java.util.ArrayList;
import java.util.Scanner;

public class EvaluatePolynomial {
    ArrayList<PolyLinkedList> polyLinkedLists = new ArrayList<>();

    private int[] getDegrees(int degree) {
        int[] degrees = new int[degree + 1];
        for (int i = 0; i < degree; i++) {
            degrees[i] = degree - i;
        }
        return degrees;
    }

    public void createPolynomial(int degree) {
        int[] degrees = getDegrees(degree);
        polyLinkedLists.add(new PolyLinkedList(degrees, getCoefficient(degrees)));
    }

    private int[] getCoefficient(int[] degrees) {
        Scanner sc = new Scanner(System.in);
        int[] coefficients = new int[degrees.length];
        for (int i = 0; i < degrees.length; i++) {
            System.out.println("Enter Coefficient of X^" + degrees[i]);
            coefficients[i] = sc.nextInt();
            sc.nextLine();
        }
        return coefficients;
    }

    public String addition(PolyLinkedList p1, PolyLinkedList p2, boolean add) {
        int[] degrees;
        int[] coefficients;

        int sign = 1;
        if (!add) {
            sign = -1;
        }

        if (p1.size() == p2.size()) {
            degrees = new int[p1.size()];
            coefficients = new int[p1.size()];
            for (int i = 0; i < p1.size(); i++) {
                degrees[i] = p1.get(i).getPower();
                coefficients[i] = p1.get(i).getCoefficient() + sign * p2.get(i).getCoefficient();
            }
        } else {
            PolyLinkedList biggerPoly = p1.size() > p2.size() ? p1 : p2;
            PolyLinkedList smallerPoly = p1.size() < p2.size() ? p1 : p2;
            int difference = biggerPoly.size() - smallerPoly.size();

            degrees = new int[biggerPoly.size()];
            coefficients = new int[biggerPoly.size()];
            for(int i = 0; i < difference; i++){
                degrees[i] = biggerPoly.get(i).getPower();
                coefficients[i] = biggerPoly.get(i).getCoefficient();
            }
            for (int i = difference; i < biggerPoly.size(); i++){
                degrees[i] = biggerPoly.get(i).getPower();
                coefficients[i] = biggerPoly.get(i).getCoefficient() + sign * smallerPoly.get(i-difference).getCoefficient();
            }
        }
        return new PolyLinkedList(degrees, coefficients).getPolynomialExpression();
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

