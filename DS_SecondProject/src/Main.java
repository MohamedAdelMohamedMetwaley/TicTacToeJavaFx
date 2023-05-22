import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EvaluatePolynomial ev = new EvaluatePolynomial();
        ev.polyLinkedLists.add(new PolyLinkedList(new int[]{3, 2, 1, 0}, new int[]{6, 10, 0, 5}));
        ev.polyLinkedLists.add(new PolyLinkedList(new int[]{2, 1, 0}, new int[]{4, 2, 1}));
        boolean quit = false;
        while (!quit) {
            System.out.println("-----------------------------");
            System.out.println("Current Polynomial Expressions:");
            ev.printExpressions();
            System.out.println("""
                    ------------------------------------------
                    1. Add a polynomial to the list
                    2. Evaluate a polynomial from the list (Second Degree Only)
                    3. Add 2 Polynomials from the list
                    4. Subtract 2 Polynomials
                    5. Multiply 2 Polynomials
                    6. Quit
                    """);
            int choice = sc.nextInt();
            sc.nextLine();
            PolyLinkedList result = null;
            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter Degree of the polynomial: ");
                        ev.createPolynomial(sc.nextInt());
                        sc.nextLine();
                    }
                    case 2 -> {
                        System.out.print("Which Polynomial from the list do you want to evaluate? ");
                        int p = sc.nextInt() - 1;
                        sc.nextLine();
                        double[] solutions = ev.evaluate(ev.polyLinkedLists.get(p));
                        System.out.println("Result: " + solutions[0] + ", " + solutions[1]);
                    }
                    case 3 -> {
                        System.out.print("First Polynomial: ");
                        int p1 = sc.nextInt() - 1;
                        sc.nextLine();
                        System.out.print("Second Polynomial: ");
                        int p2 = sc.nextInt() - 1;
                        sc.nextLine();
                        result = ev.addition(ev.polyLinkedLists.get(p1), ev.polyLinkedLists.get(p2), true);
                        System.out.println("Result: " + result.getPolynomialExpression());
                    }
                    case 4 -> {
                        System.out.print("First Polynomial: ");
                        int p1 = sc.nextInt() - 1;
                        sc.nextLine();
                        System.out.print("Second Polynomial: ");
                        int p2 = sc.nextInt() - 1;
                        sc.nextLine();
                        result = ev.addition(ev.polyLinkedLists.get(p1), ev.polyLinkedLists.get(p2), false);
                        System.out.println("Result: " + result.getPolynomialExpression());
                    }
                    case 5 -> {
                        System.out.print("First Polynomial: ");
                        int p1 = sc.nextInt() - 1;
                        sc.nextLine();
                        System.out.print("Second Polynomial: ");
                        int p2 = sc.nextInt() - 1;
                        sc.nextLine();
                        result = ev.multiply(ev.polyLinkedLists.get(p1), ev.polyLinkedLists.get(p2));
                        System.out.println("Result: " + result.getPolynomialExpression());
                    }
                    case 6 -> quit = true;
                    default -> System.out.println("Please enter a valid choice");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Please choose an expression from the list");
                continue;
            }
            if (choice == 3 || choice == 4 || choice == 5) {
                System.out.println("Add Result To The List? (y/n)");
                char addResult = sc.nextLine().charAt(0);
                if (addResult == 'y')
                    ev.polyLinkedLists.add(result);
                else if (addResult == 'n')
                {}
                else System.out.println("Invalid choice");
            }
        }
    }
}