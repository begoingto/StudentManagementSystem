package util;

import java.util.Scanner;
public class InputField {
    public static <T> T setField(Class<T> field, String fieldName){
        Scanner scanner = new Scanner(System.in);
        boolean error;
        T input;
        do {
            System.out.print("=> Enter "+ fieldName +" : ");
            String inputString = scanner.nextLine();
            // Convert input string to the requested type
            input = null;
            try {
                input = field.getConstructor(String.class).newInstance(inputString);
                error=false;
            } catch (Exception e) {
                AppUtil.errorWrapper("Input invalid value");
                error=true;
            }
        }while (error);
        return input;
    }
}
