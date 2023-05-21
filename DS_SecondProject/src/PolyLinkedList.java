public class PolyLinkedList {
    class Node {
        private final int coefficient, power;
        Node next;

        public Node(int coefficient, int power) {
            this.coefficient = coefficient;
            this.power = power;
            next = null;
        }

        public int getCoefficient() {
            return coefficient;
        }

        public int getPower() {
            return power;
        }
    }

    private Node head, tail;
    private final String polynomialExpression;
    private int size;

    public PolyLinkedList(int[] degrees, int[] coefficients) {
        head = tail = null;
        polynomialExpression = fillList(degrees, coefficients);
        size = degrees.length;
    }

    public void add(int coefficient, int power) {
        if (isEmpty()) {
            head = tail = new Node(coefficient, power);
        } else {
            tail.next = new Node(coefficient, power);
            tail = tail.next;
        }
        size++;
    }

    public Node get(int index){
        Node pointer = head;

        for (int i = 0; i < index;i++) {
            pointer = pointer.next;
        }
        return pointer;
    }

    private String fillList(int[] degrees, int[] coefficients) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < degrees.length; i++) {
            add(coefficients[i], degrees[i]);
            if ((i + 1) == degrees.length) {//if we reached the last element don't append a '+' in the end
                sb.append(coefficients[i]);
                break;
            }
            sb.append(coefficients[i]).append("X");
            if (degrees[i] != 1)
                sb.append("^").append(degrees[i]);
            if(coefficients[i+1] >= 0)
                sb.append("+");
        }
        return sb.toString();
    }

    public String getPolynomialExpression() {
        return polynomialExpression;
    }



    public int size(){
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }
}

