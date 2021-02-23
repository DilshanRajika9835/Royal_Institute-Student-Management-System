package business.custom.impl;/*@author:Dilshan Rajika Withanachchi*/

import business.custom.CourseBo;
import dao.DAOFactory;
import dao.custom.impl.CourseDAOImpl;
import dto.CourseDTO;
import entity.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseBOImpl implements CourseBo {
    final CourseDAOImpl courseDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.COURSE);

    @Override
    public boolean add(CourseDTO dto) throws Exception {
        return courseDAO.add(new Course(dto.getCourse_id(),dto.getCourse_name(),dto.getDuration(),dto.getCourse_fee()));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return courseDAO.delete(id);
    }

    @Override
    public boolean update(CourseDTO dto) throws Exception {
        return courseDAO.update(new Course(dto.getCourse_id(),dto.getCourse_name(),dto.getDuration(),dto.getCourse_fee()));

    }

    @Override
    public CourseDTO find(String id) throws Exception {
        final Course course = courseDAO.find(id);
        return new CourseDTO(0,course.getCourse_id(),course.getCourse_name(),course.getDuration(),course.getCourse_fee());

    }

    @Override
    public List<CourseDTO> findall() throws Exception {
        List<CourseDTO>courseDTOS=new ArrayList<>();
        final List<Course> findall = courseDAO.findall();
        int no=1;
        for (Course course:findall) {
         courseDTOS.add(new CourseDTO(no++,course.getCourse_id(),course.getCourse_name(),course.getDuration(),
                 course.getCourse_fee()));
        }
        return courseDTOS;
    }
}
