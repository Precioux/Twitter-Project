package entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * This class defines error
 * @author Samin Mahdipour
 * @version 3.0
 * @since 12.7.2021
 * */
public class Error {
    String errorType;
    String errorCode;

    /**
     * error search
     * @param index data
     */
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

    /**
     * getter
     * @return data
     */
    public String getErrorType() {
        return errorType;
    }

    /**
     * setter
     * @param errorType data
     */
    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    /**
     * getter
     * @return data
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * setter
     * @param errorCode data
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
