public class MyclassImpl implements MyInterface {

    private MyInterface MyInterface;
    @Override
    public MyInterface printMe() {
        System.out.println("This is Print me method from MyInterface");
        return  MyInterface;
    }
}
