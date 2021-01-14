package service;

import dao.StudentDao;
import models.Student;
import org.hibernate.Session;
import utils.HibernateUtil;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    @Override
    public Student findById(int id) {
        Session session = null;
        Student student = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            student = session.get(Student.class, id);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
        return student;
    }

    @Override
    public void save(Student student) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Student student) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(student);
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Student student) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(student);
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            students = session.createQuery("From Student").list();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
        return students;
    }
}
