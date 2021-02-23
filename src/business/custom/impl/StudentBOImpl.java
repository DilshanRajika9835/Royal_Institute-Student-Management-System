package business.custom.impl;
/*@author:Dilshan Rajika Withanachchi*/

import business.custom.StudentBO;
import dao.DAOFactory;
import dao.SuperDAO;
import dao.custom.impl.StudentDAOImpl;
import dto.StudentDTO;
import entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {
    final StudentDAOImpl studentDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.STUDENT);
    @Override
    public boolean add(StudentDTO dto) throws Exception {
      return   studentDAO.add(new Student(dto.getStudent_id(),dto.getStudent_name(),dto.getAddress(),dto.getContact(),
                dto.getDob(),dto.getGender()));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return studentDAO.delete(id);

    }

    @Override
    public boolean update(StudentDTO dto) throws Exception {
        return   studentDAO.update(new Student(dto.getStudent_id(),dto.getStudent_name(),dto.getAddress(),dto.getContact(),
                dto.getDob(),dto.getGender()));
    }

    @Override
    public StudentDTO find(String id) throws Exception {
        final Student student = studentDAO.find(id);
       return new StudentDTO(0,student.getStudent_id(),student.getStudent_name(),student.getAddress(),student.getContact(),
                student.getDob(),student.getGender());
    }

    @Override
    public List<StudentDTO> findall() throws Exception {
        List<StudentDTO>studentDTOS=new ArrayList<>();
        int no=1;
        final List<Student> findall = studentDAO.findall();
        for (Student student:findall) {
         studentDTOS.add(new StudentDTO(no++,student.getStudent_id(),student.getStudent_name(),student.getAddress(),
                 student.getContact(),student.getDob(),student.getGender()));   
        }
        return studentDTOS;
    }

    @Override
    public String genstudentID() throws Exception {
        final String sid = studentDAO.genStudentID();
        if(sid!=null){
            int newSID=Integer.parseInt( sid.substring(1, 4))+1;
            System.out.println(newSID-1);
            if(newSID<10){
                return "S00"+newSID;
            }else if(newSID<100){
                return "S0"+newSID;
            }else {
                return "S"+newSID;
            }
        }
        return "S001";
    }
}
