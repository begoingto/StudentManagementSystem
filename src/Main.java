import util.AppUtil;

public class Main {
    public static void main(String[] args) {
        StudentApplication application = new StudentApplication();
        do {
            application.start();
            switch (application.option) {
                case 1 -> application.create();
                case 2 -> application.update();
                case 3 -> application.search();
                case 4 -> application.delete();
                case 5 -> application.show();
                case 6 -> application.stop();
                default -> AppUtil.errorWrapper("Something wrong");
            }
        }while (application.defaultOption.contains(application.option));
    }
}