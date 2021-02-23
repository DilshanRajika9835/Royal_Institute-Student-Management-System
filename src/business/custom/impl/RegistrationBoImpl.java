package business.custom.impl;/*@author:Dilshan Rajika Withanachchi*/

import business.custom.RegistrationBo;
import dao.DAOFactory;
import dao.custom.impl.CourseDAOImpl;
import dao.custom.impl.QueryDAOImpl;
import dao.custom.impl.RegistrationDAOImpl;
import dao.custom.impl.StudentDAOImpl;
import dto.RegistrationDTO;
import dto.StudentDTO;
import entity.Registration;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import view.tm.RegistrationTM;

import java.util.ArrayList;
import java.util.List;

public class RegistrationBoImpl implements RegistrationBo {
    final RegistrationDAOImpl registrationDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.REGISTRATION);
    final StudentDAOImpl studentDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.STUDENT);
    final CourseDAOImpl courseDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.COURSE);
    final QueryDAOImpl queryDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOType.QUERY);

    @Override
    public boolean add(RegistrationDTO dto) throws Exception {
       return registrationDAO.add(new Registration(dto.getReg_no(),studentDAO.find(dto.getStudent_id()),
                courseDAO.find(dto.getCourse_id()),dto.getReg_date(),dto.getReg_fee()));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public boolean update(RegistrationDTO dto) throws Exception {
        return registrationDAO.update(new Registration(dto.getReg_no(),studentDAO.find(dto.getStudent_id()),
                courseDAO.find(dto.getCourse_id()),dto.getReg_date(),dto.getReg_fee()));
    }

    @Override
    public StudentDTO find(String id) throws Exception {
        return null;
    }

    @Override
    public List<RegistrationDTO> findall() throws Exception {
        int no=1;
        List<RegistrationDTO>registrationDTOList=new ArrayList<>();
        final List<Registration> findall = registrationDAO.findall();
        for (Registration reg:findall) {
            registrationDTOList.add(new RegistrationDTO(no++,reg.getReg_no(),reg.getStudent().getStudent_id(),
                    reg.getCourse().getCourse_id(),reg.getReg_date(),reg.getReg_fee()));
        }
        return registrationDTOList;
    }

    @Override
    public String genRegID(String id) throws Exception {
        final String regid = registrationDAO.genRegID(id);
        if(regid!=null){
            int newSID=Integer.parseInt( regid.substring(1, 4))+1;
            if(newSID<10){
                return "R00"+newSID;
            }else if(newSID<100){
                return "R0"+newSID;
            }else {
                return "R"+newSID;
            }
        }
        return "R001";

    }

    @Override
    public String findRegistration(String SID, String CID) throws Exception {
        return registrationDAO.findRegistration(SID,CID);
    }

    @Override
    public boolean deleteRegistration(String SID, String CID, String Reg) throws Exception {
      return registrationDAO.deleteRegistration(SID,CID,Reg);
    }

    @Override
    public ObservableList<RegistrationTM> getRegStudents(String CID) throws Exception {
        ObservableList<RegistrationTM>RegList=FXCollections.observableArrayList();
        final List<Object[]> regStudents = queryDAO.getRegStudents(CID);
        int no=1;
        for (Object[] objects : regStudents) {
            RegList.add(new RegistrationTM(no++,objects[0].toString(),objects[1].toString(),objects[2].toString(),
                    objects[3].toString(),
                    objects[4].toString(),
                    objects[5].toString()));
             }


        return RegList;
    }


}
