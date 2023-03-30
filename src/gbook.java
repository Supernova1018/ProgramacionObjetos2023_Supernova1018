public class gbook {
    public String setgradeBook;
    public String getcourseName;

    public void displayMessage() {
    }

    public void setgradeBook(String gradeBook) {
    }

    public class Gradebook {


    /***
     *
     * ***/
    private String courseName;


    /**
     * @return the courseName
     */
    public String gradeBook() {
        return courseName;
    }


    /**
     *
     * @return name of the course
     * @param gradeBook the courseName to set
     */
    public void setgradeBook(String gradeBook) {
        this.courseName = courseName;
    }


    public void displayMessage() {
        System.out.println("Bienvenido a la planilla de notas " +  setgradeBook);
    }

        public String getCourseName() {
            return courseName;
        }
    }
}
