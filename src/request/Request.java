package request;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class Request {

    protected Scanner scanner = new Scanner(System.in);
    protected ArrayList<Pattern> commandPattern = new ArrayList<>();
    protected boolean isCommandPatternsInitialized = false;

}
