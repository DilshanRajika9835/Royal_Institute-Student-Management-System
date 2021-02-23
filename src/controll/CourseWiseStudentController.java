package controll;/*@author:Dilshan Rajika Withanachchi*/

import business.BOFactory;
import business.custom.impl.RegistrationBoImpl;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import view.tm.RegistrationTM;


public class CourseWiseStudentController {
    public AnchorPane root;
    public Label lbltitle;
    public Label lbldescription;
    public TableView<RegistrationTM> tblregistrationStudent;
    public TableColumn colno;
    public TableColumn colid;
    public TableColumn colsname;
    public TableColumn colcid;
    public TableColumn colcname;
    public TableColumn colregdate;
    final RegistrationBoImpl registrationBo = BOFactory.getInstance().getBo(BOFactory.BoType.REGISTRATION);
    public TableColumn colreg;

    public void initialize(){
        colno.setCellValueFactory(new PropertyValueFactory<>("no"));
        colid.setCellValueFactory(new PropertyValueFactory<>("sid"));
        colsname.setCellValueFactory(new PropertyValueFactory<>("sname"));
        colcid.setCellValueFactory(new PropertyValueFactory<>("cid"));
        colcname.setCellValueFactory(new PropertyValueFactory<>("cname"));
        colregdate.setCellValueFactory(new PropertyValueFactory<>("regdate"));
        colreg.setCellValueFactory(new PropertyValueFactory<>("reg"));


    }
    public void  loadRegisterStudent(String courseID){
        try {
            final ObservableList<RegistrationTM> regStudents = registrationBo.getRegStudents(courseID);
            tblregistrationStudent.setItems(regStudents);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
