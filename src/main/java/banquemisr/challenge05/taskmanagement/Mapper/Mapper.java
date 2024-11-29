package banquemisr.challenge05.taskmanagement.Mapper;

public interface Mapper <A,B>{
    public A mapTo(B b);
    public B mapFrom(A a);
}
