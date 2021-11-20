package Handlers;

public class Action {
    public void wait(int seconds) {
        try {
            Thread.sleep(10000);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public boolean redo(UnsafeMethod method, int tries) {
        if (tries > 0) {
            System.out.println("Tries left: "+tries);
            try {
                method.execute();
                return true;
            } catch (Exception err) {
                wait(5);
                redo(method, tries-1 );
            }
        }
        return false;
    }
}