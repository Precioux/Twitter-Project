package entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Error {
    String errorType;
    String errorCode;

    public void errorSearch(int index)
    {
        File errors=new File("./Errors/"+index+".txt");
        Scanner scanner=null;
        try {
            scanner=new Scanner(errors).useDelimiter("\n");
            errorType=scanner.next();
            errorCode=Integer.toString(index);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
