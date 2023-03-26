import util.AppUtil;
import util.Action;
import util.InputField;
import util.Student;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentApplication extends AppUtil implements Action {
    public ArrayList<Student> students;
    public Integer option;
    public final ArrayList<Integer> defaultOption;
    public StudentApplication() {
        this.students = new ArrayList<>(){{
            add(new Student(1,"Long Dara","Male",19,"C++",80.50));
            add(new Student(2,"Jonh Smith","Male",20,"Java",75.0));
            add(new Student(3,"Kong Thida","Female",18,"C#",99.50));
        }};
        this.defaultOption = new ArrayList<>(){{
            for (int i=1;i<7;i++){
                add(i);
            }
        }};
    }

    public void start(){
        super.menu();
        do {
            this.option = InputField.setField(Integer.class, "menu of option");
            if (!defaultOption.contains(option)){
                AppUtil.errorWrapper("invalid option should be 1-6");
            }
        }while (!defaultOption.contains(option));
    }

    @Override
    public void create() {
        super.topMessage("Insert student");
        Student student = new Student();
        do {
            student.id = InputField.setField(Integer.class, "Id");
            if (this.checkStudentId(student.id)){
                AppUtil.errorWrapper("student id:"+ student.id +" already exist");
            }
        }while (this.checkStudentId(student.id));

        student.name = InputField.setField(String.class, "Name");
        student.gender = InputField.setField(String.class, "gender");
        student.age = InputField.setField(Integer.class, "age");
        student.className = InputField.setField(String.class, "Class Name");
        student.score = InputField.setField(Double.class, "score");
        this.students.add(student);
        super.successMessage("Insert");
        super.enterContinue();
    }

    @Override
    public void show() {
        super.topMessage("Show students information");
        System.out.println(this.formatHeader());
        if (students.isEmpty()){
            errorWrapper("The students is empty.");
            super.enterContinue();
            return;
        }
        for (Student student: students){
            System.out.println(this.formatStudent(student));
        }
        super.enterContinue();
    }

    @Override
    public void search() {
        if (students.isEmpty()){
            AppUtil.errorWrapper("The students is empty.");
            super.enterContinue();
            return;
        }
        ArrayList<Integer> defaultSearchOption= new ArrayList<>(List.of(1,2,3,4,5));
        Integer sOption;
        do {
            super.topMessage("Search students information");
            super.searchMenu();
            do {
                sOption = InputField.setField(Integer.class, "search option 1-5");
                if (!defaultSearchOption.contains(sOption)){
                    AppUtil.errorWrapper("invalid value should be 1-5");
                }
            }while (!defaultSearchOption.contains(sOption));

            if (sOption.equals(5)){
                return;
            }
            HashMap<Integer,String> searcgBy = new HashMap<>(){{
                put(1,"id");
                put(2,"name");
                put(3,"gender");
                put(4,"className");
            }};
            String field = InputField.setField(String.class, searcgBy.get(sOption)+" to search");
            this.showBy(searcgBy.get(sOption),field);
            super.enterContinue();
        }while (defaultSearchOption.contains(sOption));
    }

    @Override
    public void update() {
        super.topMessage("Update students information");
        if (students.isEmpty()){
            AppUtil.errorWrapper("The students is empty.");
            super.enterContinue();
            return;
        }
        Integer id = InputField.setField(Integer.class,"id to update");
        if (this.checkStudentId(id)){
            Student student = this.getStudent(id);
            System.out.println("ID: "+ student.id +
                    "\t Name: "+ student.name +
                    "\t Gender: "+ student.gender +
                    "\t Age: "+ student.age +
                    "\t Class Name: "+ student.className +
                    "\t Score: "+ student.score
            );
            student.name = InputField.setField(String.class, "Name");
            student.gender = InputField.setField(String.class, "gender");
            student.age = InputField.setField(Integer.class, "age");
            student.className = InputField.setField(String.class, "class name");
            student.score = InputField.setField(Double.class, "score");
            students.set(students.indexOf(student),student);
            super.successMessage("updated");
        }else {
            AppUtil.errorWrapper("Student id:"+ id +" isn't exist.");
        }
        super.enterContinue();
    }

    @Override
    public void delete() {
        super.topMessage("Delete students information");
        if (students.isEmpty()){
            AppUtil.errorWrapper("The students is empty.");
            super.enterContinue();
            return;
        }
        Integer id = InputField.setField(Integer.class,"id to delete");
        if (this.checkStudentId(id)){
            students.remove(this.getStudent(id));
            super.successMessage("deleted");
        }else {
            AppUtil.errorWrapper("Student id:"+ id +" isn't exist.");
        }
        super.enterContinue();
    }

    @Override
    public void stop() {
        String message ="*\tAre you sure!, you want to exit\t*";
        System.out.println("*".repeat(message.length()+5));
        System.out.println(message);
        System.out.println("*\t=>Press Y/y to confirm.");
        System.out.println("*\t=>Press N/n to cancel.");
        String confirm;
        do {
            confirm = InputField.setField(String.class,"Confirm");
            if (confirm.equalsIgnoreCase("y")){
                System.exit(0);
            }else if (confirm.equalsIgnoreCase("n")){
                System.out.println("***** You are cancel exit. *****");
                break;
            }else {
                AppUtil.errorWrapper("invalid value should be y or n");
            }
        }while (true);
    }

    private Student getStudent(Integer id){
        for (Student student: students){
            if (student.id.equals(id)){
                return student;
            }
        }
        return new Student();
    }

    private boolean checkStudentId(Integer id){
        if (!students.isEmpty()){
            ArrayList<Integer> ids = new ArrayList<>();
            for (Student student : students) {
                ids.add(student.id);
            }
            return ids.contains(id);
        }
        return false;
    }

    private void showBy(String field, String value){
        System.out.println("*".repeat(this.formatHeader().length()));
        System.out.println(this.formatHeader());
        for (Student student: students){
            String str="";
            switch (field){
                case "id":
                    if (student.id.equals(Integer.parseInt(value))){
                        System.out.println(this.formatStudent(student));
                    }
                    break;
                case "name":
                    if (student.name.toLowerCase().startsWith(value.toLowerCase())){
                        System.out.println(this.formatStudent(student));
                    }
                    break;
                case "gender":
                    if (student.gender.toLowerCase().startsWith(value.toLowerCase())){
                        System.out.println(this.formatStudent(student));
                    }
                case "className":
                    if (student.className.toLowerCase().startsWith(value.toLowerCase())){
                        System.out.println(this.formatStudent(student));
                    }
                    break;
                default:
                    errorWrapper("Wrong option");
            }
        }
        System.out.println("*".repeat(this.formatHeader().length()));
    }

    private String formatHeader(){
        String head = "ID";
        head+= String.format("%-15s","");
        head += String.format("%-15s", "NAME");
        head += String.format("%-15s", "GENDER");
        head += String.format("%-15s", "AGE");
        head += String.format("%-15s", "CLASSNAME");
        head += String.format("%-15s", "SCORE");
        return head;
    }

    private String formatStudent(Student student){
        String str="";
        str = String.format("%-15s",String.format("%04d",student.id));
        str+= String.format("%-18s",student.name);
        str+= String.format("%-15s",student.gender);
        str+= String.format("%-15s",student.age);
        str+= String.format("%-15s",student.className);
        str+= String.format("%-15s",student.score);
        return str;
    }
}
