public class Server {
    /*    private ObjectInputStream input;
    private ObjectOutputStream output;

    Thread t = new Thread();
        t.start();
    public void run(){
        String localHostName;
        try{
            localHostName = InetAddress.getLocalHost().getCanonicalHostName();
            Socket connection = new Socket(localHostName, 9000);
            output = new ObjectOutputStream(connection.getOutputStream());
            input = new ObjectInputStream(connection.getInputStream());


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void processServerMessages(){
        while (true) {

            try{
                String message = (String) input.readObject();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void jTextFieldActionPerformed(ActionEvent event){
        String msg /*= textField.getText();*/ = null;
        try {
        output.writeObject("\nClient>>> "+msg);

    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    //answerTextField.append("\nClient>>>" + msg)
}
*/
}
