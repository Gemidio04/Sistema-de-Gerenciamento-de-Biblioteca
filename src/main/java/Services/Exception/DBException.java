package Services.Exception;

public class DBException extends RuntimeException  {

    public DBException(String msg){
        super(msg);
    }
}
