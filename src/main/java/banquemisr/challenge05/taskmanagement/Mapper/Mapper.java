package banquemisr.challenge05.taskmanagement.mapper;

public interface Mapper <A,B>{
    public A mapTo(B b);
    public B mapFrom(A a);
}
